public class Solution {
    public String rearrangeString(String str, int k) {
        int len = str.length();
        int[] count = new int[128]; // count of each character
        int[] pos = new int[128]; // the valid position of each character
        
        for (char c : str.toCharArray()) count[c]++;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int validChar = findPos(count, pos, i);
            if (validChar == -1) return "";
            count[validChar]--;
            pos[validChar] = i + k; // move this char k steps ahead
            sb.append((char)validChar);
        }
        return sb.toString();
    }
    
    private int findPos(int[] count, int[] pos, int candidatePos) {
        int maxCount = Integer.MIN_VALUE;
        int validChar = -1;
        for (int c = 0; c < 128; c++) {
            // 1. the count of c is largest
            // 2. current position of c is no more than candidatePos
            if (count[c] > 0 && count[c] > maxCount && candidatePos >= pos[c]) {
                maxCount = count[c];
                validChar = c;
            }
        }
        return validChar;
    }
}
