import UIKit

var n = 15
var count = 0

var start = Date(), end = Date(), timer:Double = 0.0

var shu = 0, pie = 0, na = 0

func queen3() {
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
        if (((shu >> col) | (pie >> j) | (na >> k)) & 1) != 0 {
            continue
        }
        if row == n-1 {
            count += 1
        } else {
            shu ^= (1 << col); pie ^= (1 << j); na ^= (1 << k);
            DFS(row + 1)
            shu ^= (1 << col); pie ^= (1 << j); na ^= (1 << k);
        }
    }
}
