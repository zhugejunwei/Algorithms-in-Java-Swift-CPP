public class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = {1,2,4,8}, nums2 = {1,2,4,8,16,32};
        for (int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for (int n : list1) {
                if (n >= 12) continue;
                for (int m : list2) {
                    if (m >= 60) continue;
                    res.add(n + ":" + (m < 10 ? ("0" + m) : m));
                }
            }
        }
        return res;
    }
    
    public List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }
    
    public void generateDigitHelper(int[] nums, int count, int start, int sum, List<Integer> res) {
        if (count == 0) {
            res.add(sum);
            return;
        }
        for (int s = start; s < nums.length; s++) {
            generateDigitHelper(nums, count - 1, s + 1, sum + nums[s], res);
        }
    }
}


// shorter version
public class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount((h << 6) | m) == num) {
                    res.add(h + ":" + ((m < 10) ? ("0" + m) : m));
                }
            }
        }
        return res;
    }
}
