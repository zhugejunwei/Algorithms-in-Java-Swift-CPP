// use normal iteration way
public class Solution {
    public int poorPigs(int buckets, int m, int p) {
        int t = p/m + 1;
        // t^x >= buckets ==> x
        // <=> log(buckets) base t
        int x = 0;
        while (Math.pow(t, x) < buckets) x++;
        return x;
    }
}

// use log
public class Solution {
    public int poorPigs(int buckets, int m, int p) {
        int t = p/m + 1;
        return (int)Math.ceil(Math.log(buckets)/Math.log(t));
    }
}
