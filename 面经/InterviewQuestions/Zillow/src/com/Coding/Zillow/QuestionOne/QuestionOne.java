package com.Coding.Zillow.QuestionOne;

import static org.junit.Assert.assertEquals;
public class QuestionOne {


    /*
     * COMMENT: There is potential limitations within this program. Since the
     * definition of data type "long" is dependent on specific architectures, so
     * it can be 4 bytes long or 8 bytes long, depending on the architecture.
     * Another limitation is bits overflow. If the String consists of a number
     * larger than Long.MAX then overflow will occur resulting in either negated
     * value or some upper bits will be ignored thus the number will be very
     * different. Last, current program will not tolerate any non-numeric
     * character, therefore once it detects one, and exception will be thrown.It
     * is possible to just extract the numeric parts in the String, but since it
     * is not specified, it's easier and more reasonable to let user check for
     * input characters.
     */
    public long stringToLong(String s) {
        if (s.isEmpty()) {
            throw new RuntimeException("Empty input");
        }
        int len = s.length();
        long val = 0;
        boolean Neg = false;
        int i = 0;
        char[] sChar = s.toCharArray();
        if (sChar[0] == '-') {
            Neg = true;
            i = 1;
        }

        for (; i < len; ++i) {
            int c = (sChar[i] - '0');

            if (c < 0 || c > 9) {
                throw new RuntimeException("Invalid input");
            }

            val *= 10;
            val += c;
        }

        if (Neg) {
            val *= -1;
        }
        return val;
    }

    public void test() {
        assertEquals(stringToLong("123"), 123);
        assertEquals(stringToLong("-123"), -123);
        try{
            stringToLong("12e3");
        }catch(RuntimeException e){
            assertEquals(e, new RuntimeException("Invalid input"));
        }

        System.out.println(stringToLong("87566565689778798"));
    }
    
    public static void main(String args[]){
        QuestionOne q = new QuestionOne();
        q.test();
    }
}