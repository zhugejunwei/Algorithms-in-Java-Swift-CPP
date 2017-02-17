// leetcode 186. Reverse Words in a String II
public class Main {
    // Reverse Words in a String II
    // follow up, 有标点，如何保持标点的位置
    // "This, is a - test" -> "test, a is - This"
    public static String reverseWordsWithMark(String s) {
        Deque<String> s1 = new ArrayDeque();
        Deque<String> s2 = new ArrayDeque();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            StringBuilder tmp = new StringBuilder();
            while (i < arr.length && isLetter(arr, i)) {
                tmp.append(arr[i++]);
            }
            if (tmp.length() > 0) s1.push(tmp.toString());
        }
        
        for (int i = arr.length - 1; i >= 0; i--) {
            StringBuilder tmp = new StringBuilder();
            while (i >= 0 && !isLetter(arr, i)) {
                tmp.insert(0, arr[i--]);
            }
            if (tmp.length() > 0) s2.push(tmp.toString());
        }
        StringBuilder res = new StringBuilder();
        res.append(s1.pop());
        while (!s1.isEmpty() && !s2.isEmpty()) {
            res.append(s2.pop()).append(s1.pop());
        }
        return res.toString();
    }
    
    private static boolean isLetter(char[] arr, int i) {
        return (arr[i] >= 'a' && arr[i] <= 'z') || (arr[i] >= 'A' && arr[i] <= 'Z');
    }
    
    
    public static void main(String[] args) {
        String s = "This, is a - test";
        System.out.println(reverseWordsWithMark(s));
    }
}
