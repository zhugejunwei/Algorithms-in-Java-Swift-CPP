public class ValidWordAbbr {
    Map<String, String> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap();
        for (String dic : dictionary) {
            String abbr = getAbbr(dic);
            if (map.containsKey(abbr)) {
                if (!map.get(abbr).equals(dic)) {
                    map.put(abbr, "");
                }
            } else {
                map.put(abbr, dic);
            }
        }
    }
    
    public boolean isUnique(String word) {
        String a = getAbbr(word);
        return !map.containsKey(a) || map.get(a).equals(word);
    }
    
    private String getAbbr(String s) {
        if (s.length() <= 2) return s;
        return s.charAt(0) + String.valueOf(s.length() - 2) + s.charAt(s.length() - 1);
    }
}
