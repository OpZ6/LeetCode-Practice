//17. Letter Combinations of a Phone Number
//https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/


//recursion ： not backtrack
class Solution {
    public List<String> letterCombinations(String digits) {
        String[] check = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digits == "" || digits.length() == 0) {
            return new ArrayList<>(); // 修正：返回空列表
            // return List.of(); // 直接返回一个空的列表
        }
        return backtrack(digits, 0, check);
    }

    public List<String> backtrack(String digits, int index, String[] check) {
        if (index == digits.length()) {
            return new ArrayList<String>() {{ add(""); }}; // 添加一个空字符串作为终止条件
            // return List.of(""); // 使用List.of创建包含单个空字符串的列表
        }
        List<String> result = new ArrayList<>();
        String letters = check[digits.charAt(index) - '0'];
        for (char letter : letters.toCharArray()) {
            for (String sub : backtrack(digits, index + 1, check)) {
                result.add(letter + sub);//最后一层必须经过这
            }
        }
        return result;
    }
}

//backtrack
class Solution {
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> letters = Map.of(
            '2',
            "abc",
            '3',
            "def",
            '4',
            "ghi",
            '5',
            "jkl",
            '6',
            "mno",
            '7',
            "pqrs",
            '8',
            "tuv",
            '9',
            "wxyz");
    private String phoneDigits;

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return combinations;
        }
        phoneDigits = digits;
        backtrack(0, new StringBuilder());
        return combinations;
    }

    private void backtrack(int index, StringBuilder path) {
        if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return;
        }

        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            // Add the letter to our current path
            path.append(letter);
            // Move on to the next digit
            backtrack(index + 1, path);
            // Backtrack by removing the letter before moving onto the next
            path.deleteCharAt(path.length() - 1);
        }
    }
}
