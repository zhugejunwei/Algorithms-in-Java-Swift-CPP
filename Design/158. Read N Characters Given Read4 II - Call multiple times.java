/* The read4 API is defined in the parent class Reader4.
 int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {      // this question is about internal buffer and external buffer
        // internal buffer: i4, n4, buf4
        // external buffer: i, n, buf
        int i = 0;
        while (i < n) {     // external buf limitation
            if (i4 >= n4) {     // internal buf limitation
                i4 = 0;
                n4 = read4(buf4);
                if (n4 == 0) break;
            }
            buf[i++] = buf4[i4++];
        }
        return i;
    }
    char[] buf4 = new char[4];
    int i4 = 0, n4 = 0;
}
