/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> leftNodes = new Stack();
        TreeNode cur = root;
        while (cur != null) {
            list.addFirst(cur.val);
            if (cur.left != null) {
                leftNodes.push(cur.left);
            }
            cur = cur.right;
            if (cur == null && !leftNodes.empty()) {
                cur = leftNodes.pop();
            }
        }
        return list;
    }
}

// better solution
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        
        Deque<TreeNode> stack = new ArrayDeque();
        
        TreeNode cur = root, visitedRight = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && peekNode.right != visitedRight) { // can go right
                    cur = peekNode.right;
                } else {
                    res.add(peekNode.val);
                    stack.pop();
                    visitedRight = peekNode;
                }
            }
        }
        return res;
    }
}
