public class Solution {
    public int maxProduct(String[] words) {
        int res = 0;
        int[] mask = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            char[] arr = words[i].toCharArray();
            for (int j = 0; j < arr.length; j++) {
                mask[i] |= (1 << arr[j]);
            }
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((mask[i] & mask[j]) == 0) { // no overlap
                    int mult = words[i].length() * words[j].length();
                    if (mult > res)
                        res = mult;
                }
            }
        }
        return res;
    }
}
