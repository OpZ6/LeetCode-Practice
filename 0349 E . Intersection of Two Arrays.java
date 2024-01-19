// 349. Intersection of Two Arrays
// https://leetcode.com/problems/intersection-of-two-arrays/description/

// my approach
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> checklist1 = new HashSet<>();
        Set<Integer> checklist2 = new HashSet<>();
        for (int num : nums1) {
            if (!checklist1.contains(num)) {//add directly！！！
                checklist1.add(num);
            }
        }
        for (int num : nums2) {
            if (!checklist2.contains(num)) {
                checklist2.add(num);
            }
        }
        List<Integer> res = new ArrayList<Integer>();
        for (Integer key : checklist2) {
            if (checklist1.contains(key)) {
                res.add(key);
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }
}
//better hash
class Solution {
    public int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int[] output = new int[set1.size()];
        int idx = 0;
        for (Integer s : set1)
            if (set2.contains(s))
                output[idx++] = s;

        return Arrays.copyOf(output, idx);
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1)
            set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2)
            set2.add(n);

        if (set1.size() < set2.size())
            return set_intersection(set1, set2);
        else
            return set_intersection(set2, set1);
    }
}

//another hash
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> s1 = new HashSet<>();
        HashSet<Integer> s2 = new HashSet<>();

        for (int i : nums1) {
            s1.add(i);
        }
        for (int i : nums2) {
            if (s1.contains(i))
                s2.add(i);
        }

        int[] res = new int[s2.size()];
        int i = 0;
        for (int n : s2) {
            res[i++] = n;
        }
        return res;
    }
}
