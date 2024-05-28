//1046. Last Stone Weight
//https://leetcode.com/problems/last-stone-weight/

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            maxHeap.add(stone);
        }

        while (maxHeap.size() > 1) {
            int x = maxHeap.poll();
            int y = maxHeap.poll();
            if (x != y) {
                maxHeap.add(Math.abs(x - y));
            }
        }
        if (maxHeap.peek() == null) {
            return 0;
        }
        return maxHeap.peek();
    }
}

//easier
class Solution {
    public int lastStoneWeight(int[] stones) {
     PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> b - a);
        for (int a : stones)
            pq.offer(a);
        while (pq.size() > 1)
            pq.offer(pq.poll() - pq.poll());
        return pq.poll();
    }
}

//list
class Solution {
    public int lastStoneWeight(int[] stones) {
     ArrayList<Integer> arr=new ArrayList<>();
     for(int i=0;i<stones.length;i++){
         arr.add(stones[i]);
     }
     while(arr.size()>1){
      Collections.sort(arr);
       int y = arr.get(arr.size()-1);
        arr.remove(new Integer(y));
        
        int x = arr.get(arr.size()-1);
        arr.remove(new Integer(x));
        
        if(x!=y){
            arr.add(y-x);
        }
     }
     if(arr.isEmpty()) return 0;
      else return arr.get(0);
    }
}
