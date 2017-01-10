public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long Q = scanner.nextInt();
        int M = scanner.nextInt();
        long N = 0;
        Pair[] pairs = new Pair[M];
        for (int i = 0; i < M; i++) {
            int v = scanner.nextInt();
            long c = (long)scanner.nextInt();
            
            N += c;
            Pair pair = new Pair(v, c);
            pairs[i] = pair;
        }
        
        Arrays.sort(pairs, new MyPairComparator());
        
        for (int i = 1; i < M; i++) {
            pairs[i].count += pairs[i - 1].count;
        }
        
        List<Integer> result = findQuantiles(N, Q, pairs);
        for (Integer num : result) {
            System.out.println(num);
        }
        scanner.close();
    }
    
    public static List<Integer> findQuantiles(long N, long Q, Pair[] pairs) {
        List<Integer> res = new ArrayList();
        if (N <= 0 || Q <= 1 || pairs == null || pairs.length == 0) {
            return res;
        }
        
        for (long i = 1; i < Q; i++) {
            long index = (long)Math.ceil((double)N * i / Q);
            int quantile = binarySearch(pairs, index);
            res.add(quantile);
        }
        return res;
    }
    
    private static int binarySearch(Pair[] pairs, long target) {
        int l = 0, r = pairs.length - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (pairs[mid].count == target) return pairs[mid].value;
            else if (pairs[mid].count > target) r = mid;
            else l = mid;
        }
        if (pairs[l].count >= target) {
            return pairs[l].value;
        } else {
            return pairs[r].value;
        }
    }
    
    static class Pair {
        int value;
        long count;
        public Pair(int value, long count) {
            this.value = value;
            this.count = count;
        }
    }
    static class MyPairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair a, Pair b) {
            return a.value - b.value;
        }
    }
}
