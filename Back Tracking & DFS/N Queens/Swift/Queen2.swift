import UIKit

var n = 15
var count = 0
var sol = [Int]()

var start = Date(), end = Date(), timer:Double = 0.0

var shu = [Bool](), pie = [Bool](), na = [Bool]()

func queen2() {
    sol = Array(repeating: 0, count: n)
    shu = Array(repeating: false, count: n)
    pie = Array(repeating: false, count: 2*n-1)
    na = Array(repeating: false, count: 2*n-1)
    start = Date()
    DFS(0)
    end = Date()
    timer = end.timeIntervalSince(start)
    print(timer)
    print("Total solutions: \(count)")
}

func DFS(_ row: Int) {
    for col in 0..<n {
        let j = row + col
        let k = n - 1 + col - row
        if shu[col] || pie[j] || na[k] {
            continue
        }
        if row == n-1 {
            count += 1
            print()
        } else {
            shu[col] = true
            pie[j] = true
            na[k] = true
            DFS(row + 1)
            shu[col] = false
            pie[j] = false
            na[k] = false
        }
    }
}
