//119. Pascal's Triangle II
//https://leetcode.com/problems/pascals-triangle-ii/description/

class Solution {
    public List<Integer> getRow(int rowIndex) {
        Integer[] tmp = new Integer[rowIndex + 1];
        Arrays.fill(tmp, 1);

        int count = 0;
        while(count < rowIndex - 1){
            int tmp_num = 1;
            for(int i = 1; i <= count + 1; i++){
                int sum = tmp_num + tmp[i];
                tmp_num = tmp[i];
                tmp[i] = sum;
            }
            count++;
        }
        return Arrays.asList(tmp);
    }
}

//https://leetcode.com/problems/pascals-triangle-ii/solutions/38584/another-accepted-java-solution/
//https://youtu.be/C-895oIPC-c
//reverse way much easier
class Solution {
    public List<Integer> getRow(int rowIndex) {
        Integer[] arr = new Integer[rowIndex + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;
        
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                arr[j] += arr[j - 1];
            }
        }
        return Arrays.asList(arr);
    }
}

//note: use recursive theory
