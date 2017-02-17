public class PhoneDirectory {
    Set<Integer> vis;
    Queue<Integer> avail;
    int max;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        this.max = maxNumbers;
        vis = new HashSet();
        avail = new ArrayDeque();
        for (int i = 0; i < maxNumbers; i++) avail.offer(i);
    }
    
    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        Integer n = avail.poll();
        if (n == null) return -1;
        vis.add(n);
        return n;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if (number >= max || number < 0) return false;
        return !vis.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (vis.remove(number)) avail.offer(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
