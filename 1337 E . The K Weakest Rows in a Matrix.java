//1337. The K Weakest Rows in a Matrix
//Easy
//https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/

class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        int[] res = new int[k];
        for(int i = 0; i < mat.length; i++){
            tmp.add(i);
        }
        int count = 0;
        for(int j = 0; j <= mat[0].length; j++){
            int i = 0;
            while(i < tmp.size()){
                int tmpNum = tmp.get(i);
                if(j == mat[0].length || mat[tmpNum][j] == 0){
                    res[count] = tmpNum;
                    tmp.remove(i);
                    count++;
                    if(count == k){
                        return res;
                    }
                }else{
                    i++;                    
                }
            }
        }
        return res;
    }
}

//change mind, use row score with sort function
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int rows = mat.length;
        int cols = mat[0].length;

        int[] score = new int[rows];
        int j;
        for (int i = 0; i < rows; i++) {
            j = 0;
            for (; j < cols; j++) {
                if (mat[i][j] == 0) {
                    break;
                }
            }
            /*
            * we can create a score to match the sort condition from description
            * score = soldiersCount * rows + currentRowIndex
            * so we can get soldiersCount by score / rows, and get rowIndex by score % rows
            */
            score[i] = j * rows + i;
        }

        Arrays.sort(score);
        for (int i = 0; i < score.length; i++) {
            // get rowIndex
            score[i] = score[i] % rows;
        }

        return Arrays.copyOfRange(score, 0, k);
    }
}
