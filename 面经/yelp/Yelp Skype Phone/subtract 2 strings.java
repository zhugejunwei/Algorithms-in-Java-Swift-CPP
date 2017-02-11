public class Main {
    static int sign = 1;
    public static String subtract2Strings(String s1, String s2) {
        if (s1.length() < s2.length()) {
            sign = -1;
            return subtract2Strings(s2, s1);
        }
        String res = "";
        int i = s1.length() - 1, j = s2.length() - 1, carry = 0;
        while (j >= 0) {
            int n1 = s1.charAt(i--) - '0', n2 = s2.charAt(j--) - '0';
            int sub = n1 - carry - n2;
            if (n1 < n2) {
                carry = 1;
                sub = n1 + 10 - n2;
            } else carry = 0;
            res += sub;
        }
        while (i >= 0) {
            int sub = carry > 0 ? s1.charAt(i--) - '0' - carry : s1.charAt(i--) - '0';
            carry--;
            res = sub + res;
        }
        i = 0;
        while (i < res.length() - 1 && res.charAt(i) == '0') i++;
        res = res.substring(i);
        return sign == -1 ? "-" + res : res;
    }
    
    public static void main(String[] args) {
        System.out.println(subtract2Strings("0", "123"));
    }
}
