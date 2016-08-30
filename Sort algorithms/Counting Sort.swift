import Darwin

/*
 Counting sort
 
 Description: Comparison-based sorts can't be faster than n * log(n).
 BUT non-comparison based ones can. There are catches, though.
 
 count[]: Record the position of each element in arr[]
 */

func countingSort(inout arr: [Int], _ max: Int) -> [Int] {
    var count = Array(count: max, repeatedValue: 0)
    var output = Array(count: arr.count+1, repeatedValue: 0)
    for i in 0..<arr.count {
        count[arr[i]] += 1
    }
    for i in 1..<max {
        count[i] += count[i-1]
    }
    for j in 0..<arr.count {
        let i = arr[j]
        while count[i] > count[i-1] && output[count[i]] == 0 {
            output[count[i]] = i
            count[i] -= 1
        }
    }
    output.removeFirst()
    return output
}

var a = [1,4,1,2,7,5,2]

countingSort(&a, 9)



