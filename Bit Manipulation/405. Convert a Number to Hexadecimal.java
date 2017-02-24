public class Solution {
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String res = "";
        while (num != 0) {
            res = map[num & 15] + res;
            num >>>= 4;
        }
        return res;
    }
}

//
public class Solution {
    public String toHex(int num) {
        char[] map = {'a', 'b', 'c', 'd', 'e', 'f'}; // map[i - 10]
        if (num == 0) return "0";
        String res = "";
        while (num != 0) {
            int v = num & 0b1111;
            if (v >= 10) res = map[v-10] + res;
            else res = v + res;
            num >>>= 4;
        }
        return res;
    }
}
