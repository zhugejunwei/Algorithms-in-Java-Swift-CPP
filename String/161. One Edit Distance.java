// one edit distance away
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

// one edit distance away
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) return false;
        String s1 = s.length() > t.length() ? s : t;
        String s2 = s.length() > t.length() ? t : s;
        int idx1 = 0, idx2 = 0;
        boolean foundDif = false;
        // s1 is longer, s2 shorter
        while (idx1 < s1.length() && idx2 < s2.length()) {
            if (s1.charAt(idx1) != s2.charAt(idx2)) {
                if (foundDif) return false;
                foundDif = true;
                
                if (s.length() == t.length()) idx2++; // if replace, move shorter pointer
            } else idx2++; // if match, move shorter pointer
            idx1++; // always move longer pointer
        }
        return foundDif  || (!foundDif && s.length() != t.length());
    }
}


// one edit or "zero" edit distance away
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) return false;
        String s1 = s.length() > t.length() ? s : t;
        String s2 = s.length() > t.length() ? t : s;
        int idx1 = 0, idx2 = 0;
        boolean foundDif = false;
        // s1 is longer, s2 shorter
        while (idx1 < s1.length() && idx2 < s2.length()) {
            if (s1.charAt(idx1) != s2.charAt(idx2)) {
                if (foundDif) return false;
                foundDif = true;
                
                if (s.length() == t.length()) idx2++; // if replace, move shorter pointer
            } else idx2++; // if match, move shorter pointer
            idx1++; // always move longer pointer
        }
        return true;
    }
}
