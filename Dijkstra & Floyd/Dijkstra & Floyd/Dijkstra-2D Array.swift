//
//  Dijkstra-2D Array.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 9/29/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

/*
 Description: A swift program for Dijkstra's single source shortest path algorithm.
 The program is for adjacency matrix representation of the graph.
 
 Time complexity: O(v^2)
 */

import Foundation

// Function that implements Dijkstra's single source shortest path algorithm
// for a graph represented using adjacency matrix representation
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
//
//func printPath(_ parent: [Int], _ j: Int) {
//    if parent[j] == -1 { return }
//    printPath(parent, parent[j])
//    print(j, terminator: " ")
//}
//
//func printSolution(_ dist: [Int],_ n: Int,_ parent: [Int], _ src: Int)
//{
//    for i in 1..<n
//    {
//        print("\n\(src) -> \(i) \t\t \(dist[i])\t\t \(src)", terminator: " ");
//        printPath(parent, i);
//    }
//}

// Function that implements Dijkstra's single source shortest path algorithm
// for a graph represented using adjacency matrix representation
func dijkstra(_ graph: [[Int]], _ src: Int)
{
    let V = graph.count
    var parent = Array(repeating: -1, count: V)
    var dist = Array(repeating: Int.max, count: V) // The output array.  dist[i] will hold the shortest
    // distance from src to i
    
    var sptSet = Array(repeating: false, count: V) // sptSet[i] will be true if vertex i is included in shortest
    // path tree or shortest distance from src to i is finalized
    
    // Distance of source vertex from itself is always 0
    dist[src] = 0
    
    // Find shortest path for all vertices
    for _ in 0..<V-1
    {
        // Pick the minimum distance vertex from the set of vertices not yet processed.
        // u is always equal to src in first iteration
        let u = minDistance(dist, sptSet, V)
        
        // Mark the picked vertex as processed
        sptSet[u] = true
        
        // Update dist value of the adjacent vertices of the picked vertex.
        for v in 0..<V {
            // Update the distance values of adjacent vertices of picked vertex.
            // Only if 1. it is not in sptSet;
            // 2. there is an edge from u to v
            // 3. total weight of path from src to v through u is smaller than current value of dist[v]
            if sptSet[v] == false && graph[u][v] > 0 && dist[u] + graph[u][v] < dist[v] {
                parent[v]  = u
                dist[v] = dist[u] + graph[u][v]
            }
        }
    }
//    printSolution(dist, V, parent, src)
}

func allPairDijkstra(_ graph: [[Int]]) {
    for k in 0..<graph.count {
        dijkstra(graph, k)
    }
}
