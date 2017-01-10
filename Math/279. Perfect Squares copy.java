public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        sb.append(((numerator > 0) ^ (denominator > 0) ? "-" : ""));
        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);
        
        // integral part
        sb.append(n / d);
        n %= d;
        if (n == 0) return sb.toString();
        
        // fractional part
        sb.append(".");
        Map<Long, Integer> map = new HashMap();
        map.put(n, sb.length());
        while (n != 0) {
            n *= 10;
            sb.append(n / d);
            n %= d;
            if (map.containsKey(n)) {
                int idx = map.get(n);
                sb.insert(idx, "(");
                sb.append(")");
                break;
            } else {
                map.put(n, sb.length());
            }
        }
        return sb.toString();
    }
}
