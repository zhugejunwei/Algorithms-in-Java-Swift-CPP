public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int eleNum = nums.length;
        int setNum = 1 << eleNum;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < setNum; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < eleNum; j++) {
                if (((i >> j) & 1) != 0) {
                    list.add(nums[j]);
                }
            }
            res.add(list);
        }
        return res;
    }
}
