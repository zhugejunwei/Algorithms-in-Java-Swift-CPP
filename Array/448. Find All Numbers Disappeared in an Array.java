public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && nums[-nums[i] - 1] > 0) {
                nums[-nums[i] - 1] = -nums[-nums[i] - 1];
            }
            else if (nums[i] > 0 && nums[nums[i] - 1] > 0) {
                nums[nums[i] - 1] = -nums[nums[i] - 1];
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) res.add(i + 1);
        }
        return res;
    }
}
