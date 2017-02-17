public class Solution {
    double median = 0;
    PriorityQueue<Double> minHeap = new PriorityQueue(), // peek is smallest
    maxHeap = new PriorityQueue(Collections.reverseOrder()); // peek is largest
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length - k + 1 <= 0) return new double[0];
        int n = nums.length;
        double[] res = new double[n - k + 1];
        for (int i = 0; i < n; i++) {
            add((double)nums[i]);
            if (i + 1 >= k) {
                res[i + 1 - k] = getMedian();
                median = res[i + 1 - k];
                remove((double)nums[i + 1 - k]);
            }
        }
        return res;
    }
    
    private void add(double val) {
        minHeap.add(val);
        maxHeap.add(minHeap.poll());
        resizeHeap();
    }
    
    private double getMedian() {
        if (minHeap.size() == maxHeap.size()) return (minHeap.peek() + maxHeap.peek()) / 2.0;
        return minHeap.peek();
    }
    
    private void remove(double val) {
        (val < median ? maxHeap : minHeap).remove(val);
        resizeHeap();
    }
    
    // final status: minHeap.size() >= maxHeap.size
    private void resizeHeap() {
        while (minHeap.size() < maxHeap.size())
            minHeap.add(maxHeap.poll());
        
        // if remove one element from minHeap, it may happen
        if (minHeap.size() - maxHeap.size() > 1)
            maxHeap.add(minHeap.poll());
    }
}
