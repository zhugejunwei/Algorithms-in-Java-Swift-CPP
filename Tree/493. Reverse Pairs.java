// same as "315 count smaller numbers after self"
public class Solution {
    int count = 0;
    public int reversePairs(int[] nums) {
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            search(root, nums[i]/2.0);
            root = insert(root, nums[i]);
        }
        return count;
    }
    
    private void search(Node node, double target) {
        if (node == null) return;
        if
            (target < node.val) search(node.left, target);
        else if
            (target == node.val) count += node.less;
        else {
            count += node.same + node.less;
            search(node.right, target);
        }
    }
    
    private Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        else if (val > node.val)
            node.right = insert(node.right, val);
        else if
            (val == node.val) node.same++;
        else {
            node.less++;
            node.left = insert(node.left, val);
        }
        return node;
    }
    
    class Node {
        int val;
        int less = 0;
        int same = 1;
        Node left, right;
        public Node(int val) {
            this.val = val;
        }
    }
}
