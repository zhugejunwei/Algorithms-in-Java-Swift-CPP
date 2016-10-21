public class Solution {
    public String toHex(int num) {
        String res = new String();
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if (num == 0) return "0";
        while (num != 0) {
            res = map[num&15] + res;
            num = (num >>> 4);
        }
        return res;
    }
}
