// recursion 1
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        double t = myPow(x, n / 2);
        if ((n & 1) == 1) { // must use & instead of %, because n maybe < 0
            return n > 0 ? x*t*t : 1/x*t*t;
        } else { // x^2
            return t*t;
        }
    }
}

// recursion 2, better than 3
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 2) return x * x;
        if ((n & 1) == 1) return n > 0 ? x * myPow(myPow(x, n/2), 2) : 1/x * myPow(myPow(x, n/2), 2);
        else return myPow(myPow(x, n/2), 2);
    }
}

// recursion 3
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (n < 0) return 1/x * myPow(1/x, -(n + 1));
        if (n==2) return x*x;
        if ((n&1) == 0) return myPow(myPow(x, n/2), 2);
        else return x * myPow(myPow(x, n/2), 2);
    }
}


// iteration 1
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (n < 0) {
            n = -(++n);
            x = 1 / x;
        } else
            n--;
        double result = x;
        while (n > 0) {
            double y = x;
            for (int i = 1; i > 0 && i <= n; i *= 2) {
                result *= y;
                y *= y;
                n -= i;
            }
        }
        return result;
    }
}

// iteration 2
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (n < 0) {
            n = -(++n);
            x = 1/x;
        } else n--;
        
        double res = x;
        
        while (n > 0) {
            if ((n & 1) == 1) res *= x;
            x *= x;
            n >>>= 1;
        }
        return res;
    }
}
