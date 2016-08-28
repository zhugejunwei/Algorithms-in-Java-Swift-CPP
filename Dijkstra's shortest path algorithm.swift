/*
 Description: A swift program for Dijkstra's single source shortest path algorithm.
 The program is for adjacency matrix representation of the graph.
 
 Time complexity: O(v^2)
 */

import Darwin

// Number of vertices in the graph
var V = 9

// A utility function to find the vertex with minimum distance value, from
// the set of vertices not yet included in shortest path tree.
func minDistance(dist: [Int], _ sptSet: [Bool]) -> Int {
    var min = Int.max
    var minIndex = 0
    
    for v in 0..<V {
        if sptSet[v] == false && dist[v] <= min {
            min = dist[v]
            minIndex = v
        }
    }
    
    return minIndex
}

// A utility function to print the constructed distance array
func printSolution (dist: [Int], _ n: Int) {
    print("Vertex   Distance from Source")
    for i in 0..<V {
        print(i, dist[i])
    }
}

// Function that implements Dijkstra's single source shortest path algorithm
// for a graph represented using adjacency matrix representation
func dijkstra(graph: [[Int]], _ src: Int) {
    var dist = Array(count: V, repeatedValue: Int.max) // The output array.  dist[i] will hold th shortest
    // distance from src to i
    
    var sptSet = Array(count: V, repeatedValue: false) // sptSet[i] will true if vertex i is included in shortest
    // path tree or shortest distance from src to i is finalized
    
    // Distance of source vertex from itself is always 0
    dist[src] = 0
    
    // Find shortest path for all vertices
    for _ in 0..<V-1
    {
        // Pick the minimum distance vertex from the set of vertices not yet processed.
        // U is always equal to src in first iteration
        let u = minDistance(dist, sptSet)
        
        // Mark the picked vertex as processed
        sptSet[u] = true
        
        // Update dist value of the adjacent vertices of the picked vertex.
        for v in 0..<V {
            
            // Update the distance values of adjacent vertices of picked vertex.
            // Only if 1. it is not in sptSet;
            // 2. there is an edge from u to v
            // 3. total weight of path from src to v through u is smaller than current value of dist[v]
            if sptSet[v] == false && graph[u][v] > 0 && dist[u] + graph[u][v] < dist[v] {
                dist[v] = dist[u] + graph[u][v]
            }
        }
    }
    
    printSolution(dist, V)
}

let graph = [[0, 4, 0, 0, 0, 0, 0, 8, 0],
             [4, 0, 8, 0, 0, 0, 0, 11, 0],
             [0, 8, 0, 7, 0, 4, 0, 0, 2],
             [0, 0, 7, 0, 9, 14, 0, 0, 0],
             [0, 0, 0, 9, 0, 10, 0, 0, 0],
             [0, 0, 4, 0, 10, 0, 2, 0, 0],
             [0, 0, 0, 14, 0, 2, 0, 1, 6],
             [8, 11, 0, 0, 0, 0, 1, 0, 7],
             [0, 0, 2, 0, 0, 0, 6, 7, 0]
]

dijkstra(graph, 0)

