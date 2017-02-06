public class Solution {
    public String[] findWords(String[] words) {
        String row1 = "QWERTYUIOP", row2 = "ASDFGHJKL", row3 = "ZXCVBNM";
        List<String> res = new ArrayList();
        for (String word : words) {
            boolean yes = true;
            String w = word.toUpperCase();
            String c = w.charAt(0) + "";
            String row = row1.contains(c) ? row1 : row2.contains(c) ? row2 : row3.contains(c) ? row3 : "";
            for (char x : w.toCharArray()) if (!row.contains(x + "")) yes = false;
            if (yes) res.add(word);
        }
        return res.toArray(new String[res.size()]);
    }
}
