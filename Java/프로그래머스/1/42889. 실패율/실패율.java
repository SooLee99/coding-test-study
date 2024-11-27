import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        // 1. 스테이지별 사용자 수를 저장 (1번부터 N번까지만 관리)
        int[] usersAtStage = new int[N];
        for (int stage : stages) {
            if (stage <= N) { // N + 1은 모든 스테이지를 클리어한 사용자 → 무시
                usersAtStage[stage - 1]++;
            }
        }

        // 2. 실패율 계산
        int playersRemaining = stages.length; // 초기 도달 사용자 = 전체 사용자 수
        double[] failureRates = new double[N]; // 실패율 저장 배열
        for (int i = 0; i < N; i++) {
            if (playersRemaining == 0) {
                failureRates[i] = 0.0; // 도달한 사용자가 없으면 실패율 0
            } else {
                failureRates[i] = (double) usersAtStage[i] / playersRemaining;
            }
            playersRemaining -= usersAtStage[i]; // 다음 스테이지로 이동할 사용자 감소
        }

        // 3. 실패율을 기준으로 정렬
        Integer[] stageOrder = new Integer[N];
        for (int i = 0; i < N; i++) {
            stageOrder[i] = i + 1; // 스테이지 번호 저장
        }
        Arrays.sort(stageOrder, (a, b) -> {
            if (failureRates[b - 1] == failureRates[a - 1]) {
                return Integer.compare(a, b); // 실패율 같으면 스테이지 번호 오름차순
            }
            return Double.compare(failureRates[b - 1], failureRates[a - 1]); // 실패율 내림차순
        });

        // 4. 정렬된 결과 반환
        return Arrays.stream(stageOrder).mapToInt(i -> i).toArray();
    }

}