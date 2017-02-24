public class Solution {
    Map<Integer, Integer> freqMap = new HashMap(); // sum:freq
    int max = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[0];
        calSum(root);
        List<Integer> res = new ArrayList();
        for (int key : freqMap.keySet()) {
            if (freqMap.get(key) == max) {
                res.add(key);
            }
        }
        return res.stream().mapToInt(i->i).toArray();
    }
    
    private int calSum(TreeNode root) {
        if (root == null) return 0;
        int s = calSum(root.left) + calSum(root.right) + root.val;
        freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
        if (freqMap.get(s) > max) max = freqMap.get(s);
        return s;
    }
}
