/* 来源
https://discuss.leetcode.com/topic/65158/c-0ms-o-n-35-lines-solution-with-detailed-explanation
*/
public class Solution {
    public int strongPasswordChecker(String s) {
        int deleteTarget = Math.max(0, s.length() - 20), addTarget = Math.max(0, 6 - s.length());
        int toDelete = 0, toAdd = 0, toReplace = 0, nlow = 1, nup = 1, ndig = 1;
        
        for (int l = 0, r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (c >= 'A' && c <= 'Z') nup = 0;
            if (c >= 'a' && c <= 'z') nlow = 0;
            if (c >= '0' && c <= '9') ndig = 0;
            
            if (r - l == 2) {
                if (s.charAt(l) == s.charAt(l + 1) && s.charAt(l + 1) == s.charAt(r)) {
                    if (toAdd < addTarget) {
                        toAdd++; l = r;
                    } else {
                        toReplace++; l = r + 1;
                    }
                } else l++;
            }
        }
        
        if (s.length() <= 20) return Math.max(addTarget + toReplace, nup + nlow + ndig);
        
        toReplace = 0;
        Map<Integer, Integer>[] lenCnts = new Map[3];
        for (int i = 0; i < 3; i++) lenCnts[i] = new HashMap();
        for (int l = 0, r = 0, len; r <= s.length(); r++) {
            if (r == s.length() || s.charAt(l) != s.charAt(r)) {
                if ((len = r - l) > 2) {
                    Map<Integer, Integer> tmp = lenCnts[len % 3];
                    tmp.put(len, tmp.getOrDefault(len, 0) + 1);
                }
                l = r;
            }
        }
        
        for (int i = 0, numLetters, dec; i < 3; i++) {
            for (Map.Entry<Integer, Integer> entry : lenCnts[i].entrySet()) {
                if (i < 2) {
                    numLetters = i + 1;
                    dec = Math.min(entry.getValue(), (deleteTarget - toDelete) / numLetters);
                    toDelete += dec * numLetters; 
                    entry.setValue(entry.getValue() - dec);
                }
                toReplace += (entry.getValue() * (entry.getKey() / 3));
            }
        }
        
        int dec = (deleteTarget - toDelete) / 3;
        toReplace -= dec;
        toDelete -= dec * 3;
        return deleteTarget + Math.max(toReplace, nup + nlow + ndig);
    }
}