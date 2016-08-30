import Darwin

func insertionSort(inout arr: [Int]) {
    var curV, lastKey: Int
    for i in 1..<arr.count {
        curV = arr[i]
        lastKey = i - 1
        
        while lastKey >= 0 && arr[lastKey] > curV {
            arr[lastKey + 1] = arr[lastKey]
            lastKey -= 1
        }
        arr[lastKey + 1] = curV
    }
}

var a = [1,4,1,2,5,8,7,0]

insertionSort(&a)

