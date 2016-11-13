public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String sent = String.join(" ", sentence) + " ";
        int start = 0;
        int l = sent.length();
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (sent.charAt(start % l) == ' ') start++;
            else {
                while (start > 0 && sent.charAt((start - 1) % l) != ' ')
                    start--;
            }
        }
        return start / l;
    }
}
