// 1st Version
func twoLargeIntMultiplication(x: Int, _ y: Int)
{
    var x = x, y = y
    if x > y {
        swap(&x, &y)
    }
    var arrX = [Int](), arrY = [Int]()
    var result = 0
    while x != 0
    {
        arrX.append(x)
        x = x/2
        arrY.append(y)
        y = y * 2
    }
    for j in 0..<arrX.count {
        if arrX[j] % 2 == 1 {
            result = result + arrY[j]
        }
    }
    print(result)
}


// 2nd Version
func twoLargeIntMultiplication(x: Int, y: Int)
{
    var x = x, y = y
    var arrX = [Int](), arrY = [Int]()
    while x != 0 {
        let temp = x % 10
        arrX.append(temp)
        x /= 10
    }
    while y != 0 {
        let temp = y % 10
        arrY.append(temp)
        y /= 10
    }
    var arr = Array(count: arrY.count, repeatedValue: Array(count: arrX.count, repeatedValue: 0))
    var j = arrY.endIndex.predecessor()
    var m = 0
    while j >= 0 {
        var i = arrX.endIndex.predecessor()
        var n = 0
        while i >= 0 {
            arr[m][n] = arrY[j] * arrX[i]
            i -= 1
            n += 1
        }
        j -= 1
        m += 1
    }
    var res = [Int]()
    var carry = 0
    var i = arrY.count - 1
    while i >= 0 {
        var m = i
        var n = arrX.count - 1
        var down = 1
        var tmp = 0
        while m <= arrY.count - 1 && n >= 0 {
            if down == 1 {
                tmp += arr[m][n] % 10
                m += 1
                down *= -1
            } else {
                tmp += arr[m][n] / 10
                n -= 1
                down *= -1
            }
        }
        tmp += carry
        carry = tmp / 10
        tmp = tmp % 10
        res.append(tmp)
        i -= 1
    }
    j = arrX.count - 1
    while j >= 0 {
        var m = 0
        var n = j
        var tmp = 0
        var up = 1
        while m <= arrY.count - 1 && n >= 0 {
            if up == 1 {
                tmp += arr[m][n] / 10
                n -= 1
                up *= -1
            } else {
                tmp += arr[m][n] % 10
                m += 1
                up *= -1
            }
        }
        tmp += carry
        carry = tmp / 10
        tmp = tmp % 10
        res.append(tmp)
        j -= 1
    }
    var out = 0
    i = res.count - 1
    while i >= 0 {
        out = out * 10 + res[i]
        i -= 1
    }
}