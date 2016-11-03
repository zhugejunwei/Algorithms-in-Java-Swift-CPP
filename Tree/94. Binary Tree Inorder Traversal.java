// =====================  Recursion ==================//

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }
    
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}


// =====================  Iteration ==================//

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }
}

// =====================  O(n) space ==================//

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                TreeNode pre = node.left;
                while (pre.right != null && pre.right != node) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = node;
                    node = node.left;
                } else {
                    pre.right = null;
                    list.add(node.val);
                    node = node.right;
                }
            } else {
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }
}
