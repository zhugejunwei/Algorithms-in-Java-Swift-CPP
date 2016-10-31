import Foundation

struct case1 {
    static let graph1 = [[0, 1, 0, 0, 1, 1, 0],
                  [1, 0, 1, 0, 0, 0, 1],
                  [0, 1, 0, 1, 0, 0, 0],
                  [0, 0, 1, 0, 0, 1, 1],
                  [1, 0, 0, 0, 0, 0, 1],
                  [1, 0, 0, 1, 0, 0, 1],
                  [0, 1, 0, 1, 1, 1, 0]]
    
    static let graph2 = [[0, 1, 0, 1, 1, 0, 0],
                  [1, 0, 0, 0, 0, 1, 1],
                  [0, 0, 0, 1, 1, 1, 0],
                  [1, 0, 1, 0, 0, 0, 0],
                  [1, 0, 1, 0, 0, 1, 1],
                  [0, 1, 1, 0, 1, 0, 0],
                  [0, 1, 0, 0, 1, 0, 0]]
}

struct case2 {
    static let graph1 = [[0, 1, 1, 1, 1, 0],
                  [1, 0, 0, 0, 1, 1],
                  [1, 0, 0, 0, 1, 1],
                  [1, 0, 0, 0, 0, 1],
                  [1, 1, 1, 0, 0, 1],
                  [0, 1, 1, 1, 1, 0]]
    
    static let graph2 = [[0, 0, 0, 0, 0, 1],
                  [0, 0, 0, 1, 1, 0],
                  [0, 0, 0, 1, 1, 0],
                  [0, 1, 1, 0, 1, 1],
                  [0, 1, 1, 1, 0, 0],
                  [1, 0, 0, 1, 0, 0]]
}

struct case3 {
    static let graph1 = [[0, 0, 1, 1, 1, 1, 0],
                  [0, 0, 0, 0, 1, 0, 0],
                  [1, 0, 0, 0, 1, 1, 0],
                  [1, 0, 0, 0, 1, 1, 0],
                  [1, 1, 1, 1, 0, 0, 1],
                  [1, 0, 1, 1, 0, 0, 1],
                  [0, 0, 0, 0, 1, 1, 0]]
    
    static let graph2 = [[0, 0, 0, 0, 0, 0, 1],
                  [0, 0, 1, 1, 0, 1, 1],
                  [0, 1, 0, 0, 0, 1, 1],
                  [0, 1, 0, 0, 0, 1, 1],
                  [0, 0, 0, 0, 0, 1, 1],
                  [0, 1, 1, 1, 1, 0, 0],
                  [1, 1, 1, 1, 1, 0, 0]]
}

// permutation
var perm = Array(repeating: -1, count: case3.graph1.count)

func isomorphism(_ graph1: [[Int]], _ graph2: [[Int]]) {
    let n = graph1.count
    var used = Array(repeating: false, count: n)
    let isomorphic = DFS(n, n - 1, graph1, graph2, &used)
    if isomorphic == true {
        print("Graphs are isomorphic")
        print("The orderings of graph1 vertices: ")
        for i in 0..<n {
            print(i, terminator: "\t")
        }
        print("\n")
        print("The orderings of graph1 vertices: ")
        for i in 0..<n {
            print(perm[i], terminator: "\t")
        }
    } else {
        print("Graphs are NOT isomorphic")
    }
}

func DFS(_ n: Int, _ level: Int, _ graph1: [[Int]], _ graph2: [[Int]], _ used: inout [Bool]) -> Bool {
    var result = false
    if (level == -1) {
        result = checkEdges(n, graph1, graph2);
    } else {
        var i = 0
        while (i < n && result == false) {
            if used[i] == false {
                used[i] = true
                perm[level] = i
                result = DFS(n, level - 1, graph1, graph2, &used)
                used[i] = false
            }
            i += 1
        }
    }
    return result
}

// check the edges of graph1 and graph2
func checkEdges(_ n: Int, _ graph1: [[Int]], _ graph2: [[Int]]) -> Bool {
    var same = true
    for x in 0...n-1 {
        var y = 0
        while (y < n && same == true) {
            if graph1[x][y] != graph2[perm[x]][perm[y]] {
                same = false
            }
            y += 1
        }
    }
    return same
}

//isomorphism(case1.graph1, case1.graph2) // Graphs are isomorphic
//isomorphism(case2.graph1, case2.graph2) // Graphs are NOT isomorphic
//isomorphism(case3.graph1, case3.graph2) // Graphs are isomorphic
