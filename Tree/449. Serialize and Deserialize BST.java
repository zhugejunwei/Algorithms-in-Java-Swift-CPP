/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    private static final String SEP = ",";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            sb.append(cur.val).append(SEP);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null;
        String[] str = data.split(SEP);
        Queue<Integer> q = new ArrayDeque();
        for (String s : str) {
            q.offer(Integer.valueOf(s));
        }
        return helper(q);
    }
    
    private TreeNode helper(Queue<Integer> q) {
        if (q.isEmpty()) return null;
        int rootVal = q.poll();
        TreeNode root = new TreeNode(rootVal);
        Queue<Integer> leftPart = new ArrayDeque();
        while (!q.isEmpty() && q.peek() < rootVal) {
            leftPart.offer(q.poll());
        }
        root.left = helper(leftPart);
        root.right = helper(q);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
