// same as 493. Reverse Pairs
public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList();
        List<Integer> res = new ArrayList();
        res.add(0);
        Node root = new Node(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i--) {
            buildTree(res, root, nums[i]);
        }
        Collections.reverse(res);
        return res;
    }
    
    private void buildTree(List<Integer> res, Node node, int target) {
        int count = 0;
        while (true) {
            if (target > node.val) {
                count += node.count;
                if (node.right == null) {
                    node.right = new Node(target);
                    break;
                }
                node = node.right;
            } else {
                node.count++;
                if (node.left == null) {
                    node.left = new Node(target);
                    break;
                }
                node = node.left;
            }
        }
        res.add(count);
    }
    
    class Node {
        int val;
        int count = 1;
        Node left, right;
        public Node(int val) {
            this.val = val;
        }
    }
}

// below is slower, because list.add(idx, val) is O(n) time
public class Solution {
    class Node {
        int count = 1;
        int val;
        Node left, right;
        public Node(int val) {
            this.val = val;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList();
        Node root = new Node(nums[nums.length - 1]);
        List<Integer> res = new ArrayList();
        res.add(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            boolean insert = false;
            Node node = root;
            int count = 0;
            while (!insert) {
                if (nums[i] > node.val) {
                    count += node.count;
                    if (node.right == null) {
                        node.right = new Node(nums[i]);
                        insert = true;
                    } else node = node.right;
                } else {
                    node.count++;
                    if (node.left == null) {
                        node.left = new Node(nums[i]);
                        insert = true;
                    } else node = node.left;
                }
            }
            res.add(0, count);
        }
        return res;
    }
}
