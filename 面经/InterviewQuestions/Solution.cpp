#include <iostream>
#include <map>
#include <list>
#include <utility>
using namespace std;

/* This is an algorithm that can solve all Knight Moving Problem 
 * for any random sizes or dimensions of check boards. 

 * The check board is represented as a 2D array backed by a 1D array,
 * and it can contain variables lengths of rows and columns, with valid
 * point set to 1 and invalid to 0, we can have the following as a valid 
 * check board for example:
 * 1 1 1 1 1 1 1 
 * 1 1 1 1 1 0 0 
 * 1 1 1 1 0 0 0
 * 1 1 1 1 1 1 0
 * 1 0 1 1 0 0 0
 * 1 0 0 0 0 0 0

 * We need four parameters: start position, end position, and array dimensions
 * all represented as pair<int, int>, and the check board array.


 */




//Map that stores all the valid moves of a discovered point
map<pair<int, int>, list<pair<int, int>>> hashMap;

//Fetch valid moves out of the map if available, calculate and stores all possible moves otherwise
list<pair<int, int>> possibleMoves(pair<int, int> source, pair<int, int> edge, int* board) {
	//Check if valid moves already exist in map
	if (hashMap.find(source) != hashMap.end()) {
		return hashMap[source];
	}

	//Not in map, need manual calculation
	list<pair<int, int>> result;

	//For any given point, there are a total of eight possible moves: 2 tops, 2 bottoms, 2 lefts and 2 rights
	//Check if the move will be off the check board or land on a invalid point in array (ones with value 0)
	//Store in the move list if the move is valid

	//Check left 2 moves 
	if (source.first >= 2) {
		//Check upper left move
		if (source.second >= 1 && board[source.first - 2 + (source.second - 1) * edge.first]) {
			result.emplace_back(pair<int, int>(source.first - 2, source.second - 1));
		}
		//Check lower left move
		if (source.second + 1 <= edge.second - 1 && board[source.first - 2 + (source.second + 1) * edge.first]) {
			result.emplace_back(pair<int, int>(source.first - 2, source.second + 1));
		}
	}

	//Check right 2 moves
	if (source.first + 2 <= edge.first - 1) {
		//Check upper right move
		if (source.second >= 1 && board[source.first + 2 + (source.second - 1) * edge.first]) {
			result.emplace_back(pair<int, int>(source.first + 2, source.second - 1));
		}
		//Check lower right move
		if (source.second + 1 <= edge.second - 1 && board[source.first + 2 + (source.second + 1) * edge.first]) {
			result.emplace_back(pair<int, int>(source.first + 2, source.second + 1));
		}
	}

	//Check top 2 moves
	if (source.second >= 2) {
		//Check left top move
		if (source.first >= 1 && board[source.first - 1 + (source.second - 2) * edge.first]) {
			result.emplace_back(pair<int, int>(source.first - 1, source.second - 2));
		}
		//Check right top move
		if (source.first + 1 <= edge.first - 1 && board[source.first + 1 + (source.second - 2) * edge.first]) {
			result.emplace_back(pair<int, int>(source.first + 1, source.second - 2));
		}
	}

	//Check bottom 2 moves
	if (source.second + 2 <= edge.second - 1) {
		//Check left bottom move
		if (source.first >= 1 && board[source.first - 1 + (source.second + 2) * edge.first]) {
			result.emplace_back(pair<int, int>(source.first - 1, source.second + 2));
		}
		//Check right bottom move
		if (source.first + 1 <= edge.first - 1 && board[source.first + 1 + (source.second + 2) * edge.first]) {
			result.emplace_back(pair<int, int>(source.first + 1, source.second + 2));
		}
	}
	//Store the result valid moves in map before sending the result back
	hashMap[source] = result;
	return result;
}

//Recursively check the number of paths to end point
int numberOfPaths(pair<int, int> start, pair<int, int> end, int moves, pair<int, int> edge, int* board) {
	int count = 0;
	//If moves == 0, we have a base case in recursion,
	//and if start == end, we have found 1 solution
	//Convert from 2D array to 1D array (x, y) -> (x + y * number of columns)
	if (moves <= 0 || !board[start.first + start.second * edge.first] || !board[end.first + end.second * edge.first]) {
		if (start == end) {
			return 1;
		}
		else {
			return count;
		}
	}

	for (pair<int, int> prev : possibleMoves(end, edge, board)) {
		count += numberOfPaths(start, prev, moves - 1, edge, board);
	}
	return count;
}

int main() {
	int board[] = { 1,1,1, 1,1,1, 1,1,1, 0,1,0 };
	pair<int, int> start(0, 0);
	pair<int, int> end(2, 2);
	pair<int, int> edge(3, 4);
	list<pair<int, int>> p;
	cout << numberOfPaths(start, end, 1, edge, board) << endl;
	cout << numberOfPaths(start, end, 2, edge, board) << endl;
	cout << numberOfPaths(start, end, 3, edge, board) << endl;
	cout << numberOfPaths(start, end, 4, edge, board) << endl;
	cout << numberOfPaths(start, end, 5, edge, board) << endl;
	cout << numberOfPaths(start, end, 6, edge, board) << endl;
	cout << numberOfPaths(start, end, 7, edge, board) << endl;
	cout << numberOfPaths(start, end, 8, edge, board) << endl;
	cout << numberOfPaths(start, end, 9, edge, board) << endl;
	cout << numberOfPaths(start, end, 10, edge, board) << endl;
	return 1;
}
