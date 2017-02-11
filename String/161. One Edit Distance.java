public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length())
                    // their length are the same
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() - t.length() == 1)
                    // s is one char longer
                    return s.substring(i + 1).equals(t.substring(i));
                else if (t.length() - s.length() == 1)
                    // t is one char longer
                    return s.substring(i).equals(t.substring(i + 1));
                else return false; // more than one char longer
            }
        }
        return Math.abs(s.length() - t.length()) == 1;
    }
}
