public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, i = 0, total = 0;
        int sum = 0, j = i;
        for (; j < n; j++) {
            sum += gas[j] - cost[j];
            total += gas[j] - cost[j];
            if (sum < 0) {
                i = j + 1;
                sum = 0;
            }
        }
        return total < 0 ? -1 : i;
    }
}
