public class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int p = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = p + res[i + j + 1];
                
                res[i + j] += sum/10;
                res[i + j + 1] = sum%10;
            }
        }
        int i = 0;
        while (i < m + n && res[i] == 0) i++;
        StringBuilder sb = new StringBuilder();
        while (i < m + n) sb.append(res[i++]);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
