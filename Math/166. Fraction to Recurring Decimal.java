public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);
        int sign = (numerator > 0) ^ (denominator > 0) ? -1 : 1;
        
        StringBuilder sb = new StringBuilder();
        // integral part
        sb.append(sign == -1 ? "-" : "").append(n / d);
        n %= d;
        if (n == 0) return sb.toString();
        
        sb.append(".");
        Map<Long, Integer> map = new HashMap();
        map.put(n, sb.length());
        
        while (n != 0) {
            n *= 10;
            sb.append(n / d);
            n %= d;
            if (map.containsKey(n)) {
                sb.insert(map.get(n), "(");
                sb.append(")");
                break;
            } else {
                map.put(n, sb.length());
            }
        }
        return sb.toString();
    }
}
