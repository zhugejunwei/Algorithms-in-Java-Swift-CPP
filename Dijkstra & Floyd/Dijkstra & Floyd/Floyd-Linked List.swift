//
//  Floyd-Linked List.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 10/1/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import Foundation

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
    print3(V, dist)
}

func print3(_ n: Int, _ dist: [[Int]]) {
    for i in 0..<n {
        for j in 0..<n {
            print(dist[i][j], terminator:",")
        }
        print("\n")
    }
}
