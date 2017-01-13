/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        int n = airplanes.size();
        List<Point> list = new ArrayList(n * 2);
        for (Interval it : airplanes) {
            list.add(new Point(it.start, 1));
            list.add(new Point(it.end, 0));
        }
        
        Collections.sort(list, new TimeComarator());
        
        int count = 0, res = 0;
        for (Point p : list) {
            if (p.state == 1) count++;
            else count--;
            if (count > res) res = count;
        }
        
        return res;
    }
    
    class Point {
        int time, state;
        public Point(int time, int state) {
            this.time = time;
            this.state = state;
        }
    }
    
    class TimeComarator implements Comparator<Point> {
        @Override
        public int compare(Point a, Point b) {
            return a.time == b.time ? a.state - b.state : a.time - b.time;
        }
    }
}
