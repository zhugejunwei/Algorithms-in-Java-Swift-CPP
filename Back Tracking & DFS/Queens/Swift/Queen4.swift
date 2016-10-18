import UIKit

var n = 15
var count = 0

var start = Date(), end = Date(), timer:Double = 0.0

var shu = 0, pie = 0, na = 0

func queen4() {
    start = Date()
    DFS(0)
    end = Date()
    timer = end.timeIntervalSince(start)
    print(timer)
    print("Total solutions: \(count)")
}


func DFS(_ row: Int) {
    // pie 0～2n－2 位
    // 不能放，或起来，取反
    var ok = ((1 << n) - 1) & (~(shu | (pie >> row) | (na >> (n - 1 - row))))
    // ((1 << n) - 1)  ＝> 0～n－1全是1，其他都是0
    while ok != 0 {
        let p = ok & -ok
        ok ^= p
        if row == n - 1 {
            count += 1
        } else {
            shu ^= p; pie ^= (p << row); na ^= (p << (n - 1 - row));
            DFS(row + 1)
            shu ^= p; pie ^= (p << row); na ^= (p << (n - 1 - row));
        }
    }
}
