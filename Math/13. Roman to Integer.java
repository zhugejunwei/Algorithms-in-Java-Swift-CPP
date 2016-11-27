public class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> romanValue = new HashMap();
        romanValue.put('I', 1);
        romanValue.put('V', 5);
        romanValue.put('X', 10);
        romanValue.put('L', 50);
        romanValue.put('C', 100);
        romanValue.put('D', 500);
        romanValue.put('M', 1000);
        int sum = 0, preValue = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer value = romanValue.get(s.charAt(i));
            if (value <= preValue) {
                sum += value;
            } else {
                sum = sum + value - 2 * preValue;
            }
            preValue = value;
        }
        return sum;
    }
}
