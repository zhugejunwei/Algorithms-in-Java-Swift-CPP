public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
        
        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<String>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            
            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();
                
                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);
                        
                        if (endSet.contains(target)) {
                            return len + 1;
                        }
                        
                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }
            
            beginSet = temp;
            len++;
        }
        
        return 0;
    }
}

// slower

public class Solution {
 public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
     // set1 is beginSet, set2 is target set that has the potential target.
     Set<String> set1 = new HashSet(), set2 = new HashSet();
     Set<String> vis = new HashSet();
     set1.add(beginWord);
     set2.add(endWord);
     int step = 2;
     while (!set1.isEmpty() && !set2.isEmpty()) {
         if (set1.size() > set2.size()) {
             Set<String> tmp = set1;
             set1 = set2;
             set2 = tmp;
         }
         Set<String> nextLevel = new HashSet();
         for (String word : set1) {
             for (int i = 0; i < word.length(); i++) {
                 for (int j = 'a'; j <= 'z'; j++) {
                     String newWord = word.substring(0, i) + (char)j + word.substring(i + 1);
                     if (set2.contains(newWord)) {
                         return step;
                     }
                     if (!vis.contains(newWord) && wordList.contains(newWord)) {
                         nextLevel.add(newWord);
                         vis.add(newWord);
                     }
                 }
             }
         }
         set1 = nextLevel;
         step++;
     }
     return 0;
 }
}
