// 136. Single Number
// https://leetcode.com/problems/single-number/description/

//my approach: hashmap
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> checklist = new HashMap<>();
        for (int num : nums) {
            checklist.put(num, checklist.getOrDefault(num, 0) + 1);
        }
        for (Integer key: checklist.keySet()) {//can iterate the original list
            int value = checklist.get(key);
            if (value == 1) {
                return key;
            }
        }
        // for (int i : nums) { //better code
        //     if (checklist.get(i) == 1) {
        //         return i;
        //     }
        // }
        return 0;
    }
}

//Approach 2: List operation
// Iterate over all the elements in nums\text{nums}nums
// If some number in nums\text{nums}nums is new to array, append it
// If some number is already in the array, remove it
class Solution {
    public int singleNumber(int[] nums) {
        List<Integer> no_duplicate_list = new ArrayList<>();

        for (int i : nums) {
            if (!no_duplicate_list.contains(i)) {
                no_duplicate_list.add(i);
            } else {
                no_duplicate_list.remove(new Integer(i));
            }
        }
        return no_duplicate_list.get(0);
    }
}

//Approach 3: Math
//2∗(a+b+c)−(a+a+b+b+c)=c
class Solution {
    public int singleNumber(int[] nums) {
        int sumOfSet = 0, sumOfNums = 0;
        Set<Integer> set = new HashSet();

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sumOfSet += num;
            }
            sumOfNums += num;
        }
        return 2 * sumOfSet - sumOfNums;
    }
}

//Approach 4: Bit Manipulation
// If we take XOR of zero and some bit, it will return that bit
//     a⊕0=aa \oplus 0 = aa⊕0=a
// If we take XOR of two same bits, it will return 0
//     a⊕a=0a \oplus a = 0a⊕a=0
// a⊕b⊕a=(a⊕a)⊕b=0⊕b=b

class Solution {
    public int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }
}
  
