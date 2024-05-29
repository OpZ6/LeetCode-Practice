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

//priorityqueue
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        // 创建一个最小堆
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            // 如果士兵数量相同，则比较行索引
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            // 否则比较士兵数量
            return a[1] - b[1];
        });//define the compare rules

        // 遍历矩阵的每一行
        for (int i = 0; i < mat.length; i++) {
            // 计算每行的士兵数量
            int count = 0;
            for (int j = 0; j < mat[i].length; j++) {
                count += mat[i][j];
            }
            // 将行索引和士兵数量添加到最小堆中
            pq.offer(new int[]{i, count});
        }

        // 从最小堆中取出前k个最弱的行
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll()[0];
        }

        return result;
    }
}
