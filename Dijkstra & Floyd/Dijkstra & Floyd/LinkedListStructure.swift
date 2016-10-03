//
//  LinkedListStructure.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 10/2/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

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
