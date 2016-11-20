// iteration solution
public class Solution {
    public int lastRemaining(int n) {
        int head = 1;
        int step = 1;
        boolean left = true;
        int remain = n;
        while (remain > 1) {
            if (left || remain % 2 == 1) {
                head += step;
            }
            step *= 2;
            left = !left;
            remain /= 2;
        }
        return head;
    }
}

// recursion solution 1
/*
 Explanation:
 
 lastRemaining(n) + EliminateFromRightFirst(n) = n + 1;
 <=> lastRemaining(n/2) + EliminateFromRightFirst(n/2) = n/2 + 1;
 <=> EliminateFromRightFirst(n/2) = 1 + n/2 - lastRemaining(n/2);
 */
public class Solution {
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (1 + n/2 - lastRemaining(n / 2));
    }
}


// recursion solution 2
public class Solution {
    public int lastRemaining(int n) {
        return fromLeft(n);
    }
    private int fromLeft(int n) {
        if (n <= 2) return n;
        return 2 * fromRight(n/2);
    }
    private int fromRight(int n) {
        if (n <= 2) return 1;
        if (n % 2 == 1) return 2 * fromLeft(n/2);
        return 2 * fromLeft(n/2) - 1;
    }
}
