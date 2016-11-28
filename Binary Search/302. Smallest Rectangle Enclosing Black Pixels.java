public class Solution {
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int rowMin = binarySearch(image, 0, x, 0, n, false, true); //lower, upper, min, max, horizontal, golower
        int rowMax = binarySearch(image, x + 1, m, 0, n, false, false);
        int colMin = binarySearch(image, 0, y, rowMin, rowMax, true, true);
        int colMax = binarySearch(image, y + 1, n, rowMin, rowMax, true, false);
        return (colMax - colMin) * (rowMax - rowMin);
    }
    
    private int binarySearch(char[][] image, int lower, int upper, int min, int max, boolean horizontal, boolean golower) {
        while (lower < upper) {
            int mid = lower + (upper - lower)/2;
            boolean inside = false;
            for (int i = min; i < max; i++) {
                if ((horizontal ? image[i][mid] : image[mid][i]) == '1') {
                    inside = true;
                    break;
                }
            }
            if (inside == golower) {
                upper = mid;
            } else {
                lower = mid + 1;
            }
        }
        return lower;
    }
}
