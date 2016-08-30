import Darwin
/*
 Divide and Conquer
 Two pointer
 QuickSort: nlog(n)
 i: last small
 j: first small/last big
 pivot: pivot element(here we choose the last one)
 */

// 1st version
func partition(inout arr: [Int], _ l: Int, _ r: Int) -> Int
{
    var i, j, pivot: Int
    pivot = arr[r]
    i = l - 1
    for j in l..<r {
        if arr[j] <= pivot {
            i += 1
            swap(&arr[i], &arr[j])
        }
    }
    swap(&arr[i+1], &arr[r])
    //    swap(&arr[i+1], &pivot) <- Wrong!! pivot is not arr[r], it's arr[r]'s copy, not reference!
    return i + 1
}

func quickSort(inout arr: [Int], _ l: Int, _ r: Int)
{
    if l < r {
        let key = partition(&arr, l, r)
        quickSort(&arr, l, key - 1)
        quickSort(&arr, key + 1, r)
    }
}

var a = [1,2,1,4,5,7,0]
let l = 0, r = a.count - 1
quickSort(&a, l, r)


// 2nd version
func adjust(inout arr: [Int], _ l: Int, _ r: Int) -> Int
{
    var i = l, j = r, p = arr[l]
    while i < j {
        // j: bigger than p
        while i < j && arr[j] >= p {
            j -= 1
        }
        if i < j {
            arr[i] = arr[j]
            i += 1
        }
        // i: smaller than p
        while i < j && arr[i] <= p {
            i += 1
        }
        if i < j {
            arr[j] = arr[i]
            j -= 1
        }
    }
    arr[i] = p
    return i
}

func quickSort2(inout arr: [Int], _ l: Int, _ r: Int)
{
    if l < r {
        let key = adjust(&arr, l, r)
        quickSort2(&arr, l, key - 1)
        quickSort2(&arr, key + 1, r)
    }
}

var b = [1,2,1,4,5,7,0]
let le = 0, ri = b.count - 1
quickSort2(&b, le, ri)