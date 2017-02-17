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
        
        Collections.sort(list, new TimeComparator());
        
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
    
    class TimeComparator implements Comparator<Point> {
        @Override
        public int compare(Point a, Point b) {
            return a.time == b.time ? a.state - b.state : a.time - b.time;
        }
    }
}

// greedy solution
class Solution {
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) return 0;
        Collections.sort(airplanes, new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.start == b.start? a.end - b.end : a.start - b.start;
            }
        });
        PriorityQueue<Integer> q = new PriorityQueue();
        
        int mostAirplanes = 0;
        for (int i = 0; i < airplanes.size(); i++) {
            while (!q.isEmpty() && q.peek() <= airplanes.get(i).start) {
                q.poll();
            }
            q.offer(airplanes.get(i).end);
            mostAirplanes = Math.max(mostAirplanes, q.size());
        }
        
        return mostAirplanes;
    }
}
