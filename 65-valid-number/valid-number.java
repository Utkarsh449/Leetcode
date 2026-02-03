class Solution {
    public boolean isNumber(String s) {
          s = s.trim();
        if (s.isEmpty()) return false;
        
        int i = 0, n = s.length();
        boolean hasDigit = false;
        
       
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
        
        
        int start = i;
        while (i < n && Character.isDigit(s.charAt(i))) {
            hasDigit = true;
            i++;
        }
        
        
        if (i < n && s.charAt(i) == '.') {
            i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                hasDigit = true;
                i++;
            }
        }
        
      
        if (i < n && (s.charAt(i) == 'e' || s.charAt(i) == 'E')) {
            i++;
            if (i >= n || (s.charAt(i) != '+' && s.charAt(i) != '-') && !Character.isDigit(s.charAt(i))) {
                return false;
            }
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
         
            if (i >= n || !Character.isDigit(s.charAt(i))) return false;
            i++;
            while (i < n && Character.isDigit(s.charAt(i))) i++;
        }
        
        return i == n && hasDigit;
        
    }
    
}