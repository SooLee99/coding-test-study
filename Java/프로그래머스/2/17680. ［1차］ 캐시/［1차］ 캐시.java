import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        // 캐시 크기가 0이면 모든 도시 검색은 미스(5초)
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        // 캐시를 리스트로 구현 (LRU 방식)
        LinkedList<String> cache = new LinkedList<>();
        
        for (String city : cities) {
            // 도시 이름을 소문자로 변환 (대소문자 구분하지 않음)
            String cityLower = city.toLowerCase();
            
            // 캐시에서 해당 도시가 있는지 확인
            if (cache.contains(cityLower)) {
                // 캐시 히트: 실행시간 1초
                answer += 1;
                // 해당 도시를 최근에 사용한 도시로 갱신 (LRU)
                cache.remove(cityLower);
                cache.add(cityLower);
            } else {
                // 캐시 미스: 실행시간 5초
                answer += 5;
                // 캐시 크기가 꽉 차면 가장 오래된 도시를 제거
                if (cache.size() == cacheSize) {
                    cache.poll();
                }
                // 새 도시를 캐시에 추가
                cache.add(cityLower);
            }
        }
        
        return answer;
    }
}