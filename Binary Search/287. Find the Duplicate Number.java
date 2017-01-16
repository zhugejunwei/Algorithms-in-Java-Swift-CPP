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
        int n = nums.length - 1, l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            n >>>= 1;
            int countLeft = 0;
            for (int num : nums) {
                if (num <= mid) countLeft++;
            }
            if (countLeft <= mid) { // means there are no extra elements at the left side
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
