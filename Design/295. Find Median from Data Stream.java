public class MedianFinder {
    private Queue<Long> large = new PriorityQueue(), small = new PriorityQueue();
    
    public void addNum(int num) {
        large.add((long) num);
        small.add(-large.poll());
        if (small.size() > large.size()) {
            large.add(-small.poll());
        }
    }
    
    
    // Returns the median of current data stream
    public double findMedian() {
        return large.size() > small.size() ? large.peek() : (large.peek() - small.peek()) / 2.0;
    }
};
