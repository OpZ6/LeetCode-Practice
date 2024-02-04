// 288. Unique Word Abbreviation
// https://leetcode.com/problems/unique-word-abbreviation/description/

class ValidWordAbbr {
    Map<String, List<String>> check;

    public ValidWordAbbr(String[] dictionary) {
        this.check = new HashMap<>();
        for (String dict : dictionary) {
            String abbr = abbreviation(dict);
            List<String> value = check.getOrDefault(abbr, new ArrayList<>());
            if (!value.contains(dict)) {
                value.add(dict);
            }
            check.put(abbr, value);
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = abbreviation(word);
        if (!check.containsKey(abbr) || (check.containsKey(abbr) && check.get(abbr).contains(word) && check.get(abbr).size() == 1)) {
            return true;
        }
        return false;
    }

    public String abbreviation (String word) {
      if(str.length()<=2) return str;//!!!!
        int len = word.length();
        String res = word.charAt(0) + (len - 2 + "") + word.charAt(len - 1);
        return res;
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */

//Simple Java solution (HashMap of HashSets)
class ValidWordAbbr {
    private Map<String, Set<String>> abbreviations;
    
    public ValidWordAbbr(String[] dictionary) {
        this.abbreviations = new HashMap<>();
        
        for (String word: dictionary) {
            String abbreviation = this.getAbbreviation(word);
            Set<String> set = this.abbreviations.getOrDefault(abbreviation, new HashSet<>());
            set.add(word);
            
            this.abbreviations.put(abbreviation, set);
        }
    }
    
    private String getAbbreviation(String word) {
        int w = word.length();
        String abbreviation;
        
        if (w < 3)
            abbreviation = word;
        else
            abbreviation = String.format("%c%d%c", word.charAt(0), w - 2, word.charAt(w - 1));
        
        return abbreviation;
    }
    
    public boolean isUnique(String word) {
        String abbreviation = this.getAbbreviation(word);
        
        Set<String> set = this.abbreviations.getOrDefault(abbreviation, null);
        
        if (set == null)
            return true;
        
        return set.size() == 1 && set.contains(word);
    }
}

//better hashmap
public class ValidWordAbbr {
    HashMap<String, String> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<String, String>();
        for(String str:dictionary){
            String key = getKey(str);
            // If there is more than one string belong to the same key
            // then the key will be invalid, we set the value to ""
            if(map.containsKey(key)){
                if(!map.get(key).equals(str)){
                    map.put(key, "");
                }
            }
            else{
                map.put(key, str);
            }
        }
    }

    public boolean isUnique(String word) {
        return !map.containsKey(getKey(word))||map.get(getKey(word)).equals(word);
    }
    
    String getKey(String str){
        if(str.length()<=2) return str;
        return str.charAt(0)+Integer.toString(str.length()-2)+str.charAt(str.length()-1);
    }
}
