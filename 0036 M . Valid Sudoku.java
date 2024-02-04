// 36. Valid Sudoku
// https://leetcode.com/problems/valid-sudoku/description/

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, List<Character>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char tmp = board[i][j];
                if (tmp != '.') {
                    int[] pos = { i + 1, -j, (i / 3 + 1) * 10 + (j / 3 + 1) };// row column block
                    for (int ind : pos) {
                        List<Character> check = map.getOrDefault(ind, new ArrayList<Character>());
                        if (check.contains(tmp)) {
                            return false;
                        }
                        check.add(tmp);
                        map.put(ind, check);
                        // System.out.println(tmp);
                        // System.out.println(ind);
                        // System.out.println(check);
                    }
                }
            }
        }
        return true;
    }
}

//!seen.add if true then add, if false then collide
public boolean isValidSudoku(char[][] board) {
    Set seen = new HashSet();
    for (int i=0; i<9; ++i) {
        for (int j=0; j<9; ++j) {
            char number = board[i][j];
            if (number != '.')
                if (!seen.add(number + " in row " + i) ||
                    !seen.add(number + " in column " + j) ||
                    !seen.add(number + " in block " + i/3 + "-" + j/3))
                    return false;
        }
    }
    return true;
}
