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

struct MinHeapNode {
    var v: Int?
    var dist: Int?
}

struct MinHeap {
    var size: Int = 0
    var capacity: Int
    var pos: [Int] = [0]
    var array: [MinHeapNode]
    init(_ capacity: Int) {
        self.capacity = capacity
        self.array = [MinHeapNode]()
    }
}

func createMinHeap(_ capacity: Int) -> MinHeap {
    var minHeap = MinHeap(capacity)
    minHeap.size = 0
    minHeap.pos = Array(repeating: 0, count: capacity)
    minHeap.array = Array(repeating: MinHeapNode(), count: capacity)
    return minHeap
}

//sort shortest paths into a min-heap (heapify)
func minHeapify(_ minHeap: inout MinHeap, _ idx: Int) {
    var smallest, left, right: Int
    smallest = idx
    left = 2 * idx + 1
    right = 2 * idx + 2
    
    if left < minHeap.size && minHeap.array[left].dist! < minHeap.array[smallest].dist! {
        smallest = left
    }
    if right < minHeap.size && minHeap.array[right].dist! < minHeap.array[smallest].dist! {
        smallest = right
    }
    if smallest != idx
    {
        // The nodes to be swapped in min heap
        let smallestNode = minHeap.array[smallest]
        let idxNode = minHeap.array[idx]
        // swap position
        minHeap.pos[smallestNode.v!] = idx
        minHeap.pos[idxNode.v!] = smallest
        // swap nodes
        swap(&minHeap.array[smallest], &minHeap.array[idx])
        
        minHeapify(&minHeap, smallest)
    }
}

func isEmpty(_ minHeap: MinHeap) -> Bool {
    guard minHeap.size != 0 else {
        return true
    }
    return false
}

// extract minimum node from heap
func extractMin(_ minHeap: inout MinHeap) -> MinHeapNode {
    if isEmpty(minHeap) {
        return MinHeapNode()
    }
    let root = minHeap.array[0]
    let lastNode = minHeap.array[minHeap.size - 1]
    minHeap.array[0] = lastNode
    // update position of last node
    minHeap.pos[root.v!] = minHeap.size - 1
    minHeap.pos[lastNode.v!] = 0
    
    // reduce heap size and heapity root
    minHeap.size -= 1
    minHeapify(&minHeap, 0)
    
    return root
}

// decrease dist value of a given vertex v
func decreaseDist(_ minHeap: inout MinHeap, _ v: Int, _ dist: Int) {
    // index of v
    var i = minHeap.pos[v]
    
    // get the node and update its dist value
    minHeap.array[i].dist = dist
    
    while i > 0 && minHeap.array[i].dist! < minHeap.array[(i-1)/2].dist! {
        minHeap.pos[minHeap.array[i].v!] = (i-1)/2
        minHeap.pos[minHeap.array[(i-1)/2].v!] = i
        swap(&minHeap.array[i], &minHeap.array[(i-1)/2])
        i = (i - 1) / 2
    }
}

func isInMinHeap(_ minHeap: MinHeap, _ v: Int) -> Bool {
    if minHeap.pos[v] < minHeap.size {
        return true
    }
    return false
}

var srcNode = 0

func print(_ dist: [Int], _ n: Int) {
    print("Vertex Distance from Source \(srcNode)")
    for i in 0..<n {
        print("\t \(i) \t \(dist[i])")
    }
    srcNode += 1
}

// MARK: - The Main Function, O(ELogV)

func dijkstraLinkedList(_ graph: Graph, _ src: Int) {
    let V = graph.V
    var dist = Array(repeating: Int.max, count: V)
    var minHeap = createMinHeap(V)
    for v in 0..<V {
        minHeap.array[v] = MinHeapNode(v: v, dist: dist[v])
        minHeap.pos[v] = v
    }
    
    minHeap.array[src] = MinHeapNode(v: src, dist: dist[src])
    minHeap.pos[src] = src
    dist[src] = 0
    decreaseDist(&minHeap, src, dist[src])
    
    minHeap.size = V
    
    while !isEmpty(minHeap) {
        let minHeapNode = extractMin(&minHeap)
        let u = minHeapNode.v
        
        var pCrawl = graph.array[u!].head
        while pCrawl != nil {
            let v = pCrawl?.dest
            if isInMinHeap(minHeap, v!) && dist[u!] != Int.max && (pCrawl?.weight)! + dist[u!] < dist[v!] {
                dist[v!] = dist[u!] + (pCrawl?.weight)!
                decreaseDist(&minHeap, v!, dist[v!])
            }
            pCrawl = pCrawl?.next
        }
    }
    print(dist, V)
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

func allPairDijkstra(_ graph: Graph) {
    for k in 0..<graph.V {
        dijkstraLinkedList(graph, k)
    }
}

allPairDijkstra(graph)
