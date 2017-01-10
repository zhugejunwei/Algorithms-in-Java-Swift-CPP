public class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] arr = new String[nums.length];
        int i = 0;
        for (int n : nums) {
            arr[i++] = String.valueOf(n);
        }
        
        Arrays.sort(arr, new Comparator<String>(){
            public int compare(String a, String b) {
                String s1 = a + b, s2 = b + a;
                return s2.compareTo(s1);
            }
        });
        if (arr[0].charAt(0) == '0') return "0";
        
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str);
        }
        return sb.toString();
    }
}
