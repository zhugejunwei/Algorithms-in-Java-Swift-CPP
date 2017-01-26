public class Solution {
    public String shortestPalindrome(String s) {
        String ss = s + "+" + new StringBuilder(s).reverse().toString();
        int[] table = new int[ss.length()];
        int j = 0;
        for (int i = 1; i < ss.length(); i++) {
            if (ss.charAt(j) == ss.charAt(i)) {
                table[i] = j + 1;
                j++;
            } else {
                while (j > 0 && ss.charAt(j) != ss.charAt(i)) {
                    j = table[j - 1];
                }
                if (ss.charAt(j) == ss.charAt(i)) {
                    j++;
                }
                table[i] = j;
            }
        }
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }
}
