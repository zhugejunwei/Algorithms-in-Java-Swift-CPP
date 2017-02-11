public class Solution {
    public boolean isUgly(int num) {
        while (num > 1) {
            if (num % 2 == 0) num /= 2;
            else if (num % 3 == 0) num /= 3;
            else if (num % 5 == 0) num /= 5;
            else return false;
        }
        return num == 1;
    }
}

// another concise solution
public class Solution {
    public boolean isUgly(int num) {
        for (int i = 2; i < 6 && num > 1; i++) {
            while (num % i == 0) {
                num /= i;
            }
        }
        return num == 1;
    }
}
