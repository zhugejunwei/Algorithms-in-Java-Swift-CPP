/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        
        Collections.sort(intervals, (a, b) -> (a.start - b.start));
        
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        List<Interval> res = new ArrayList();
        for (Interval in : intervals) {
            if (in.start <= end) {
                end = Math.max(end, in.end);
            } else {
                res.add(new Interval(start, end));
                start = in.start;
                end = in.end;
            }
        }
        res.add(new Interval(start, end)); // the last one
        return res;
    }
}

// 

public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start == b.start ? b.end - a.end : a.start - b.start;
            }
        });
        List<Interval> res = new ArrayList();
        int len = intervals.size(), i = 0;
        while (i < len) {
            Interval tmp = intervals.get(i++);
            while (i < len && tmp.end >= intervals.get(i).start) {
                tmp.start = Math.min(tmp.start, intervals.get(i).start);
                tmp.end = Math.max(tmp.end, intervals.get(i).end);
                i++;
            }
            res.add(tmp);
        }
        return res;
    }
}
