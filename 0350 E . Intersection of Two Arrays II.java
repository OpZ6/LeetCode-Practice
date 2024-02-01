// 350. Intersection of Two Arrays II
// https://leetcode.com/problems/intersection-of-two-arrays-ii/description/

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] test1 = new int[1001];
        int[] test2 = new int[1001];
        Arrays.fill(test1, 0);
        Arrays.fill(test2, 0);
        for (int i = 0; i < nums1.length; i++) {
            test1[nums1[i]]++;
        }
        for (int i = 0; i < nums2.length; i++) {
            test2[nums2[i]]++;
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < 1001; i++) {
            int count = Math.min(test1[i], test2[i]);
            while (count > 0) {
                res.add(i);
                count--;
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }
}

//hashmap: deduct to check whether we could add more
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            int freq = map.getOrDefault(i, 0);
            map.put(i, freq + 1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.get(i) != null && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}

//sorted and two-pointer
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < n && j < m) {
            int a = nums1[i], b = nums2[j];
            if (a == b) {
                list.add(a);
                i++;
                j++;
            } else if (a < b) {
                i++;
            } else {
                j++;
            }
        }
        int[] ret = new int[list.size()];
        for (int k = 0; k < list.size(); k++)
            ret[k] = list.get(k);
        return ret;
    }
}
