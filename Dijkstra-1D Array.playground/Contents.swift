import Foundation

func minDistance(_ dist: [Int], _ sptSet: [Bool], _ V: Int) -> Int {
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

var srcNode3 = 0

func print(_ dist: [Int], _ n: Int) {
    print("Vertex Distance from Source \(srcNode3)")
    for i in 0..<n {
        print(i, dist[i])
    }
    srcNode3 += 1
}

func dijkstra1D(_ graph: [Int], _ src: Int)
{
    let V = Int(sqrt(Double(2 * graph.count))) + 1
    var dist = Array(repeating: Int.max, count: V)
    var sptSet = Array(repeating: false, count: V)
    dist[src] = 0
    for _ in 0..<V-1
    {
        let u = minDistance(dist, sptSet, V)
        sptSet[u] = true
        for v in 0..<V {
            let index = (17 - u)*u/2+v-u-1
            let temp = (17 - v)*v/2+u-v-1
            if sptSet[v] == false && dist[u] != Int.max && dist[u] + graph[index] < dist[v] {
                if u > v {
                    if graph[temp] > 0 && dist[u] + graph[temp] < dist[v] {
                        dist[v] = dist[u] + graph[temp]
                    }
                } else if u < v {
                    if graph[index] > 0 {
                    dist[v] = dist[u] + graph[index]
                    }
                }
            }
        }
    }
    print(dist, V)
}

func allPairDijktra(_ graph: [Int]) {
    let V = Int(sqrt(Double(2 * graph.count))) + 1
    for k in 0..<V {
        dijkstra1D(graph, k)
    }
}

let graph = [4, 0, 0, 0, 0, 0, 8, 0, 8, 0, 0, 0, 0, 11, 0, 7, 0, 4, 0, 0, 2, 9, 14, 0, 0, 0, 10, 0, 0, 0, 2, 0, 0, 1, 6, 7]
allPairDijktra(graph)
