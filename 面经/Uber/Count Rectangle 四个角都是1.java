// given a matrix only containing 1 or 0，find how many rectangles are 4个角都是1

public class Main {
    public static int solution(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        
        int res = 0;
        for (int l = 0; l + 1 < n; l++) {
            int[] left = new int[m];
            for (int k = 0; k < m; k++)
                left[k] ^= matrix[k][l];
            
            for (int r = l + 1; r < n; r++) {
                int[] right = Arrays.copyOf(left, m);
                int count = 0;
                for (int k = 0; k < m; k++) {
                    right[k] &= matrix[k][r];
                    if (right[k] == 1) count++;
                }
                res += count * (count - 1)/2;
                System.out.println(l + " " + r + " " + count);
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
            {1,0,1,1},
            {0,1,1,0},
            {1,1,0,0},
            {0,0,1,1},
            {1,1,0,1}};
        System.out.println(solution(matrix));
    }
}
