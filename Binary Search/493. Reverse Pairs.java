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

// using merge sort, faster and shorter !!!
public class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }
    private int mergeSort(int[] nums, int l, int r) {
        if (l >= r) return 0;
        int mid = l + (r - l)/2;
        int count = mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);
        int[] cache = new int[r - l + 1];
        int i = l, t = l, c = 0;
        for (int j = mid + 1; j <= r; j++, c++) {
            while (i <= mid && nums[i] <= 2 * (long)nums[j]) i++;
            while (t <= mid && nums[t] < nums[j]) cache[c++] = nums[t++];
            cache[c] = nums[j];
            count += mid - i + 1;
        }
        while (t <= mid) cache[c++] = nums[t++];
        System.arraycopy(cache, 0, nums, l, r - l + 1);
        return count;
    }
}


// LintCode Reverse Pairs, i < j, A[i] > A[j]
public class Solution {
    /**
     * @param A an array
     * @return total of reverse pairs
     */
    int count = 0;
    public long reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }
    
    private void mergeSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l)/2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }
    
    private void merge(int[] nums, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        int[] left = new int[n1], right = new int[n2];
        for (int i = 0; i < n1; i++) left[i] = nums[i + l];
        for (int i = 0; i < n2; i++) right[i] = nums[i + m + 1];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                count += n1 - i;
                nums[k++] = right[j++];
            }
        }
        while (i < n1) nums[k++] = left[i++];
        while (j < n2) nums[k++] = right[j++];
    }
}
