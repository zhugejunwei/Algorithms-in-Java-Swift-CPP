public class Solution {
    public int splitArray(int[] nums, int m) {
        int l = 0, r = 0; // l = largest valie in nums; r = sum of all value in nums
        for (int n : nums) {
            if (n > l) l = n;
            r += n;
        }
        
        while (l < r) {
            int mid = (l & r) + ((l ^ r) >>> 1);
            if (noLargerThanM(nums, mid, m)) {
                // if the number of subarrays is not larger than m, mid could be smaller
                r = mid;
            } else {
                // if the number of subarrays is larger than m, mid should be larger.
                l = mid + 1;
            }
        }
        return l;
    }
    
    public boolean noLargerThanM(int[] nums, int target, int m) {
        int sum = 0, count = 1;
        for (int n : nums) {
            // here it is actually not from left to right, the order doesn't matter, it is easy to prove:
            // if a + b > t, c + d > t
            // then there is no way to put a + b + c + d in one array, that is to say,
            // it could be a + b + c > t, d > t
            // or a > t, b + c + d > t
            // it doesn't matter, the number of subarrays is still 2, which is equal to a+b>t, and c+d > t
            sum += n;
            if (sum > target) {
                // find a way to form subarray, as said before, it doesn't matter how to form the subarray
                // the number matters, which will not change according to the ways to form a subarray.
                sum = n;
                count++;
            }
            if (count > m) return false; // if count == m, we still have a chance to make mid smaller
        }
        return true;
    }
}

/*
依照例子，建二维数组6 by 2,第一行全为0，第二行开始第一列存累加到此行的和，
 第二列存到此行的最小和，也就是最后一行的第二列将是返回值，
 最小和就是对比当前值与依次考虑包含与不包含之前的每一个数的的和的最大值，存入较小的
*/

