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
