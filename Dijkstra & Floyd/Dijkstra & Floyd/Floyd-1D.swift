//
//  Floyd-1D.swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 10/1/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import Foundation

func floyd1D(_ graph: [Int])
{
    let n = Int(sqrt(Double(2 * graph.count))) + 1
    var dist = graph
    for i in 0..<graph.count {
        if graph[i] == 0 {
            dist[i] = Int(Int32.max)
        }
    }
    for k in 0..<n {
        for i in 0..<n {
            for j in 0..<n {
                var ij = 0, ik = 0, kj = 0
                if i < j {
                    ij = (17-i)*i/2+j-i-1
                } else if i > j {
                    ij = (17-j)*j/2+i-j-1
                }
                if i < k {
                    ik = (17-i)*i/2+k-i-1
                } else if i > k {
                    ik = (17-k)*k/2+i-k-1
                }
                if k < j {
                    kj = (17-k)*k/2+j-k-1
                } else if k > j {
                    kj = (17-j)*j/2+k-j-1
                }
                guard ij != ik && ij != kj && ik != kj else {
                    break
                }
                dist[ij] = min(dist[ij], dist[ik] + dist[kj])
            }
        }
    }
    print(n, dist)
}

func print(_ n: Int, _ dist: [Int]) {
    var dist = dist
    var n = n - 1
    var m = n
    for i in 0..<dist.count {
        if i == n {
            print("\n")
            m -= 1
            n += m
        }
        if dist[i] == Int(Int32.max) {
            dist[i] = 0
        }
        print(dist[i], terminator:",")
    }
}

