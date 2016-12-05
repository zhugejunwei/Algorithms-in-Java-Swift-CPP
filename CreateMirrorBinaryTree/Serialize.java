package io.kongming;

import java.util.LinkedList;

/**
 * Created by zhugejunwei on 12/4/16.
 */
public class Serialize {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode t = queue.poll();
            if(t!=null){
                sb.append(String.valueOf(t.val) + ",");
                queue.add(t.left);
                queue.add(t.right);
            }else{
                sb.append("null,");
            }
        }

        sb.deleteCharAt(sb.length()-1);
        while (sb.length() > 4 && sb.substring(sb.length() - 4).toString().equals("null")) {
            sb = sb.delete(sb.length() - 4, sb.length());
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
