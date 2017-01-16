// https://leetcode.com/articles/maximum-gap/
public class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int n = nums.length;
        int minEle = Integer.MAX_VALUE, maxEle = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > maxEle) maxEle = num;
            if (num < minEle) minEle = num;
        }
        
        // bucket size
        int bucketSize = Math.max(1, (maxEle - minEle) / (n - 1));
        // bucket number
        int bucketNum = (maxEle - minEle) / bucketSize + 1;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i = 0; i < buckets.length; i++)
            buckets[i] = new Bucket();
        
        for (int num : nums) {
            int bucketIdx = (num - minEle) / bucketSize;
            buckets[bucketIdx].used = true;
            buckets[bucketIdx].minVal = Math.min(num, buckets[bucketIdx].minVal);
            buckets[bucketIdx].maxVal = Math.max(num, buckets[bucketIdx].maxVal);
        }
        
        int preMax = minEle, maxGap = 0;
        for (Bucket b : buckets) {
            if (!b.used) continue;
            maxGap = Math.max(maxGap, b.minVal - preMax);
            preMax = b.maxVal;
        }
        
        return maxGap;
    }
    
    class Bucket {
        boolean used = false;
        int minVal;
        int maxVal;
        public Bucket() {
            this.minVal = Integer.MAX_VALUE;
            this.maxVal = Integer.MIN_VALUE;
        }
    }
}
