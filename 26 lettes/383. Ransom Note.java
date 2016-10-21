public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() == 0) return true;
        if (magazine.length() == 0) return false;
        int[] arr = new int[26];
        char[] r = ransomNote.toCharArray();
        char[] m = magazine.toCharArray();
        for (int i = 0; i < m.length; i++) {
            arr[m[i] - 'a']++;
        }
        for (int i = 0; i < r.length; i++) {
            --arr[r[i] - 'a'];
            if (arr[r[i] - 'a'] < 0) return false;
        }
        return true;
    }
}
