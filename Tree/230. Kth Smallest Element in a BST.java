// recursion
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left) + 1;
        if (count > k) {
            return kthSmallest(root.left, k);
        } else if (count < k) {
            return kthSmallest(root.right, k - count);
        }
        return root.val;
    }
    
    private int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}

// iteration
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> q = new ArrayDeque();
        TreeNode t = root;
        while (t != null || !q.isEmpty()) {
            if (t != null) {
                q.push(t);
                t = t.left;
            } else {
                TreeNode node = q.pop();
                k--;
                if (k == 0) return node.val;
                t = node.right;
            }
        }
        return -1;
    }
}
