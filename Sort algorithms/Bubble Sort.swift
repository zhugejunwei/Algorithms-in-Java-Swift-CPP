/*
 The maximum elements bubble to the top
 O(n^2) performance
 O(1) space
 */

import Darwin

func swap(inout array: [Int], _ i: Int, _ j: Int) {
    let tmp = array[i]
    array[i] = array[j]
    array[j] = tmp
}

func bubbleSort(array: [Int]) -> [Int] {
    var array = array
    for _ in 0..<array.count {
        for j in 0..<array.count-1 {
            if array[j] > array[j+1] {
                swap(&array, j, j+1)
            }
        }
    }
    return array
}

let a = [8,5,2,6]

bubbleSort(a)