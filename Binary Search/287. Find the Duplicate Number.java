// O(n)
public class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (fast != slow) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        
        int head = 0;
        while (head != slow) {
            head = nums[head];
            slow = nums[slow];
        }
        return head;
    }
}

// O(nlogn) binary seach

public class Solution {
    public int findDuplicate(int[] nums) {
        int l = 1, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int countLeft = 0;
            for (int n : nums) {
                if (n <= mid) countLeft++;
            }
            if (countLeft <= mid) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
