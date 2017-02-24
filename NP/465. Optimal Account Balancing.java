// paper: http://www.mathmeth.com/tom/files/settling-debts.pdf

public class Solution {
    public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0 || transactions[0].length == 0) return 0;
        // all accounts : final val
        Map<Integer, Integer> account = new HashMap();
        for (int[] t : transactions) {
            int out = t[0], in = t[1], val = t[2];
            account.put(out, account.getOrDefault(out, 0) - val);
            account.put(in, account.getOrDefault(in, 0) + val);
        }
        
        // put all none zero vals into a list
        List<Integer> debts = new ArrayList();
        for (int v : account.values())
            if (v != 0) debts.add(v);
        
        // optimize by deleting those "one-transaction" debt
        int one_transaction = removeOneTrans(debts);
        
        return one_transaction + minTransStartFrom(debts, 0);
    }
    
    private int removeOneTrans(List<Integer> debts) {
        Collections.sort(debts);
        int count = 0, i = 0, j = debts.size() - 1;
        while (i < j) {
            if (-debts.get(i) == debts.get(j)) {
                debts.remove(j);
                debts.remove(i);
                count++;
                j -= 2;
            } else if (-debts.get(i) > debts.get(j)) {
                i++;
            } else {
                j--;
            }
        }
        return count;
    }
    
    // 排列：p(n,m)=n(n-1)(n-2)……(n-m+1)= n!/(n-m)!(规定0!=1)
    // 全排列： m = n, p(n, n) = n!
    // for this question, it's actually, (n-1)*(n-2)*...*(n-n) = (n-1)!, because start is fixed
    private int minTransStartFrom(List<Integer> debts, int start) {
        int res = -1, n = debts.size();
        while (start < n && debts.get(start) == 0)
            start++;
        
        if (start == n) return 0;
        
        for (int i = start + 1; i < n; i++) {
            if ((long)debts.get(i) * debts.get(start) < 0) {
                debts.set(i, debts.get(i) + debts.get(start));
                int count = 1 + minTransStartFrom(debts, start + 1);
                if (count < res || res == -1)
                    res = count;
                debts.set(i, debts.get(i) - debts.get(start));
            }
        }
        return res;
    }
}


// second solution
// https://discuss.leetcode.com/topic/68948/easy-java-solution-with-explanation/2
public class Solution {
    public int minTransfers(int[][] transactions) {
        if(transactions == null || transactions.length == 0) return 0;
        Map<Integer, Integer> acc = new HashMap<>();
        for(int i = 0;i<transactions.length;i++){
            int id1 = transactions[i][0];
            int id2 = transactions[i][1];
            int m = transactions[i][2];
            acc.put(id1, acc.getOrDefault(id1, 0)-m);
            acc.put(id2, acc.getOrDefault(id2, 0)+m);
        }
        List<Integer> negs = new ArrayList<>();
        List<Integer> poss = new ArrayList<>();
        for(Integer key:acc.keySet()){
            int m = acc.get(key);
            if(m == 0) continue;
            if(m<0) negs.add(-m);
            else poss.add(m);
        }
        int ans = Integer.MAX_VALUE;
        Stack<Integer> stNeg = new Stack<>(), stPos = new Stack<>();
        for(int i =0;i<1000;i++){
            for(Integer num:negs) stNeg.push(num);
            for(Integer num:poss) stPos.push(num);
            int cur = 0;
            while(!stNeg.isEmpty()){
                int n = stNeg.pop();
                int p = stPos.pop();
                cur++;
                if(n == p) continue;
                if(n>p){
                    stNeg.push(n-p);
                } else {
                    stPos.push(p-n);
                }
            }
            ans = Math.min(ans, cur);
            Collections.shuffle(negs);
            Collections.shuffle(poss);
        }
        return ans;
    }
}
