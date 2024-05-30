//1642. Furthest Building You Can Reach
//

//minheap
class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> ladder_allocations = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i] - heights[i + 1];
            if (diff <= 0) {
                continue;
            }

            // Otherwise, allocate a ladder for this climb.
            ladder_allocations.offer(diff);
            // If we haven't gone over the number of ladders, nothing else to do.
            if (ladder_allocations.size() <= ladders) {
                continue;
            }
            // Otherwise, we will need to take a climb out of ladder_allocations
            bricks -= ladder_allocations.poll();
            // If this caused bricks to go negative, we can't get to i + 1
            if (bricks < 0) {
                return i;
            }

            // if (ladders > 0) {
            //     ladder_allocations.offer(diff);
            //     ladders--;
            // } else {
            //     if (ladder_allocations.peek() == null || ladder_allocations.peek() > diff) {
            //         bricks -= diff;
            //     } else {
            //         int small_ladder = ladder_allocations.poll();
            //         ladder_allocations.offer(diff);
            //         bricks -= small_ladder;
            //     }
            //     if (bricks < 0) {
            //         return i;
            //     }
            // }
        }
        return heights.length - 1;
    }
}

//maxheap
class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // Create a priority queue with a comparator that makes it behave as a max-heap.
        Queue<Integer> brickAllocations = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            // If this is actually a "jump down", skip it.
            if (climb <= 0) {
                continue;
            }            
            // Otherwise, allocate a ladder for this climb.
            brickAllocations.add(climb);
            bricks -= climb;
            // If we've used all the bricks, and have no ladders remaining, then 
            // we can't go any further.
            if (bricks < 0 && ladders == 0) {
                return i;
            }
            // Otherwise, if we've run out of bricks, we should replace the largest
            // brick allocation with a ladder.
            if (bricks < 0) {
                bricks += brickAllocations.remove();
                ladders--;
            }
        }
        // If we got to here, this means we had enough materials to cover every climb.
        return heights.length - 1;
    }
}

//binary search
class Solution {
    
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        // Make a sorted list of all the climbs.
        List<int[]> sortedClimbs = new ArrayList<>();
        for (int i = 0; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            if (climb <= 0) {
                continue;
            }
            sortedClimbs.add(new int[]{climb, i + 1});
        }
        Collections.sort(sortedClimbs, (a,b) -> a[0] - b[0]);
        
        // Now do the binary search, same as before.
        int lo = 0;
        int hi = heights.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (isReachable(mid, sortedClimbs, bricks, ladders)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return hi; // Note that return lo would be equivalent.
    }
    
    private boolean isReachable(int buildingIndex, List<int[]> climbs, int bricks, int ladders) {
        for (int[] climbEntry : climbs) {
            // Extract the information for this climb
            int climb = climbEntry[0];
            int index = climbEntry[1];
            // Check if this climb is within the range.
            if (index > buildingIndex) {
                continue;
            }//important！！！
            // Allocate bricks if enough remain; otherwise, allocate a ladder if
            // at least one remains.
            if (climb <= bricks) {
                bricks -= climb;
            } else if (ladders >= 1) {
                ladders -= 1;
            } else {
                return false;
            }
        }
        return true;
    }
}
