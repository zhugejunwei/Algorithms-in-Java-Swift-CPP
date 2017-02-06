public class Solution {
    // two conditions to be TRUE
    //  1. sum of area should be equal to the area calculated by 4 corner points
    //  2. all points except 4 corner points should count even, and there should only be ONE of each 4 corner points
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0) return false;
        
        Set<String> set = new HashSet();
        
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        
        int area = 0;
        for (int[] rect : rectangles) {
            minX = Math.min(minX, rect[0]);
            minY = Math.min(minY, rect[1]);
            maxX = Math.max(maxX, rect[2]);
            maxY = Math.max(maxY, rect[3]);
            
            String s1 = rect[0] + "," + rect[1], s2 = rect[0] + "," + rect[3];
            String s3 = rect[2] + "," + rect[1], s4 = rect[2] + "," + rect[3];
            
            // condition 2. all points except 4 corner points should have "even" count
            if (!set.add(s1)) set.remove(s1);
            if (!set.add(s2)) set.remove(s2);
            if (!set.add(s3)) set.remove(s3);
            if (!set.add(s4)) set.remove(s4);
            
            // sum of areas
            area += (rect[3] - rect[1])*(rect[2] - rect[0]);
        }
        
        // condition 2. there should be only one of each 4 corner points
        if (!set.contains(minX + "," + minY) || !set.contains(minX + "," + maxY)
            || !set.contains(maxX + "," + minY) || !set.contains(maxX + "," + maxY)
            || set.size() != 4)
            return false;
        
        // conditions 1. area should be equal
        return area == (maxY - minY)*(maxX - minX);
    }
}
