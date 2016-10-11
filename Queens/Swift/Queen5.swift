import UIKit

var n = 15
var count = 0

var start = Date(), end = Date(), timer:Double = 0.0

var shu = 0, pie = 0, na = 0

func queen5() {
    start = Date()
    DFS(0, 0, 0, 0)
    end = Date()
    timer = end.timeIntervalSince(start)
    print(timer)
    print("Total solutions: \(count)")
}

func DFS(_ row: Int, _ shu: Int, _ pie: Int, _ na: Int) {
    var ok = ((1 << n) - 1) & ~(shu | pie | na)
    while ok != 0 {
        let p = ok & -ok
        ok ^= p
        if row == n - 1 {
            count += 1
        } else {
            DFS(row + 1, shu ^ p, (pie ^ p) >> 1, (na ^ p) << 1)
        }
    }
}
