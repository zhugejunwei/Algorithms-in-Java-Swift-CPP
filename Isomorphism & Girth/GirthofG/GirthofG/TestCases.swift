//
//  TestCases.swift
//  Girth
//
//  Created by 诸葛俊伟 on 10/23/16.
//  Copyright © 2016 University of Pittsburgh. All rights reserved.
//

import Foundation

struct TestCases {
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
}
