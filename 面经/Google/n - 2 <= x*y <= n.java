public class Main {
    // 给1个non-negative的integer n, 找出x和y, 使得 n-2 <= x*y <= n,
    // 并且让(x-y)的绝对值最小, 假设n=29, 那x=7, y=4
    public static int[] solu(int n) {
        int x = (int)Math.sqrt(n), y = x;
        for (int i = 0; i < n; i++) {
            y = x + i;
            if (x * y < n - 2) {
                x++; y++;
            } else if (x * y > n) {
                x--; y--;
            }
            if (x * y >= n - 2 && x * y <= n) {
                return new int[]{x, y};
            }
        }
        return new int[]{x, y};
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        int[] res = solu(44559); // 7, 4
        System.out.println(res[0] + " " + res[1]);
    }
}
