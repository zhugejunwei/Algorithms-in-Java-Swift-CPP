// ==========================  00 -> 01 -> 10 -> 00 ========================== //

public class Solution {
    // 0 ^ 1 = 0 + 1 = 1
    // 1 ^ 1 = 1 + 1 = 0
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            // count 1s
            a = a ^ (b & nums[i]); // only b and nums[i] == 1, a += 1;
            b = b ^ nums[i]; // b += 1;
            
            // convert 0b11 -> 0b00
            int c = a & b;
            a = a & ~c;
            b = b & ~c;
        }
        return a|b;
    }
}

// ========================== every number appears 3 times, k = 3 ======================== //

public class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }
}

// ===================== every number appears 5 times, k = 5 ======================== //

// generaic way

public class Solution {
    static int singleNumber(int nums[], int n) {
        int a = 0, b = 0, c = 0;
        for (int i : nums) {
            c ^= b & a & i;
            b ^= a & i;
            a ^= i;
            int mask = ~(a & ~b & c); // mask = 5
            a &= mask;
            b &= mask;
            c &= mask;
        }
        return a;
    }
}

// 000 -> 001 -> 010 -> 011 -> 100

public class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < nums.length; i++) {
            b = b ^ (c & nums[i]);
            c = (c ^ nums[i]) & ~a;
            a = a ^ (nums[i] & ~b & ~c);
        }
        return c & ~a & ~b;
    }
}

// or 011 -> 100 -> 101 -> 110 -> 111

public class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0xffffffff, c = 0xffffffff;
        for (int i = 0; i < nums.length; i++) {
            c = c ^ (nums[i] & b);
            b = (b ^ nums[i]) & ~a;
            a = a ^ (nums[i] & ~b & ~c);
        }
        return a;
    }
}



// ========================== slow ========================== //
// O(32N) ~> O(N)
public class Solution {
    // count 1s at every position in 32bit array
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((nums[j] >> i) & 1) == 1) // read the ith 1
                    count++;
            }
            if (count % 3 != 0) res |= (1 << i);
        }
        return res;
    }
}

// k = 2, p = 1
