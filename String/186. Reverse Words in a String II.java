public class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) return;
        reverse(s, 0, s.length - 1);
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') continue;
            int j = i;
            while (j < s.length && s[j] != ' ') j++;
            reverse(s, i, --j);
            i = ++j;
        }
    }
    
    private void reverse(char[] s, int i, int j) {
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++; j--;
        }
    }
}
