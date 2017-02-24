public class Solution {
    public boolean detectCapitalUse(String word) {
        if (word.length() <= 1) return true;
        int n = word.length();
        if (isLow(word.charAt(n - 1))) {
            for (int i = 1; i < n; i++)
                if (isUp(word.charAt(i)))
                    return false;
            return true;
        }
        for (int i = 0; i < n; i++)
            if (isLow(word.charAt(i)))
                return false;
        return true;
    }
    
    private boolean isLow(char c) {
        return c >= 'a' && c <= 'z';
    }
    
    private boolean isUp(char c) {
        return c >= 'A' && c <= 'Z';
    }
}

// more concise solution
public class Solution {
    public boolean detectCapitalUse(String word) {
        int count = 0;
        for (char c : word.toCharArray())
            if ('Z' - c >= 0) count++;
        return count == 0 || (count == 1 && 'Z' - word.charAt(0) >= 0) || count == word.length();
    }
}
