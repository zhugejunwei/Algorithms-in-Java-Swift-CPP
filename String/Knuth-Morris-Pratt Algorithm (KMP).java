// KMP:
// String text and pattern, find the pattern in the given text.

public class Main
{
    public static int[] build_failure_function(char[] pattern) {
        int m = pattern.length;
        int[] F = new int[m + 1];
        F[0] = F[1] = 0;
        for (int i = 2; i <= m; i++) {
            // j is the index of the largest next partial match
            // (the largest suffix/prefix) of the string under
            // index i - 1
            int j = F[i - 1];
            for (;;) {
                // check to see if the last character of string i -
                // - pattern[i - 1] "expands" the current "candidate"
                // best partial match - the prefix under index j
                if (pattern[j] == pattern[i - 1]) {
                    F[i] = j + 1;
                    break;
                }
                // if we cannot "expand" even the empty string
                if (j == 0) {
                    F[i] = 0;
                    break;
                }
                // else go to the next best "candidate" partial match
                j = F[j];
            }
        }
        return F;
    }
    
    public static String Knuth_Morris_Pratt(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        String res = "";
        // let n be the size of the text, m the
        // size of the pattern, and F[] - the
        // "failure function"
        int[] F = build_failure_function(pattern.toCharArray());
        
        int i = 0; // the initial state of the automaton is
        // the empty string
        
        int j = 0; // the first character of the text
        
        for (;;) {
            if (j == n) break; // we reached the end of the text
            // if the current character of the text "expands" the
            // current match
            if (text.charAt(j) == pattern.charAt(i)) {
                i++;
                j++;
                if (i == m) {
                    int l = j - 1, r = j;
                    while (l >= 0 && text.charAt(l) != ' ') l--;
                    while (r < n && text.charAt(r) != ' ') r++;
                    return text.substring(l + 1, r);
                }
            }
            // if the current state is not zero (we have not
            // reached the empty string yet) we try to
            // "expand" the next best (largest) match
            else if(i > 0) i = F[i];
            
            // if we reached the empty string and failed to
            // "expand" even it; we go to the next
            // character from the text, the state of the
            // automaton remains zero
            else j++;
        }
        return "";
    }
    
    public static void main(String[] args) {
        String text = "hello world";
        String pattern = "ell";
        String res = Knuth_Morris_Pratt(text, pattern);
        System.out.println(res); // "hello"
    }
}
