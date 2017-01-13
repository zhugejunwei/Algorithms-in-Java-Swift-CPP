public class Solution {
    public int magicalString(int n) {
        if (n == 0) return 0;
        if (n <= 3) return 1;
        
        int[] arr = new int[n + 1];
        arr[0] = 1; arr[1] = 2; arr[2] = 2;
        
        int head = 2, tail = 3, val = 1, count = 1;
        while (tail < n) {
            if (val == 1) count += arr[head];
            for (int i = 0; i < arr[head]; i++) {
                arr[tail++] = val;
            }
            head++;
            val ^= 3;
        }
        if (arr[n] == 1) count--;
        return count;
    }
}
