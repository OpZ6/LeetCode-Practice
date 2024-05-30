//1167. Minimum Cost to Connect Sticks
//

class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int stick : sticks) {
            minHeap.offer(stick);
        }
        
        int res = 0;
        while (minHeap.size() > 1) {
            int tmp = minHeap.poll();
            tmp += minHeap.poll();
            res += tmp;
            minHeap.offer(tmp);
        }

        return res;
    }
}


//no Heap
//Arrays.sort(sticks);
class Solution {
    public int connectSticks(int[] sticks) {
        List<Integer> vals = new LinkedList<Integer>(),
        sums = new LinkedList<Integer>();
        Arrays.sort(sticks);
        for (int s : sticks) {
            vals.add(s);
        }

        int cost = 0;
        while (vals.size() + sums.size() >= 2) {
            int a = removeLowest(vals, sums);
            int b = removeLowest(vals, sums);
            cost += a + b;
            sums.add(a + b);
        }

        return cost;
    }

    private int removeLowest(List<Integer> A, List<Integer> B) {
        if (A.isEmpty())
            return B.remove(0);
        if (B.isEmpty())
            return A.remove(0);
        return A.get(0) < B.get(0) ? A.remove(0) : B.remove(0);
    }
}
