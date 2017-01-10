// better solution
public class Solution {
    public String minAbbreviation(String target, String[] dictionary) {
        for (int len = 1; len < target.length(); len++) {
            List<String> abbrs = new ArrayList();
            generateAbbrs(abbrs, target, "", len, 0, 0, 0); // totalLen of the abbr
            // check those abbrs is valid or not
            for (String abbr : abbrs) {
                if (isValid(target, abbr, dictionary)) {
                    return abbr;
                }
            }
        }
        return target; // don't need to check the full word
    }
    
    private void generateAbbrs(List<String> res, String s, String tmp, int len, int i, int tmpLen, int count) {
        if (tmpLen > len || tmpLen == len && i < s.length()) return;
        if (i == s.length()) {
            if (count != 0) tmp += count;
            res.add(tmp);
            return;
        }
        // abbr
        generateAbbrs(res, s, tmp, len, i + 1, tmpLen, count + 1);
        // not
        generateAbbrs(res, s, tmp + (count > 0 ? count : "") + s.charAt(i), len, i + 1, tmpLen + (count > 0 ? 1 : 0) + 1, 0);
    }
    
    private boolean isValid(String target, String t, String[] dictionary) {
        for (String dic : dictionary) {
            if (target.length() != dic.length()) continue;
            // if they are equal length, then only one possibility of their abbrs are same,
            // is that i == t.length() and j == dic.length();
            int i = 0, j = 0;
            while (i < t.length() && j < dic.length()) {
                if (t.charAt(i) >= 'a' && t.charAt(i) <= 'z') {
                    if (t.charAt(i) == dic.charAt(j)) {
                        i++;
                        j++;
                    } else { // if they are not equal
                        break; // just break, and test another word
                    }
                }
                // if it is a number
                int val = 0;
                while (i < t.length() && t.charAt(i) >= '0' && t.charAt(i) <= '9') {
                    val = val * 10 + t.charAt(i++) - '0';
                }
                if (val > 0) {
                    j += val; // dic skip val steps to new pos = (j + step)
                }
            }
            if (i == t.length() && j == dic.length()) return false; // if they are equal, it's not valid
        }
        return true;
    }
}


// not a good solution
public class Solution {
    public String minAbbreviation(String target, String[] dictionary) {
        StringBuilder bestAbbr = new StringBuilder(target);
        generateAbbr(target, bestAbbr, new StringBuilder(), 0, 0, dictionary);
        if (bestAbbr.length() < target.length()) return bestAbbr.toString();
        return target;
    }
    
    private void generateAbbr(String target, StringBuilder bestAbbr, StringBuilder sb, int count, int i, String[] dictionary) {
        int len = sb.length();
        if (i == target.length()) {
            if (count == 1) return; // no need to abbr one letter
            if (count > 0) sb.append(count);
            String abbr = sb.toString();
            boolean isValid = !hasConflict(target.length(), abbr, dictionary);
            if (isValid && abbr.length() < bestAbbr.length()) {
                bestAbbr.setLength(0);
                bestAbbr.append(sb); // cannot directly set bestAbbr equal to sb.
            }
        } else {
            // abbr target[i]
            generateAbbr(target, bestAbbr, sb, count + 1, i + 1, dictionary);
            // not abbr target[i]
            if (count != 0) sb.append(count);
            generateAbbr(target, bestAbbr, sb.append(target.charAt(i)), 0, i + 1, dictionary);
        }
        sb.setLength(len);
    }
    
    private boolean hasConflict(int targetLen, String abbr, String[] dict) {
        for (String dic:dict) {
            if (targetLen != dic.length()) continue; // skip the words in dictionary with unequal length
            boolean canBeAbbrTo = canBeAbbrTo(dic.toCharArray(), abbr.toCharArray());
            if(canBeAbbrTo) return true;
        }
        return false;
    }
    
    private boolean canBeAbbrTo(char[] word, char[] abbr) {
        int i = 0, j = 0;
        while (i < word.length && j < abbr.length) {
            if (word[i] == abbr[j]) {
                i++; j++;
                continue;
            }
            if (abbr[j] <= '0' || abbr[j] > '9') return false;
            int count = 0;
            while(j < abbr.length && (abbr[j] >= '0' && abbr[j] <= '9')) {
                count = count * 10 + abbr[j] - '0';
                j++;
            }
            i += count;
        }
        return i == word.length && j == abbr.length;
    }
}
