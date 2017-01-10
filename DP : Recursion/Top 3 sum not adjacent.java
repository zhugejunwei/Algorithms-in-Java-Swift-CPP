// dp
public static int minimumCoins(int[] coins, int total) {
    int n = coins.length;
    int[] count = new int[total + 1];
    int[] coin = new int[total + 1];
    count[0] = 0;
    
    for (int i = 1; i <= total; i++) {
        count[i] = Integer.MAX_VALUE - 1;
        coin[i] = -1;
    }
    
    for (int i = 0; i < n; i++) {
        for (int j = 1; j <= total; j++) {
            if (coins[i] > j) continue;
            if (count[j] > count[j - coins[i]] + 1) {
                count[j] = count[j - coins[i]] + 1;
                coin[j] = i;
            }
        }
    }
    return count[total];
}

// recursion + memorization

public static int minimumCoins(int[] coins, int total) {
    if (coins.length == 0 || total == 0) return 0;
    Map<Integer, Integer> map = new HashMap();
    return mini(coins, total, map);
}
private static int mini(int[] coins, int target, Map<Integer, Integer> map) {
    if (target == 0) return 0;
    
    if (map.containsKey(target))
        return map.get(target);
    
    int min = Integer.MAX_VALUE - 1;
    for (int i = 0; i < coins.length; i++) {
        if (coins[i] > target) continue;
        int cur = mini(coins, target - coins[i], map);
        
        if (cur < min) {
            min = cur;
        }
    }
    map.put(target, min += 1);
    return min;
}
