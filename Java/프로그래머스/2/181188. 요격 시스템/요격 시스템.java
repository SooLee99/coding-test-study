import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (o1, o2) -> { 
            return o1[1] - o2[1]; 
        });

        int last = -1;
        
        for (int[] target : targets) {
            if (last == -1) {
                answer++;
                last = target[1] - 1;
                continue;
            }
            
            if (last >= target[0] && last <= target[1]) continue;
            answer++;
            last = target[1] - 1;
        }
        
        // 모든 폭격 미사일 구간을 커버하는 데 필요한 최소 요격 미사일 수를 반환합니다.
        return answer;
    }
}
