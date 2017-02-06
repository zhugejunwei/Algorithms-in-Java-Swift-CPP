// iteration
public class Main {
    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int n = nums.length;
        for (int len = 1; len < n; len *= 2) {
            for (int l = 0; l < n - 1; l += 2*len) {
                // m is end point of left array, m + 1 is starting point of right array
                int m = l + len - 1;
                int r = Math.min(m + len, n - 1);
                merge(nums, l, m, r);
            }
        }
    }
    //
    // merge nums[l...m] and nums[m+1...r]
    private static void merge(int[] nums, int l , int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        int[] left = new int[n1], right = new int[n2];
        for (int i = 0; i < n1; i++) left[i] = nums[i + l];
        for (int j = 0; j < n2; j++) right[j] = nums[j + m + 1];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }
        while (i < n1) nums[k++] = left[i++];
        while (j < n2) nums[k++] = right[j++];
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        mergeSort(arr);
        for (int n : arr) System.out.println(n);
    }
}

// recursion
public class Main {
    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        mergeSortHelper(nums, 0, nums.length - 1);
    }
    
    private static void mergeSortHelper(int[] nums, int l, int r) {
        if (l < r) {
            int m = l + (r - l)/2;
            mergeSortHelper(nums, l, m);
            mergeSortHelper(nums, m + 1, r);
            merge(nums, l, m, r);
        }
    }
    
    // merge nums[l...m] and nums[m+1...r]
    private static void merge(int[] nums, int l , int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        int[] left = new int[n1], right = new int[n2];
        for (int i = 0; i < n1; i++) left[i] = nums[i + l];
        for (int j = 0; j < n2; j++) right[j] = nums[j + m + 1];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                nums[k++] = left[i++];
            } else {
                nums[k++] = right[j++];
            }
        }
        while (i < n1) nums[k++] = left[i++];
        while (j < n2) nums[k++] = right[j++];
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        mergeSort(arr);
        for (int n : arr) System.out.println(n);
    }
}
