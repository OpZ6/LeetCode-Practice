//218. The Skyline Problem
//https://leetcode.com/problems/the-skyline-problem/description/

import java.util.*;

public class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> heightEvents = new ArrayList<>();

        // Step 1: Convert buildings into events.
        for (int[] building : buildings) {
            // Add start event with negative height to distinguish from end event.
            heightEvents.add(new int[]{building[0], -building[2]});
            // Add end event with positive height.
            heightEvents.add(new int[]{building[1], building[2]});
        }

        // Step 2: Sort events.
        Collections.sort(heightEvents, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            // If same x coordinate, start event (-height) should come before end event (+height)
            return a[1] - b[1];
        });

        // Step 3: Max-heap to keep track of current heights.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // Initialize with 0 height.
        maxHeap.add(0);
        int prevMaxHeight = 0;

        // Step 4: Process each event.
        for (int[] event : heightEvents) {
            int x = event[0];
            int height = event[1];

            if (height < 0) {
                // Start event: add the building height.
                maxHeap.add(-height);
            } else {
                // End event: remove the building height.
                maxHeap.remove(height);
            }

            int currentMaxHeight = maxHeap.peek();
            // If the max height changes, add a key point.
            if (currentMaxHeight != prevMaxHeight) {
                result.add(Arrays.asList(x, currentMaxHeight));
                prevMaxHeight = currentMaxHeight;
            }
        }

        return result;
    }
}
