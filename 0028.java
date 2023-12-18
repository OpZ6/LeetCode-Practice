//28. Find the Index of the First Occurrence in a String
//Easy
//https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string

class Solution {nnn
    public int strStr(String haystack, String needle) {
        for(int i = 0; i < haystack.length() - needle.length() + 1; i++){
            for(int j = 0; j < needle.length(); j++){
                if(needle.charAt(j) != haystack.charAt(i + j)){
                    break;
                }
                if(j == needle.length() - 1){
                    return i;                    
                }
            }
        }
        return -1;
    }
}


//better way to get out of for loop
class Solution {
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) 
        {
            for (int j = 0; ; j++) 
            {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
}
