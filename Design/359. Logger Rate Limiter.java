public class Logger {
    Map<String, Integer> lastTime;
    /** Initialize your data structure here. */
    public Logger() {
        lastTime = new HashMap();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!lastTime.containsKey(message) || timestamp - lastTime.get(message) >= 10) { // first time
            lastTime.put(message, timestamp);
            return true;
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
