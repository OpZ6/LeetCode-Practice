//841. Keys and Rooms
//https://leetcode.com/problems/keys-and-rooms/

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> key = new LinkedList<>();
        int[] visited = new int[rooms.size()];

        key.add(0);

        while (!key.isEmpty()){
            int index = key.poll();
            if (visited[index] == 0){
                visited[index] = 1;
                for (Integer tmp : rooms.get(index)){
                    key.add(tmp);
                }
            }
        }

        for (int x : visited) {
            if (x == 0) return false;
        }
        return true;
    }
}

//easier
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> keys = new LinkedList<>();
        keys.add(0);
        int visitedCount = 0;

        while (!keys.isEmpty()) {
            int key = keys.poll();
            if (rooms.get(key) != null) { // Room has not been visited
                visitedCount++;
                keys.addAll(rooms.get(key));
                rooms.set(key, null); // Mark the room as visited by setting it to null
            }
        }

        return visitedCount == rooms.size();
    }
}
