// No solution works. = =!!

import Darwin

/*
 Counting sort
 
 Description: Comparison-based sorts can't be faster than n * log(n).
 BUT non-comparison based ones can. There are catches, though.
 */


// 1st solution
func countingSort(inout arr: [Int], _ max: Int) {
    var counts = Array(count: max, repeatedValue: 0)
    for i in 0..<arr.count {
        counts[arr[i] - 1] += 1
    }
    counts
    var i = 0
    for j in 0..<max {
        while counts[j] > 0 {
            arr[i] = j + 1
            i += 1
        }
    }
}



// 2nd solution.
let RANGE = 255

func countSort(inout arr: [Int]) {
    var output = Array(count: arr.count, repeatedValue: 0)
    var count = Array(count: RANGE + 1, repeatedValue: 0)
    var i = 0
    while arr[i] > 0 {
        count[arr[i]] += 1
        i += 1
    }
    
    for j in 1...RANGE {
        count[j] += count[j-1]
    }
    
    i = 0
    while arr[i] > 0 {
        output[count[arr[i]]-1] = arr[i]
        count[arr[i]] -= 1
    }
    i = 0
    while arr[i] > 0 {
        arr[i] = output[i]
    }
}

var a = [2,1]

countSort(&a)