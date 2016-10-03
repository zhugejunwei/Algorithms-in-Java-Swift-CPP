import Foundation

func floyd2D(_ n: Int, _ graph: [[Int]])
{
    var dist = Array(repeating: Array(repeatElement(0, count: n)), count: n)
    for i in 0..<n {
        for j in 0..<n {
            if i == j {
                dist[i][j] = 0
            } else if graph[i][j] == 0 {
                dist[i][j] = Int(Int32.max)
            } else {
                dist[i][j] = graph[i][j]
            }
        }
    }
    for k in 0..<n {
        for i in 0..<n {
            for j in 0..<n {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
            }
        }
    }
    print(n, dist)
}

func print(_ n: Int, _ dist: [[Int]]) {
    for i in 0..<n {
        for j in 0..<n {
            print(dist[i][j], terminator:",")
        }
        print("\n")
    }
}

let graph = [[0, 4, 0, 0, 0, 0, 0, 8, 0],
             [4, 0, 8, 0, 0, 0, 0, 11, 0],
             [0, 8, 0, 7, 0, 4, 0, 0, 2],
             [0, 0, 7, 0, 9, 14, 0, 0, 0],
             [0, 0, 0, 9, 0, 10, 0, 0, 0],
             [0, 0, 4, 14, 10, 0, 2, 0, 0],
             [0, 0, 0, 0, 0, 2, 0, 1, 6],
             [8, 11, 0, 0, 0, 0, 1, 0, 7],
             [0, 0, 2, 0, 0, 0, 6, 7, 0]]

floyd2D(9, graph)
