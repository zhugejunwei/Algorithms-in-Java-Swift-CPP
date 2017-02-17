/* 1. Expanding
 Expanding is the easiest way to solve this problem. The idea is simple, we repeat an item as many as the weight value after translation the value into integer. This method is proper for that an item won't be repeat too many time.
 */
class ExpandingWeightRandom {
    ArrayList<Integer> items = new ArrayList<Integer>();
    
    void addItem(int item, int weight) {
        for(int i = 0; i < weight; i++) {
            items.add(item);
        }
    }
    
    void removeItem(int item) {
        int start = -1;
        for(int i = 0; i < items.size(); i++) {
            if (items.get(i) == item){
                start = i;
                break;
            }
        }
        int end = start + 1;
        for (; end < items.size(); end++) {
            if (items.get(i) != item) break;
        }
        items.removeRange(start, end);
    }
    
    int chooseOne() {
        if (items.isEmpty()) return 0;
        
        return items.get(Random.nextInt(items.size()));
    }
}

/* 2. In-place (Unsorted)
 Instead of using an ArrayList, we use a HashMap to keep the item as well as its weight value in record. Each time we generate a random number between zero and the total weight value in storage. Then we iterate through the items, sum up the weight values processed. The iterate should terminated when we have an item that, before added the weight value of it, the sum of processed weight is smaller or equal to the random number, and after that, the sum exceed.

 */
class InPlaceWeightRandom {
    HashMap<Integer, Integer> weightsMap = new HashMap<Integer, Integer>();
    int totalWeight = 0;
    
    void putItem(int item, int weight) {
        if (weightsMap.containsKey(item)) {
            totalWeight -= weightsMap.get(item);
        }
        weightsMap.put(item, weight);
        totalWeight += weight;
    }
    
    void removeItem(int item) {
        if (weightsMap.containsKey(item)) {
            totalWeights -= weightMap.get(item);
            weightsMap.remove(item);
        }
    }
    
    int chooseOne() {
        int sum = 0;
        int random = Random.nextInt(totalWeights + 1);
        for(Map.Entry<Integer, Integer> entry: weightsMap) {// no order
            sum += entry.getValue();
            if (sum > random) return entry.getKey();
        }
        return 0;
    }
}

/* 3. X. Pre-calculated
 The main idea is, we pre-calculate the sum of weights and decide the boundary to choose each item. Each time we'd like to pick an element, we use binary search technique to find and return the element fast.
 */
class PreCalculateWeightsRandom{
    int totalWeight = 0;
    ArrayList<Integer> itemList = new ArrayList<Integer>();
    ArrayList<Integer> weightsList = new ArrayList<Integer>();
    
    void addItem(int item, int weight) {
        itemList.add(item);
        totalWeights += weight;
        weightList.add(totalWeights);
    }
    
    void removeItem(int item) {
        int index = itemList.indexOf(item);
        itemList.remove(index);
        int weight = weightList.get(index);
        weightsList.remove(index);
        for (int i = index; i < weightsList.size(); i++) {
            weightList.get(i) = weightList.get(i) - weight;
        }
    }
    
    int chooseOne() {
        int random = Random.nextInt(totalWeights + 1);
        int index = binarySearchBoundary(weightList, random);
        return itemList.get(index);
    }
    
    int binarySearchBoundary(ArrayList<Integer> list, int value) {
        int l = 0;
        int r = list.size() - 1;
        
        while (l < r) {
            int mid = (l + r) / 2;
            if (list.get(mid) < value) l = mid + 1;
            else r = mid;
        }
        
        return r;
    }
}

/* Weighted Random Sampling with a Reservoir
The second algorithm is even more interesting: a weighted distributed reservoir sample, where every item in the set has an associated weight, and we want to sample such that the probability that an item is selected is proportional to its weight. It wasn’t even clear whether or not this was even possible until Pavlos Efraimidis and Paul Spirakis figured out a way to do it and published it in the 2005 paper “Weighted Random Sampling with a Reservoir.” The solution is as simple as it is elegant, and it is based on the same idea as the distributed reservoir sampling algorithm described above. For each item in the stream, we compute a score as follows: first, generate a random number R between 0 and 1, and then take the nth root of R, where n is the weight of the current item. Return the K items with the highest score as the sample. Items with higher weights will tend to have scores that are closer to 1, and are thus more likely to be picked than items with smaller weights.
 */
public class Main {
    public static int[] weightedReservoirSampling(int[][] items, int m){
        if(items==null || m > items.length){
            System.out.println("incorrect input.");
            return null;
        }
        PriorityQueue<WrsElement> heap = new PriorityQueue<WrsElement>(10);
        /** Transform weight into a double between (0,1). Put it in min heap. **/
        // Math.random() returns a number from zero to one.
        for(int i=0; i < items.length ; i++){
            double key = Math.pow((Math.random()), 1/(double)items[i][1]);
            WrsElement element = new WrsElement(items[i][0], key);
            heap.add(element);
            if(heap.size() > m){
                heap.poll();
            }
        }
        /** Output result. **/
        int[] result = new int[m];
        for(int i=0;i<m; i++){
            result[i] = heap.poll().value;
        }
        return result;
    }
    
    static class WrsElement implements Comparable<WrsElement>{
        
        int value;
        double key;
        
        public WrsElement(int value, double key){
            this.value = value;
            this.key = key;
        }
        
        public int compareTo(WrsElement wrsElement) {
            return Double.compare(this.key, wrsElement.key);
        }
        
        @Override
        public String toString() {
            return "WrsElement{" +
            "value=" + value +
            ", key=" + key +
            '}';
        }
    }
    
    public static void main(String[] args) {
        /** each item = {id, weight} **/
        int[][] items = {
            {1, 1}, {2, 5}, {3, 20}, {4, 5}, {5, 10}, {6, 3}, {7, 3}, {8, 3}
        };
        int[] result = weightedReservoirSampling(items, 3);
        System.out.println(Arrays.toString(result));
    }
}
