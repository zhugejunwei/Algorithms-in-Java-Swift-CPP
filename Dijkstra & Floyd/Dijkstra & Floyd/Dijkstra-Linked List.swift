//
//  Dijkstra-Linked List.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 9/29/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import Foundation

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

func print1(_ dist: [Int], _ n: Int) {
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
    print1(dist, V)
}

func allPairDijkstra(_ graph: Graph) {
    for k in 0..<graph.V {
        dijkstraLinkedList(graph, k)
    }
}

