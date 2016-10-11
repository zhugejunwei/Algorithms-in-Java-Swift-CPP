//
//  Dijkstra-1D Array.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 9/29/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import Foundation

var srcNode3 = 0

//func printPath(_ parent: [Int], _ j: Int) {
//    if parent[j] == -1 { return }
//    printPath(parent, parent[j])
//    print(j, terminator: " ")
//}
//var V = 5
//func printSolution(_ dist: [Int],_ n: Int,_ parent: [Int], _ src: Int)
//{
//    for i in 1..<V
//    {
//        print("\n\(src) -> \(i) \t\t \(dist[i])\t\t \(src)", terminator: " ");
//        printPath(parent, i);
//    }
//}

func dijkstra1D(_ graph: [Int], _ src: Int)
{
    let V = Int(sqrt(Double(2 * graph.count))) + 1
    var parent = Array(repeating: -1, count: V)
    var dist = Array(repeating: Int(Int32.max), count: V)
    var sptSet = Array(repeating: false, count: V)
    dist[src] = 0
    for _ in 0..<V-1
    {
        let u = minDistance(dist, sptSet, V)
        sptSet[u] = true
        for v in 0..<V {
            let index = (2*V-1 - u)*u/2+v-u-1
            let temp = (2*V-1 - v)*v/2+u-v-1
            if sptSet[v] == false{
                if u > v {
                    if graph[temp] > 0 && dist[u] + graph[temp] < dist[v] {
                        parent[v]  = u
                        dist[v] = dist[u] + graph[temp]
                    }
                } else if u < v {
                    if graph[index] > 0 && dist[u] + graph[index] < dist[v] {
                        parent[v]  = u
                        dist[v] = dist[u] + graph[index]
                    }
                }
            }
        }
    }
//    printSolution(dist, V, parent, src)
}

func allPairDijktra(_ graph: [Int]) {
    let V = Int(sqrt(Double(2 * graph.count))) + 1
    for k in 0..<V {
        dijkstra1D(graph, k)
    }
}
