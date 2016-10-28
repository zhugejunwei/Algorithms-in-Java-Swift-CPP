public class Solution {
    public String getHint(String secret, String guess) {
        int[] count = new int[10];
        int A = 0, B = 0;
        // A
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                A++;
            } else {
                if (count[secret.charAt(i) - '0']++ < 0) B++;
                if (count[guess.charAt(i) - '0']-- > 0) B++;
            }
        }
        return A + "A" + B + "B";
    }
}
