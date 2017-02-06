// 欧几里得，辗转相除法
// Euclidean algorithm

/* 
 time complexity:
 辗转相除法处理大数时非常高效，如果用除法而不是减法实现，它需要的步骤不会超过较小数的位数（十进制下）的五倍。拉梅于1844年证明了这点，同时这也标志着计算复杂性理论的开端。
 O(5n), n is the number of digits of the smaller number in a and b
 */
public class Main {
    public int GCD(int a, int b) {
        if (a % b == 0 || b % a == 0) return a > b ? b : a;
        if (a > b) {
            return GCD(a%b, b);
        } else {
            return GCD(a,b%a);
        }
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.GCD(319, 377));
    }
}

// 九章算术，更相减损法
public static int getGCD(int a, int b) {
    if (a == b) return a;
    if (a > b) return getGCD(a - b, b);
    else return getGCD(a, b - a);
}
