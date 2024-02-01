// 359. Logger Rate Limiter
// https://leetcode.com/problems/logger-rate-limiter/description/

class Logger {
    HashMap<String, Integer> map;

    public Logger() {
        map = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (map.containsKey(message) && map.get(message) > timestamp) {
            return false;
        } else {
            map.put(message, timestamp + 10);
            return true;            
        }
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

// https://leetcode.com/problems/logger-rate-limiter/solutions/391558/review-of-four-different-solutions-hashmap-two-sets-queue-with-set-radix-buckets-java-centric/
// use queue to store last 10 sec message coming and use map to store message and decide whether to print!!!
class Logger {

    Queue<String> q; // holds all the words that came in last 10 seconds
    HashMap<String, Integer> map; // stores (word existing in q, timestamp last printed)

    /** Initialize your data structure here. */
    public Logger() {
        q = new LinkedList<>();
        map = new HashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp,
     * otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {

        // process q, get rid of words printed 10 seconds ago
        while (!q.isEmpty() && map.get(q.peek()) <= timestamp - 10) {
            map.remove(q.poll());
        }

        // see if word exists
        if (!map.containsKey(message)) {
            q.offer(message);
            map.put(message, timestamp);
            return true;
        } else { // word already exists in q
            return false;
        }

    }
}
