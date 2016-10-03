//
//  Floyd-2D.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 10/1/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import Foundation

func floyd2D(_ graph: [[Int]])
{
    let n = graph.count
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
//    print2(n, dist)
}

//func print2(_ n: Int, _ dist: [[Int]]) {
//    for i in 0..<n {
//        for j in 0..<n {
//            print(dist[i][j], terminator:",")
//        }
//        print("\n")
//    }
//}

