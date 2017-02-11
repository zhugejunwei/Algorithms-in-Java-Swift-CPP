public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        PriorityQueue<Tuple> q = new PriorityQueue<>();
        List<int[]> list = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) return list;
        int bound = n < k ? n : k;
        for (int j = 0; j < bound; j++) q.add(new Tuple(0, j, nums1[0] + nums2[j]));
        for (int i = 0; i < Math.min(k, m*n); i++) {
            Tuple t = q.poll();
            list.add(new int[]{nums1[t.x], nums2[t.y]});
            if (t.x == m - 1) continue;
            q.offer(new Tuple(t.x + 1, t.y, nums1[t.x + 1] + nums2[t.y]));
        }
        return list;
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, sum;
    public Tuple(int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }
    
    @Override
    public int compareTo(Tuple that) {
        return this.sum - that.sum;
    }
}

//===============================  100% ==================================//

public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0 || nums2.length == 0) return new ArrayList<int[]>();
        if (k > (nums1.length * nums2.length)) k = nums1.length * nums2.length;
        List<int[]> result = new ArrayList<>(k);
        int[] secondIndex = new int[nums1.length]; // init with all 0
        int index = 0;
        int minIndex = 0, min = 0, i = 0;
        while(k-- > 0)
        {
            if(secondIndex[index]>=nums2.length) index++;//if num2 has been fully travered for secondIndex[index] move on to next
            minIndex = index; // let' start with index
            /*
             for each value to nums1 get index of nums2 with last best index+1
             and consider it as minimum for the time being
             */
            min = nums1[index] + nums2[secondIndex[index]];
            i = index;
            /*
             if there is a possibility to check the next index and secondIndex is anything but 0
             it means there is a possibility of having even a better min value
             thus we increament i for all the next values in secondIndex where secondIndex[i] is not 0
             and its sum produces lesser than current min value
             
             if such a case occurs, replace minIndex with this index
             */
            while (i < secondIndex.length - 1 && secondIndex[i] != 0){
                i++;
                if (min > nums1[i] + nums2[secondIndex[i]]) {
                    min = nums1[i] + nums2[secondIndex[i]];
                    minIndex = i;
                }
            }
            result.add(new int[]{nums1[minIndex], nums2[secondIndex[minIndex]]});
            secondIndex[minIndex]++;//never use the same (minindex, secondIndex[minIndex]) pairs again :)
        }
        return result;
    }
}
