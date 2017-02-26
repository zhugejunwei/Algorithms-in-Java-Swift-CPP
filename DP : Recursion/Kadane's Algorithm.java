// max sum subarry

public static int Kadanes(int[] arr) {
    int n = arr.length;
    int res = arr[0], pre = arr[0];
    for (int i = 1; i < n; i++) {
        pre = Math.max(arr[i], arr[i] + pre);
        res = Math.max(pre, res);
    }
    return res;
}

// max sum no larger than K

public static int Kadanes(int[] arr, int k) {
    TreeSet<Integer> set = new TreeSet<>();
    set.add(0);
    int res = 0, sum = 0;
    for (int n : arr) {
        sum += n;
        Integer ceiling = set.ceiling(sum - k);
        if (ceiling != null) res = Math.max(res, sum - ceiling);
        set.add(sum);
    }
    return res;
}
