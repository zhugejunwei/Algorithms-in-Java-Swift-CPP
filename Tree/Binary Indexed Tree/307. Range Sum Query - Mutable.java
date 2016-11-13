public class NumArray {
    int[] tree;
    int[] nums;
    int n;
    // parent: i ^= (i&-i);
    // child: i += (i&-i);
    public NumArray(int[] nums) {
        if (nums.length == 0 || nums == null) return;
        this.nums = nums;
        n = nums.length;
        tree = new int[n + 1];
        for (int i = 1; i<= n; i++) {
            init(i, nums[i-1]);
        }
    }
    
    void init(int i, int val) {
        while(i <= n) {
            tree[i] += val;
            i += (i & -i);
        }
    }
    
    void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        init(i + 1, diff);
    }
    
    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1); // i is inclusive
    }
    
    private int getSum(int i) {
        int sum = 0;
        i++;
        while(i > 0) {
            sum += tree[i];
            i ^= (i & -i);
        }
        return sum;
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
