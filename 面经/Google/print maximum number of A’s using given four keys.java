// recursion
public class Main {
    public static int printMaximumAs(int n) {
        if (n <= 6) return n;
        
        int max = 0;
        
        for (int a = n-3; a >= 1; a--) { // number of a's printed directly
            // number of v's
            int v = n - a - 2;
            int count = (v + 1) * printMaximumAs(a);
            if (count > max) max = count;
        }
        return max;
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        for (int i = 1; i <= 20; i++) {
            System.out.println(printMaximumAs(i));
        }
    }
}

// recursion + memo
public class Main {
    static Map<Integer, Integer> map = new HashMap<>();
    public static int printMaximumAs(int n) {
        if (n <= 6) return n;
        if (map.containsKey(n)) return map.get(n);
        int max = 0;
        
        for (int a = n-3; a >= 1; a--) { // number of a's printed directly
            // number of v's
            int v = n - a - 2;
            int count = (v + 1) * printMaximumAs(a);
            if (count > max) max = count;
        }
        map.put(n, max);
        return max;
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        for (int i = 1; i <= 20; i++) {
            System.out.println(printMaximumAs(i));
        }
    }
}

// DP ~= recursion + memo
public class Main {
    public static int printMaximumAs(int n) {
        if (n <= 6) return n;
        
        int[] dp = new int[n + 1];
        for (int i = 1; i <= 6; i++)
            dp[i] = i;
        
        for (int k = 7; k <= n; k++) {
            for (int a = n - 3; a >= 1; a--) { // number of a's printed directly
                // number of v's
                int v = n - a - 2;
                int count = (v + 1) * printMaximumAs(a);
                if (count > dp[k]) dp[k] = count;
            }
        }
        return dp[n];
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        for (int i = 1; i <= 20; i++) {
            System.out.println(printMaximumAs(i));
        }
    }
}
