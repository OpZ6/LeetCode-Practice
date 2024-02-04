// 170. Two Sum III - Data structure design
// https://leetcode.com/problems/two-sum-iii-data-structure-design/description/

class TwoSum {
    List<Integer> numbers;

    public TwoSum() {
        numbers = new ArrayList<>();
    }

    public void add(int number) {
        int index = Collections.binarySearch(numbers, number);
        if (index < 0) {
            index = -(index + 1); // Calculate the insertion point
        }
        this.numbers.add(index, number);
    }

    public boolean find(int value) {
        int left = 0;
        int right = numbers.size() - 1;
        while (left < right) {
            int sum = numbers.get(right) + numbers.get(left);
            if (sum > value) {
                right--;
            } else if (sum < value) {
                left++;
            } else {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */

//use hash
public class TwoSum {
    private List<Integer> list = new ArrayList<Integer>();
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    // Add the number to an internal data structure.
	public void add(int number) {
	    if (map.containsKey(number)) map.put(number, map.get(number) + 1);
	    else {
	        map.put(number, 1);
	        list.add(number);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for (int i = 0; i < list.size(); i++){
	        int num1 = list.get(i), num2 = value - num1;
	        if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) return true;
	    }
	    return false;
	}
}
