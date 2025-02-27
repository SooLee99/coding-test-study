import java.util.*;
class Solution {
    int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        DFS(0, 1, n, q, ans, new int[5]);
        return answer;
    }
    void DFS(int x, int y, int n, int[][] q, int[] ans, int[] list)
    {
        int i0, i1, i2, t0;
        if(x==5)
        {
            for(i0 = 0; i0 < q.length; i0++)
            {
                t0 = 0;
                for(i1 = 0; i1 < 5; i1++)
                    for(i2 = 0; i2 < 5; i2++)
                        if(list[i1]==q[i0][i2])
                            t0++;
                if(t0!=ans[i0])
                    break;
            }
            if(i0 == q.length)
                answer++;
            return;
        }
        for(; y <= n; y++)
        {
            list[x] = y;
            DFS(x+1, y+1, n, q, ans, list);
        }
    }
}