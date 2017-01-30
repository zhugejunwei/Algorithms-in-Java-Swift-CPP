// Q1
// This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.

A = [-3, 7, 5, 5, 5, 3, 10, 0, 22, 13, 100]

x = 10

=> (-3,13), (7, 3), (5, 5), (10, 0)

--> each number should only be used once

// boolean vis[]
// Map<Integer, Integer> map // key: one ele, val:another ele
10-(-3) = 13
// map.put(key, sum - key)
if map.conatins(key - ele1)
// sum - key

// sort

// l = 0, r = n - 1
nums[l] + nums[r] > sum
r--


public List<Integer> solution(int[] arr) {
    if (arr == null || arr.length == 0) return new ArrayList();
    Arrays.sort(arr);
    int l = 0, r = arr.length - 1;
    List<Integer> res = new ArrayList();
    while (l < r) {
        if (nums[l] + nums[r] == sum) {
            res.add(new int[]{nums[l], nums[r]});
            l++; r--;
        } else if (nums[l] + nums[r] < sum) {
            l++;
        } else {
            r--;
        }
    }
    return res;
}
    
// Q2
    
    
    
-> "IBM", 30.5
-> "GE", 100.2
-> "ABC", 33.4
-> "IBM", 31.5

// String(company) : 10 prices

// Map<String, List<Integer>> map;

// head, time1, time2 .... time10 .. tail

// IBM, List.size > 10,

void priceChanged (const string& company, double price) {

}


void getLatest10PricesForCompany (String company) {

}

// global greatest value till now for that company

// Map<String, Integer> maxMap;  company name:greatest prices

// cur > maxMap.get(companyname) ==> update this map

void getGreatestPriceOfDay (String company)

// Map<String, Integer> minMap;  company name:smallest prices

void getSmallersPriceOfDay (String company)

-- capital english characters and they can be at the most 3 characters long



int hash (String company ) {
max value of one char is 26
aaa - 1
zzz - 26^3
abc -> range(1 - 26^3)
(a-'a')*26^2*(b-'a')*26*(c-'a')
(b-'a')*26^2*(c-'a')*26*(a-'a')
}

the latest 100 companies that have traded

0 -> some
9 -> IBM
99 ---- asdf
100 --- ABC
101 --- IBM

LRU
Node<>

head: 0,some // delete

newHead(IBM) -> Oldhead -> n1 -> n2(IBM) -> tail // number 100

class Node {
String: company name
    Node head // latest
    Node tail //
}

Map<String, Node> nodemap; // name -> node
