import Foundation

public class AdjListNode {
    var dest: Int
    var weight: Int
    var next: AdjListNode?
    
    public init(_ dest: Int, _ weight: Int) {
        self.dest = dest
        self.weight = weight
    }
}

// adjacency list
struct AdjList {
    var head: AdjListNode?
}

struct Graph {
    var V: Int // size of array
    var array: Array<AdjList>
    
    init() {
        V = 0
        self.array = Array<AdjList>()
    }
}

func createGraph(_ V: Int) -> Graph {
    var graph = Graph()
    graph.V = V
    graph.array = Array(repeating: AdjList(), count: V)
    for i in 0..<V {
        graph.array[i].head = nil
    }
    return graph
}

// add an edge to an undirected graph
func addEdge(_ graph: inout Graph, _ src: Int, _ dest: Int, _ weight: Int)
{
    // add an edge from src to destination
    // a new node is added to the adjacency list of src at the beginning.
    var newNode = AdjListNode(dest, weight)
    newNode.next = graph.array[src].head
    graph.array[src].head = newNode
    
    // add an edge from dest to src also
    newNode = AdjListNode(src, weight)
    newNode.next = graph.array[dest].head
    graph.array[dest].head = newNode
}

func floydLinkedList(_ graph: Graph) {
    let V = graph.V
    var dist = Array(repeating: Array(repeatElement(Int(Int32.max), count: V)), count: V)
    var mark = Array(repeating: false, count: V)
    var u = 0, v = 0
    for i in 0..<V {
        var cur = graph.array[i].head
        u = i
        mark[u] = true
        while cur != nil {
            v = cur!.dest
            if mark[v] == false {
                dist[u][v] = cur!.weight
                dist[v][u] = cur!.weight
            }
            cur = cur?.next

        }
    }
    for i in 0..<V {
        for j in 0..<V {
            if i==j {
                dist[i][j] = 0
            }
        }
    }
    for k in 0..<V {
        for i in 0..<V {
            for j in 0..<V {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
            }
        }
    }
    print(V, dist)
}

func print(_ n: Int, _ dist: [[Int]]) {
    for i in 0..<n {
        for j in 0..<n {
            print(dist[i][j], terminator:",")
        }
        print("\n")
    }
}

let V = 9
var graph = createGraph(V)
addEdge(&graph, 0, 1, 4)
addEdge(&graph, 0, 7, 8)
addEdge(&graph, 1, 2, 8)
addEdge(&graph, 1, 7, 11)
addEdge(&graph, 2, 3, 7)
addEdge(&graph, 2, 8, 2)
addEdge(&graph, 2, 5, 4)
addEdge(&graph, 3, 4, 9)
addEdge(&graph, 3, 5, 14)
addEdge(&graph, 4, 5, 10)
addEdge(&graph, 5, 6, 2)
addEdge(&graph, 6, 7, 1)
addEdge(&graph, 6, 8, 6)
addEdge(&graph, 7, 8, 7)

floydLinkedList(graph)
