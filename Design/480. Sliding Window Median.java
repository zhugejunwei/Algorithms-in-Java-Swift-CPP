public class Solution {
    PriorityQueue<Double> minHeap = new PriorityQueue(), // median -> large
    maxHeap = new PriorityQueue(Collections.reverseOrder());  // median -> small
    // minHeap.size() >= maxHeap.size()
    double median = 0;
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length - k + 1 <= 0) return new double[0];
        int n = nums.length;
        double[] res = new double[n - k + 1];
        for (int i = 0; i <= n; i++) {
            if (i >= k) {
                median = getMedian();
                res[i - k] = median; //
                remove((double)nums[i - k]); //
            }
            if (i < n)
                add((double)nums[i]);
        }
        return res;
    }
    
    private double getMedian() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) return 0;
        if (minHeap.size() == maxHeap.size()) return (minHeap.peek() + maxHeap.peek())/2.0;
        else return minHeap.peek();
    }
    
    private void add(double val) {
        (val < getMedian() ? maxHeap : minHeap).add(val);
        resizeHeap();
    }
    
    private void remove(double val) {
        (val >= median ? minHeap : maxHeap).remove(val);
        resizeHeap();
    }
    
    private void resizeHeap() {
        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
    }
}
