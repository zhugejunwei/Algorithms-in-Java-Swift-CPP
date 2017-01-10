import Darwin

// 1. Rectangle Multiplication algorithm (Lettuce Multiplication)
func rectangleMultiplication(_ x: [Int], _ y: [Int]) -> [Int]
{
    var arrX = x, arrY = y
    // Edge cases: if x < 0 or y < 0, or x == 0 or y == 0
    var tag = 1 // a tag if the result is positive or negative
    if arrX[0] < 0 {
        arrX[0] = -arrX[0]
        tag = -tag
    } else if arrX[0] == 0 {
        return [0]
    }
    if arrY[0] < 0 {
        arrY[0] = -arrY[0]
        tag = -tag
    } else if arrY[0] == 0 {
        return [0]
    }
    // the two dimensional rectangle array
    var arr = Array(repeating: Array(repeating: 0, count: arrX.count), count: arrY.count)
    
    var j = arrY.startIndex
    // calculate the rectangle (two dimensional array)
    while j < arrY.endIndex {
        var i = arrX.startIndex
        while i < arrX.endIndex {
            arr[j][i] = arrY[j] * arrX[i]
            i += 1
        }
        j += 1
    }
    
    var res = [Int]()
    var carry = 0
    var i = arrY.count - 1
    // add up the values in the rectangle, from bottom to top
    while i >= 0 {
        var m = i
        var n = arrX.count - 1
        var down = 1 // a mark that the value need to be added is the down side in a cell
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
    
    // add up the values in the rectangle, from right to left
    j = arrX.count - 1
    while j >= 0 {
        var m = 0;
        var n = j
        var tmp = 0
        var up = 1 // a mark that the value need to be added is the up side in a cell
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
        // calculate the last element of the result
        if j == 0 && tmp == 0 && carry == 0 {
            break
        } else if j == 0 && tmp == 0 && carry != 0 {
            // there is a carry left
            res.append(tmp)
            res.append(carry)
        } else {
            res.append(tmp)
        }
        j -= 1
    }
    // reverse the res[]
    for k in 0..<res.count/2 {
        swap(&res[k], &res[res.count-k-1])
    }
    res[0] = tag * res[0]
    return res
}



// 2. Ala Carte Multiplication algorithm

// function of two arrays' addition
func addition(_ x: [Int], _ y: [Int]) -> [Int] {
    var i = x.endIndex - 1, j = y.endIndex - 1
    var res = [Int]() // result array
    var carry = 0
    var tmp = 0
    // add up two arrays from the last element
    while i >= 0 && j >= 0 {
        tmp = x[i] + y[j] + carry
        res.append(tmp % 10)
        carry = tmp / 10
        if i == 0 && j == 0 && tmp >= 10 {
            res.append(carry)
        }
        i -= 1
        j -= 1
    }
    // y has elements left
    if i == -1 && j >= 0 {
        while j >= 0 {
            tmp = y[j] + carry
            res.append(tmp % 10)
            carry = tmp / 10
            j -= 1
            if j == 0 && tmp >= 10 {
                res.append(carry)
            }
        }
        // x has elements left
    } else if j == -1 && i >= 0 {
        while i >= 0 {
            tmp = x[i] + carry
            res.append(tmp % 10)
            carry = tmp / 10
            if i == 0 && tmp >= 10 {
                res.append(carry)
            }
            i -= 1
        }
    }
    // the elements in res[] is reverse, so we need to reverse the array
    for k in 0..<res.count/2 {
        swap(&res[k], &res[res.count-k-1])
    }
    return res
}

// function of divided, an array by 2
func divide(_ x: inout [Int]) -> [Int] {
    var res = [Int]()
    var carry = 0
    // divided the array by 2 from the first element
    for i in x.startIndex..<x.endIndex {
        let tmp = carry*10 + x[i]
        if tmp / 2 > 0 {
            res.append(tmp / 2)
            carry = tmp % 2
        } else {
            if i != x.startIndex {
                res.append(0)
            }
            carry = tmp % 2
            if i == x.endIndex - 1 {
                res.append(carry / 2)
            }
        }
    }
    return res
}

// function of multiplication, an array times 2
func multiply(_ y: inout [Int]) -> [Int] {
    var res = [Int]()
    var carry = 0
    var i = y.endIndex - 1
    // multiply the array by 2 from the last element
    while i >= y.startIndex {
        let tmp = carry + y[i] * 2
        res.append(tmp % 10)
        carry = tmp / 10
        if i == y.startIndex && tmp >= 10 {
            res.append(carry)
        }
        i -= 1
    }
    // reverse res[]
    for j in 0..<res.count/2 {
        swap(&res[j], &res[res.count-j-1])
    }
    return res
}

// the multiplication function
func twoLargeIntMultiplication(_ x: [Int], _ y: [Int]) -> [Int]
{
    var x = x, y = y
    
    // make sure x is the smaller one
    if x.count > y.count {
        swap(&x, &y)
    }
    // Edge cases: if x < 0 or y < 0, or x == 0 or y == 0
    var tag = 1 // a tag if the result is positive or negative
    if x[0] < 0 {
        x[0] = -1 * x[0]
        tag = -tag
    } else if x[0] == 0 {
        return [0]
    }
    if y[0] < 0 {
        y[0] = -y[0]
        tag = -tag
    } else if y[0] == 0 {
        return [0]
    }
    
    var arrX = [x], arrY = [y]
    var tmpX = x, tmpY = y
    
    // divide x by 2, and multiply y by 2
    while tmpX != [1] {
        tmpX = divide(&tmpX)
        arrX.append(tmpX)
        tmpY = multiply(&tmpY)
        arrY.append(tmpY)
    }
    var res = [0]
    // add up the arrays in the y side that correspond to x/2 == 1
    for i in arrX.startIndex..<arrX.endIndex {
        if arrX[i].last! & 0b1 == 1 {
            res = addition(res, arrY[i])
        }
    }
    return res
}





// Test Cases

/* 1. Ala Carte Multiplication algorithm
 
 twoLargeIntMultiplication([5,3,4,2,1,3,4,2,3], [1,2,3,4])
 // [6, 5, 9, 2, 1, 9, 3, 6, 3, 9, 8, 2]
 
 twoLargeIntMultiplication([2], [2,3])
 // [4,6]
 
 twoLargeIntMultiplication([-2], [5,7])
 // [-1, 1, 4]
 
 twoLargeIntMultiplication([-1,1], [-1,1])
 // [1, 2, 1]
 twoLargeIntMultiplication([3,2,3], [0])
 // [0]
 
 twoLargeIntMultiplication([0], [-2,3,4,2])
 // [0]
 
 twoLargeIntMultiplication([2,3,9,8,2,8], [1,9,0,2,3,4])
 // [4, 5, 6, 2, 3, 4, 3, 9, 7, 5, 2]
 
 twoLargeIntMultiplication([2,3,6,9,4,2,3,7,8,4,7,2,3,9,4,6,2,3,1,0,2,7,5,7,5,3,8,9,4], [-9,1,0,4,7,8,2,3,9,0,3,4,3,1,2,4,7,2,3,9,4,8,2,2,3,9,4,7,2,3,9,4,7,2,3,3,4,8])
 // takes toooooo much space and time
 */


/* 2. Rectangle Multiplication algorithm
 
 rectangleMultiplication([5,3,4,2,1,3,4,2,3], [1,2,3,4])
 // [6, 5, 9, 2, 1, 9, 3, 6, 3, 9, 8, 2]
 
 rectangleMultiplication([2], [2,3])
 // [4,6]
 
 rectangleMultiplication([-2], [5,7])
 // [-1, 1, 4]
 
 rectangleMultiplication([-1,1], [-1,1])
 // [1, 2, 1]
 
 rectangleMultiplication([3,2,3], [0])
 // [0]
 
 rectangleMultiplication([0], [-2,3,4,2])
 // [0]
 
 rectangleMultiplication([2,3,9,8,2,8], [1,9,0,2,3,4])
 // [4, 5, 6, 2, 3, 4, 3, 9, 7, 5, 2]
 
 rectangleMultiplication([2,3,6,9,4,2,3,7,8,4,7,2,3,9,4,6,2,3,1,0,2,7,5,7,5,3,8,9,4], [-9,1,0,4,7,8,2,3,9,0,3,4,3,1,2,4,7,2,3,9,4,8,2,2,3,9,4,7,2,3,9,4,7,2,3,3,4,8])
 // [-2, 1, 5, 7, 3, 0, 8, 7, 9, 5, 0, 4, 1, 4, 7, 4, 4, 5, 3, 7, 6, 1, 6, 5, 0, 0, 0, 6, 5, 6, 2, 8, 7, 7, 3, 5, 2, 3, 3, 0, 8, 7, 2, 9, 5, 1, 5, 1, 9, 0, 7, 6, 3, 6, 4, 3, 4, 8, 2, 6, 3, 7, 1, 7, 1, 1, 2]
 */
