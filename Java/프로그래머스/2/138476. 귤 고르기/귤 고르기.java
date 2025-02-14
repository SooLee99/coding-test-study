import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int k, int[] tangerine) {
        // Map에 귤의 크기별 개수 담기
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int t : tangerine) {
            countMap.put(t, countMap.getOrDefault(t, 0) + 1);
        }
        
        // 빈도수만 모아서 내림차순 정렬
        List<Integer> counts = countMap.values()                    
                                       .stream()
                                       .sorted(Comparator.reverseOrder())
                                       .collect(Collectors.toList());
        
        int answer = 0;
        // 내림차순으로 하나씩 빼면서 k 이하가 되면 종료
        for (int count : counts) {
            if (k <= 0) break;
            k -= count;
            answer++;
        }
        
        return answer;
    }
}
