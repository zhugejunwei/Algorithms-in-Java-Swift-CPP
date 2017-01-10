// iteration
public class Codec {
    // serialize
    public String serialize(TreeNode root) {
        if (root == null) return "";
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        TreeNode node = root;
        q.add(node);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur != null) {
                sb.append(cur.val).append(",");
                q.offer(cur.left);
                q.offer(cur.right);
            } else {
                sb.append("null,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        while (sb.length() > 4 && sb.substring(sb.length() - 4).toString().equals("null")) {
            sb = sb.delete(sb.length() - 4, sb.length());
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        data = data.substring(1, data.length() - 1);
        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode pre = q.poll();
            if (pre == null) {
                continue;
            }
            if (i < arr.length && !arr[i].equals("null")) {
                pre.left = new TreeNode(Integer.valueOf(arr[i]));
            } else {
                pre.left = null;
            }
            q.offer(pre.left);
            i++;
            if (i < arr.length && !arr[i].equals("null")) {
                pre.right = new TreeNode(Integer.valueOf(arr[i]));
            } else {
                pre.right = null;
            }
            q.offer(pre.right);
            i++;
        }
        return root;
    }
}

/* recursion
 
 there are some mistakes in this solution below:
 
 https://discuss.leetcode.com/topic/28029/easy-to-understand-java-solution/15

 */

public class Codec {
    private static final String spliter = ",";
    private static final String nn = "null";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(nn).append(spliter);
        } else {
            sb.append(root.val).append(spliter);
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.poll();
        if (val == null || val.equals(nn)) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
