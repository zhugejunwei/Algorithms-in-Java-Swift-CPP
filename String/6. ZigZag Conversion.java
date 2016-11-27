public class Solution {
    public String convert(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
        int i = 0;
        while (i < len) {
            for (int j = 0; j < nRows && i < len; j++) {
                sb[j].append(c[i++]);
            }
            for (int j = nRows-2; j >= 1 && i < len; j--) {
                sb[j].append(c[i++]);
            }
        }
        for (int k = 1; k < nRows; k++) {
            sb[0].append(sb[k]);
        }
        return sb[0].toString();
    }
}
