//207. Course Schedule
//https://leetcode.com/problems/course-schedule/description/

//Pure BFS
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            adjList.get(pre).add(course);
        }
        
        // Step 2: Track learned courses
        Set<Integer> learned = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // Step 3: Find courses with no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (adjList.get(i).isEmpty()) {
                queue.offer(i);
                learned.add(i);
            }
        }
        
        // Step 4: Process courses using BFS
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int i = 0; i < numCourses; i++) {
                if (!learned.contains(i)) {
                    List<Integer> prerequisitesList = adjList.get(i);
                    prerequisitesList.remove(Integer.valueOf(current));
                    if (prerequisitesList.isEmpty()) {
                        queue.offer(i);
                        learned.add(i);
                    }
                }
            }
        }
        
        // Step 5: Check if all courses can be completed
        return learned.size() == numCourses;
    }
}

//indegree
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create adjacency list and in-degree array
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            adjList.get(pre).add(course);
            inDegree[course]++;
        }
        
        // Step 2: Add courses with no prerequisites to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Step 3: Process courses using BFS
        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            for (int neighbor : adjList.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Step 4: Check if all courses can be completed
        return count == numCourses;
    }
}
