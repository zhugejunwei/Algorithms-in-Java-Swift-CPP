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

func complete(_ n: Int) -> [[Int]]
{
    var completeGraph = Array(repeating: Array(repeatElement(0, count: n)), count:n)
    for i in 0..<n {
        for j in 0..<i {
            let rand = Int(arc4random_uniform(UInt32(n))) + 1
            completeGraph[i][j] = rand
            completeGraph[j][i] = rand
        }
    }
    return completeGraph
}

func sparse(_ n: Int) -> [[Int]]
{
    var sparseGraph = Array(repeating: Array(repeatElement(0, count: n)), count:n)
    var chosenNodes = Array(repeating: false, count: n)
    var i = 0
    while chosenNodes.contains(false) {
        chosenNodes[i] = true
        if chosenNodes.contains(false) {
            var pickJ = Int(arc4random_uniform(UInt32(n)))
            if chosenNodes[pickJ] == true {
                while chosenNodes[pickJ] == true {
                    pickJ = (pickJ + 1)%(n)
                }
            }
            let j = pickJ
            let rand = Int(arc4random_uniform(UInt32(n))) + 1
            sparseGraph[i][j] = rand
            sparseGraph[j][i] = rand
            i = j
        }
    }
    return sparseGraph
}

func completeLinkedList(_ n: Int) -> Graph {
    let array = complete(n)
    var graph = createGraph(n)
    for i in 0..<n {
        for j in i..<n {
            addEdge(&graph, i, j, array[i][j])
        }
    }
    return graph
}

func sparseLinkedList(_ n: Int) -> Graph {
    let array = sparse(n)
    var graph = createGraph(n)
    for i in 0..<n {
        for j in i..<n {
            if array[i][j] != 0 {
                addEdge(&graph, i, j, array[i][j])
            }
        }
    }
    return graph
}

