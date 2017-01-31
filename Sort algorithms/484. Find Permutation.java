// we only need to reverse 'D' substrings
public class Solution {
    public int[] findPermutation(String s) {
        int n = s.length() + 1;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = i + 1;
        for (int i = 0; i < n-1; i++) {
            int j = i;
            while (i < n-1 && s.charAt(i) == 'D') i++;
            reverse(res, j, i);
        }
        return res;
    }
    private void reverse(int[] res, int i, int j) {
        while (i < j) {
            res[i] ^= res[j];
            res[j] ^= res[i];
            res[i++] ^= res[j--];
        }
    }
}

// not a good solution. 
public class Solution {
    public int[] findPermutation(String s) {
        int n = s.length() + 1;
        int[] res = new int[n];
        
        int i = 0, preMax = 0, idx = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            int j = i, count = 0;
            while (j < s.length() && c == s.charAt(j)) {
                j++; count++;
            }
            if (c == 'D') {
                for (int k = count + preMax + 1; k >= preMax + 1; k--)
                    res[idx++] = k;
                preMax = preMax + count + 1;
            } else {
                for (int k = preMax + 1; k < preMax + count + (preMax == 0 ? 1 : 0); k++)
                    res[idx++] = k;
                preMax = preMax + count - (preMax == 0 ? 0 : 1);
            }
            i = j;
            if (c == 'I' && i >= s.length()) res[idx] = preMax + 1;
        }
        
        return res;
    }
}
