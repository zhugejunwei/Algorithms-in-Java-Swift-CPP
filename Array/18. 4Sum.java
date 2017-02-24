// 4 sum
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int l = j + 1, r = nums.length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum > target) {
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        List<Integer> list = new ArrayList(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        res.add(list);
                        while (l + 1 < r && nums[l + 1] == nums[l]) l++;
                        while (l < r - 1 && nums[r - 1] == nums[r]) r--;
                        l++; r--;
                    }
                }
            }
        }
        return res;
    }
}


// N sum
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, 4, 0, target);
    }
    
    private List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        if (n == 2) return twoSum(nums, start, target);
        List<List<Integer>> res = new ArrayList();
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue;
            List<List<Integer>> tmp = nSum(nums, n - 1, i + 1, target - nums[i]);
            for (List<Integer> list : tmp) {
                list.add(nums[i]);
            }
            res.addAll(tmp);
        }
        return res;
    }
    
    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> res = new ArrayList();
        int l = start, r = nums.length - 1;
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum < target) {
                l++;
            } else if (sum > target) {
                r--;
            } else {
                List<Integer> tmp = new ArrayList(Arrays.asList(nums[l], nums[r]));
                res.add(tmp);
                while (l + 1 < r && nums[l] == nums[l + 1]) l++;
                while (l < r - 1 && nums[r] == nums[r - 1]) r--;
                l++; r--;
            }
        }
        return res;
    }
}
