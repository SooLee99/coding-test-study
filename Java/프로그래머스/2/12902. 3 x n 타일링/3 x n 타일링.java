class Solution {
    long[] tile = new long[5001];
    
    public long solution(int n) {
        long answer = 0;
        int mod=1000000007;
        
        tile[0]=1;
        tile[2]=3;
        
       for(int i=4; i<=n; i+=2){
           tile[i]= (tile[i-2]*4%mod -tile[i-4]%mod +mod)%mod;
        
        };
        
        return answer=tile[n];
    }
    
  
}