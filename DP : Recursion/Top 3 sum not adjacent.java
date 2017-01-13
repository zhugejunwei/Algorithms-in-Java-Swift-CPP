public class Main
{
    // top 3 sum not adjacent
    public static int top3Sum(int[] arr) {
        if (arr == null || arr.length == 0) return Integer.MIN_VALUE;
        long a = arr[0], b = Integer.MIN_VALUE, c = Integer.MIN_VALUE;
        long pre_a = Integer.MIN_VALUE, pre_b = Integer.MIN_VALUE;
        for (int val : arr) {
            c = Math.max(pre_b + val, c);
            pre_b = b;
            b = Math.max(pre_a + val, b);
            pre_a = a;
            a = Math.max(val, a);
        }
        return (int)Math.max(Math.max(a, b), c);
    }
    
    
    public static void main (String[] args) throws java.lang.Exception
    {
        int[] arr = {-2, 0, 0, 1}; // 5, 2, 9 = 16
        System.out.println(top3Sum(arr));
    }
    
}
