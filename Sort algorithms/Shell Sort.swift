import Darwin

// shell sort

func shell(inout arr: [Int], _ n: Int)
{
    var gap = n/2
    while gap > 0 {
        for i in gap..<n {
            var j = i - gap
            while j >= 0 && arr[j] > arr[j+gap] {
                swap(&arr[j], &arr[j+gap])
                j = j - gap
            }
        }
        gap /= 2
    }
}

var a = [1,2,1,4,7,6,0]
let n = a.count
shell(&a, n)