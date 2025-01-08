import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        Set<Integer> sums = new HashSet<>();
        
        // 원형 수열의 누적 합 계산
        int[] circular = new int[n * 2];
        for (int i = 0; i < n; i++) {
            circular[i] = circular[i + n] = elements[i];
        }

        // 연속 부분 수열 합 계산 (누적 합 사용)
        for (int k = 1; k <= n; k++) { // 부분 수열 길이
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += circular[i]; // 초기 길이 k 합
            }
            sums.add(sum);
            for (int start = 1; start < n; start++) {
                sum = sum - circular[start - 1] + circular[start + k - 1]; // 슬라이딩 윈도우
                sums.add(sum);
            }
        }

        return sums.size();
    }
}
