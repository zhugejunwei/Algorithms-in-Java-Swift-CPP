/*
 Find the minimum element n times
 O(n^2) performance
 O(1) space
 */

import Darwin

func swap(inout array: [Int], _ i: Int, _ j: Int) {
    let tmp = array[i]
    array[i] = array[j]
    array[j] = tmp
}

func selectionSort(array: [Int]) -> [Int] {
    var array = array
    for i in 0..<array.count {
        var minIndex = i
        for j in i..<array.count {
            if array[j] < array[minIndex] {
                minIndex = j
            }
        }
        swap(&array, i, minIndex)
    }
    return array
}

let a = [8,5,2,6]

selectionSort(a)