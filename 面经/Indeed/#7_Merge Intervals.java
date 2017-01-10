public class Main
{
    public static void main(String args[] ) throws Exception {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        Pair[] pairs = new Pair[N];
        for (int i = 0; i < N; i++) {
            long p = scan.nextInt();
            int m = scan.nextInt();
            pairs[i] = new Pair(p, m);
        }
        
        long[] res = new long[N];
        for (int i = 0; i < N; i++) {
            if (pairs[i].idx == -1) {
                res[i] = pairs[i].val;
                continue;
            }
            int j = i;
            while (j != -1 && pairs[pairs[j].idx].val < pairs[j].val) {
                pairs[j].val = pairs[pairs[j].idx].val;
                if (pairs[j].idx > 0)
                    j = pairs[j].idx;
                else break;
            }
            res[i] = pairs[j].val;
            System.out.println(res[3]);
        }
        int output = 0;
        for (long n : res) output += n;
        System.out.println(output);
        scan.close();
    }
    static class Pair {
        long val;
        int idx;
        public Pair(long val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}
