class Solution {
    boolean solution(String s) {
        String str = s.toUpperCase();
        if(countChar(str, 'P') != countChar(str, 'Y')) {
            return false;
        }
        return true;
    }
    
    public static long countChar(String str, char ch) {
        return str.chars()
            .filter(c -> c == ch)
            .count();
    }
}