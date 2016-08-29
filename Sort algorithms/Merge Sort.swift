/*
 1. Split the array in half and sort each subarray.
 2. Weave the arrays back together in one fluid pass.
 
 O(nlog n) performance
 O(n) space
 */

import Darwin

/*
 1. Recursion
 
 Function: func merge(), func mergeSort1()
 */

// Merge two subarrays of arr[]
// First subarray is arr[l...m]
// Second subarray is arr[m+1...r]
func merge(inout arr: [Int], _ l: Int, _ m: Int, _ r: Int)
{
    var i = 0, j = 0, k = l // Initial index of first/second/merged subarray
    let n1 = m - l + 1 // The length of first subarray
    let n2 = r - m // The length of second subarray
    
    // Create tmp arrays
    var leftArr = Array(count: n1, repeatedValue: 0)
    var rightArr = Array(count: n1, repeatedValue: 0)
    
    for i in 0..<n1 {
        leftArr[i] = arr[l + i]
    }
    for j in 0..<n2 {
        rightArr[j] = arr[m + 1 + j]
    }
    
    // Merge the tmp arrays back into arr[l...r]
    while i < n1 && j < n2 {
        if leftArr[i] <= rightArr[j] {
            arr[k] = leftArr[i]
            i += 1
        }else {
            arr[k] = rightArr[j]
            j += 1
        }
        k += 1
    }
    
    // Copy the remaining elements of leftArr[] into arr[]
    while i < n1 {
        arr[k] = leftArr[i]
        i += 1
        k += 1
    }
    
    // Copy the remaining elements of rightArr[] into arr[]
    while j < n2 {
        arr[k] = rightArr[j]
        j += 1
        k += 1
    }
}

func mergeSort(inout arr: [Int], _ l: Int, _ r: Int)
{
    if l < r {
        let m = l + (r-l)/2
        
        mergeSort(&arr, l, m)
        mergeSort(&arr, m+1, r)
        
        merge(&arr, l, m, r)
    }
}

print("\n First Solution:")
var myArr = [12, 11, 13, 5, 6, 7]
print("\t Given array is: \(myArr)")
let size = myArr.count
mergeSort(&myArr, 0, size - 1)
print("\t Sorted array is: \(myArr)")




/*
 2. Recursion
 
 Function: func mergeSort2()
 */

func mergeSort2(inout arr: [Int], _ l: Int, _ r: Int) -> [Int]
{
    if l == r {
        return arr
    }
    
    let m = l + (r-l)/2
    mergeSort2(&arr, l, m)
    mergeSort2(&arr, m + 1, r)
    
    var tmp = Array(count: arr.count, repeatedValue: 0)
    var f1 = l, f2 = m + 1
    for i in l...r {
        if f1 > m {
            tmp[i] = arr[f2]
            f2 += 1
        }else if f2 > r {
            tmp[i] = arr[f1]
            f1 += 1
        }else if arr[f1] > arr[f2] {
            tmp[i] = arr[f2]
            f2 += 1
        }else {
            tmp[i] = arr[f1]
            f1 += 1
        }
    }
    
    for i in l...r {
        arr[i] = tmp[i]
    }
    return arr
}

print("\n Second Solution:")
var myArr2 = [5,3,2,1,4,6,7]
print("\t Given array is: \(myArr2)")
let size2 = myArr2.count
mergeSort2(&myArr2, 0, size2 - 1)
print("\t Sorted array is: \(myArr2)")




/*
 3. Iteration
 
 Function: func mergeSort3()
 */
func mergeSort3(inout arr: [Int], _ n: Int) {
    var range = 2, i = 0
    while range <= n {
        i = 0
        while i + range <= n {
            merge(&arr, i, i + range/2 - 1, i + range  - 1)
            i += range
        }
        
        // Last and left elements
        merge(&arr, i, i + range/2 - 1, n - 1)
        range *= 2
    }
    
    // The whole array
    merge(&arr, 0, range/2-1, n - 1)
}

print("\n Third Solution:")
var myArr3 = [5,3,2,1,4,6,7]
print("\t Given array is: \(myArr3)")
let size3 = myArr3.count
mergeSort3(&myArr3, size3)
print("\t Sorted array is: \(myArr3)")






/*
 4. Iteration
 
 Function: func mergeSort()
 */

/*
 func pass(inout arr: [Int], inout _ rec: [Int], _ n: Int) -> Int {
 var num = 0
 var biger = arr[0]
 rec[num] = 0
 num += 1
 for i in 1..<n {
 if arr[i] >= biger {
 biger = arr[i]
 }else {
 rec[num] = i
 num += 1
 biger = arr[i]
 }
 }
 rec[num] = n
 num += 1
 return num
 }
 
 func merge2(inout arr: [Int], _ f: Int, _ e: Int, _ m: Int) {
 var tmp = Array(count: arr.count, repeatedValue: 0)
 var f1 = f, f2 = m + 1
 for i in f...e {
 if f1 > m {
 tmp[i] = arr[f2]
 f2 += 1
 }else if f2 > e {
 tmp[i] = arr[f1]
 f1 += 1
 }else if arr[f1] > arr[f2] {
 tmp[i] = arr[f2]
 f2 += 1
 }else {
 tmp[i] = arr[f1]
 f1 += 1
 }
 }
 for i in f...e {
 arr[i] = tmp[i]
 }
 }
 
 func mergeSort4(inout arr: [Int], _ n: Int) {
 var rec = arr
 var num = pass(&arr, &rec, n)
 while num != 2 {
 var i = 0
 while i < num {
 merge2(&arr, rec[i], rec[i+2]-1, rec[i+1]-1)
 i += 2
 }
 num = pass(&arr, &rec, n)
 }
 }
 
 print("\n Fourth Solution:")
 var arr = [4,2,3,1,5]
 print("\t Given array is: \(arr)")
 let size4 = arr.count
 mergeSort4(&arr, size4)
 print("\t Sorted array is: \(arr)")
 */