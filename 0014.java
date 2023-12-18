//14. Longest Common Prefix
//Easy
//https://leetcode.com/problems/longest-common-prefix/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){ 
            return "";
        }

        String prefix = strs[0];

        for (int index = 1; index < strs.length; index++){
            while(strs[index].indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()){
                    return "";
                }
            }
        }

        return prefix;
    }
}

//use string func like substring/isEmpty/isBlank/indexOf
//pay attention:  prefix!!!
//sort cost time

class Solution {
    public String longestCommonPrefix(String[] v) {
        StringBuilder ans = new StringBuilder();
        Arrays.sort(v);
        String first = v[0];
        String last = v[v.length-1];
        for (int i=0; i<Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return ans.toString();
            }
            ans.append(first.charAt(i));
        }
        return ans.toString();
    }
}
