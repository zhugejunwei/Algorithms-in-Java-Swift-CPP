public class Main
{
    int[] st;
    public int segmentTree(int[] arr, int len) {
        int n = arr.length;
        int x = (int)Math.ceil(Math.log(n) / Math.log(2));
        int size = 2 * (int)Math.pow(2, x) - 1;
        st = new int[size];
        constructTree(arr, 0, n - 1, 0);
        int res = 0;
        for (int i = 0; i + len - 1 < n; i++) {
            res = Math.max(res, minQuery(n, i, i + len - 1));
        }
        return res;
    }
    
    private int rangeMin(int l, int r, int qs, int qe, int i) {
        // total overlap
        if (qs <= l && qe >= r) {
            return st[i];
        }
        // no overlap
        if (l > qe || r < qs) {
            return Integer.MAX_VALUE;
        }
        // partial overlap
        int mid = l + (r - l)/2;
        return Math.min(rangeMin(l, mid, qs, qe, 2 * i + 1), rangeMin(mid + 1, r, qs, qe, 2 * i + 2));
    }
    
    private int minQuery(int n, int x, int y) {
        if (x < 0 || y >= n || x > y) return -1;
        return rangeMin(0, n - 1, x, y, 0);
    }
    
    private int constructTree(int[] arr, int l, int h, int i) {
        if (l == h) {
            st[i] = arr[l];
            return arr[l];
        }
        int mid = l + (h - l)/2;
        st[i] = Math.min(constructTree(arr, l, mid, 2 * i + 1), constructTree(arr, mid + 1, h, 2 * i + 2));
        return st[i];
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        int[] arr = {2,4,2,9,6,3,4};
        int res = m.segmentTree(arr, 3);
        System.out.println(res);
    }
}
