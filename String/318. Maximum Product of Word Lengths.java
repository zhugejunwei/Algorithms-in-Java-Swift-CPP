public class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        if (n < 2) return 0;
        int max = 0;
        int[] bitmask = new int[n];
        int index = 0;
        for (String w : words) {
            for (int j = 0; j < w.length(); j++) {
                bitmask[index] |= 1 << (w.charAt(j) - 'a'); // record a string
            }
            index++;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if ((bitmask[i] & bitmask[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
