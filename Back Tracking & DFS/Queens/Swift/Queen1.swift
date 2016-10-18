import UIKit

var n = 15
var count = 0
var sol = [Int]()

var start = Date(), end = Date(), timer:Double = 0.0

func queen1() {
    sol = Array(repeating: 0, count: n)
    start = Date()
    DFS(0)
    end = Date()
    timer = end.timeIntervalSince(start)
    print(timer)
    print("Total solutions: \(count)")
}

func DFS(_ row: Int) {
    for col in 0..<n {
        var ok = true
        for i in 0..<row {
            if col == sol[i] || col - sol[i] == row - i || col - sol[i] == i - row {
                ok = false
                break
            }
        }
        if !ok {
            continue
        }
        sol[row] = col
        if row == n-1 {
            count += 1
            print()
        } else {
            DFS(row + 1)
        }
    }
}

queen1()
// "1.47330302000046\n"
