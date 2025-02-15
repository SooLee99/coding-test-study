public class Solution {
    public int solution(int[] players, int m, int k) {
        int n = players.length; 
        int[] added = new int[n]; 
        int active = 0;         
        int ans = 0;            
        
        for (int t = 0; t < n; t++) {
            if (t >= k) {
                active -= added[t - k];
            }
            
            int required = players[t] / m;
            
            if (active < required) {
                int shortage = required - active;
                ans += shortage;
                active += shortage;
                added[t] = shortage; 
            }
        }
        
        return ans;
    }
}
