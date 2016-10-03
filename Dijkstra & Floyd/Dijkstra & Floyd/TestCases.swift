//
//  TestCases0],swift
//  Dijkstra & Floyd
//
//  Created by 诸葛俊伟 on 10/2/160],
//  Copyright © 2016 University of Pittsburgh0,All rights reserved0],
//

import Foundation

struct TestCases {
    static let testCase1 = [[0, 7, 0, 18, 15],
                     [7, 0, 11, 7, 5],
                     [0, 11, 0, 29, 0],
                     [18, 7, 29, 0, 18],
                     [15, 5, 0, 18, 0]]
    
    static let testCase2 = [[0, 0, 0, 0, 0, 0, 0, 0, 0, 8,  0, 0],
                     [0, 0,17, 13,  0, 0, 0,17, 12, 0, 0, 0],
                     [0,17,  0, 0, 0, 0, 0, 0, 0, 7,  0, 9],
                     [0,13,  0, 0, 0, 0,15,  0, 0,13,  5, 13],
                     [0, 0, 0, 0, 0,18, 0, 0, 5,  0, 0, 0],
                     [0, 0, 0, 0,18,  0, 0,17,  0, 0, 0, 0],
                     [0, 0, 0,15,  0, 0, 0,17,  0, 0, 8, 11],
                     [0,17,  0, 0, 0,17, 17,  0, 0, 0, 0, 0],
                     [0,12,  0, 0, 5,  0, 0, 0, 0, 0,15,  0],
                     [8,  0, 7, 13,  0, 0, 0, 0, 0, 0, 0,14],
                     [0, 0, 0, 5, 0, 0, 8,  0,15,  0, 0, 0],
                     [0, 0, 9, 13, 0, 0,11, 0, 0,14,  0, 0]]
    
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
            for j in i+1..<n {
                addEdge(&graph, i, j, array[i][j])
            }
        }
        return graph
    }
    
    func sparseLinkedList(_ n: Int) -> Graph {
        let array = sparse(n)
        var graph = createGraph(n)
        for i in 0..<n {
            for j in i+1..<n {
                if array[i][j] != 0 {
                    addEdge(&graph, i, j, array[i][j])
                }
            }
        }
        return graph
    }
    
    func complete1D(_ n: Int) -> [Int] {
        var res = [Int]()
        let array = complete(n)
        for i in 0..<n {
            for j in i + 1..<n {
                res.append(array[i][j])
            }
        }
        return res
    }
    
    func sparse1D(_ n: Int) -> [Int] {
        var res = [Int]()
        let array = sparse(n)
        for i in 0..<n {
            for j in i + 1..<n {
                res.append(array[i][j])
            }
        }
        return res
    }
    
    func LinkedList(_ graphArray: [[Int]]) -> Graph
    {
        let n = graphArray.count
        var graph = createGraph(n)
        for i in 0..<n {
            for j in i..<n {
                if graphArray[i][j] != 0 {
                    addEdge(&graph, i, j, graphArray[i][j])
                }
            }
        }
        return graph
    }
}

