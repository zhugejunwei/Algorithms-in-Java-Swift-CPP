package io.kongming;

import java.util.LinkedList;

/**
 * Created by zhugejunwei on 12/4/16.
 */
public class CreateMirror {
    public String createMirror(TreeNode root) {
        if (root == null) return "";
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        StringBuilder sb = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t = q.poll();
            if (t != null) {
                sb.append(t.val).append(",");
                q.add(t.right);
                q.add(t.left);
            } else {
                sb.append("null,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        while (sb.length() > 4 && sb.substring(sb.length() - 4).toString().equals("null")) {
            sb = sb.delete(sb.length() - 4, sb.length());
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
