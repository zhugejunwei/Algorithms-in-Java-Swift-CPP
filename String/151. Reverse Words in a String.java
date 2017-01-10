public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (!arr[i].isEmpty()) sb.append(arr[i]).append(" ");
        }
        if (sb.length() > 1) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
