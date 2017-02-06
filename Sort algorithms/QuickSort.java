public class Main {
    public static void quickSort(int[] nums) {
        quickSortHelper(nums, 0, nums.length - 1);
    }
    
    private static void quickSortHelper(int[] nums, int l, int r) {
        if (l < r) {
            int key = partition(nums, l, r);
            quickSortHelper(nums, l, key - 1);
            quickSortHelper(nums, key + 1, r);
        }
    }
    
    private static int partition(int[] nums, int l, int r) {
        int i = l;
        for (int j = l; j < r; j++) {
            if (nums[j] <= nums[r]) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, r);
        return i;
    }
    
    private static void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j]) return;
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
    
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        quickSort(arr);
        for (int n : arr) System.out.println(n);
    }
}
