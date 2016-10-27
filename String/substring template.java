int findSubstring(string s){
    vector<int> map(128,0);
    int counter; // check whether the substring is valid
    int begin=0, end=0; //two pointers, one point to tail and one  head
    int d; //the length of substring

    for() { /* initialize the hash map here */ }

    while(end<s.size()){

        if(map[s[end++]]-- ?){  /* modify counter here */ }

        while(/* counter condition */){

            /* update d here if finding minimum*/

            //increase begin to make it invalid/valid again

            if(map[s[begin++]]++ ?){ /*modify counter here*/ }
        }

        /* update d here if finding maximum*/
    }
    return d;
}


/* 
 Longest Substring with At Most Two Distinct Characters
*/

int lengthOfLongestSubstringTwoDistinct(string s) {
    vector<int> map(128, 0);
    int counter=0, begin=0, end=0, d=0;
    while(end<s.size()){
        if(map[s[end++]]++==0) counter++;
        while(counter>2) if(map[s[begin++]]--==1) counter--;
    d=max(d, end-begin);
    }
    return d;
}

/*
 Longest Substring Without Repeating Characters
 */

int lengthOfLongestSubstring(string s) {
    vector<int> map(128,0);
    int counter=0, begin=0, end=0, d=0;
    while(end<s.size()){
        if(map[s[end++]]++>0) counter++;
        while(counter>0) if(map[s[begin++]]-->1) counter--;
        d=max(d, end-begin); //while valid, update d
    }
    return d;
}
