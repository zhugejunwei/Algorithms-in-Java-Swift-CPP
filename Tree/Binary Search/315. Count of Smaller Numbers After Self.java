public class Solution {
    class TreeNode {
        int count = 1;
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList();
        int len = nums.length;
        if (len == 0 || nums == null) return res;
        TreeNode root = new TreeNode(nums[len - 1]);
        res.add(0);
        for (int i = len - 2; i >= 0; i--) {
            int count = insert(root, nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }
    
    private int insert(TreeNode root, int val) {
        int count = 0;
        while (true) {
            if (val <= root.val) {
                root.count++;
                if (root.left != null) {
                    root = root.left;
                } else {
                    root.left = new TreeNode(val);
                    break;
                }
            } else {
                count += root.count;
                if (root.right != null) {
                    root = root.right;
                } else {
                    root.right = new TreeNode(val);
                    break;
                }
            }
        }
        return count;
    }
}
