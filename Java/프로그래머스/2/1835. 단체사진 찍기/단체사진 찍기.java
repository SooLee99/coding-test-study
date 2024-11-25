import java.util.*;

class Solution {
    private static final char[] CHARACTERS = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private int validCount = 0;     // 조건을 만족하는 경우의 수

    public int solution(int n, String[] data) {
        findArrangements(new boolean[8], new char[8], 0, data);
        return validCount;
    }

    // 모든 배치를 생성하며 조건을 확인하는 함수
    private void findArrangements(boolean[] visited, char[] order, int depth, String[] data) {
        // 1. 모든 캐릭터가 배치된 경우
        if (depth == 8) {
            validCount++;
            return;
        }

        // 2. 캐릭터를 하나씩 배치
        for (int i = 0; i < 8; i++) {
            if (!visited[i]) { // 방문하지 않은 캐릭터만 선택
                visited[i] = true;
                order[depth] = CHARACTERS[i];

                if (isPartialValid(order, depth, data)) {
                    findArrangements(visited, order, depth + 1, data);
                }
                // NOTE: 백트래킹 - 다시 방문 가능하도록 설정
                visited[i] = false;
            }
        }
    }

    // 현재 배치 상태가 주어진 조건을 만족하는지 확인하는 함수
    private boolean isPartialValid(char[] order, int depth, String[] data) {
        // 현재까지 배치된 순열에 대해 조건 확인
        for (String condition : data) {
            char char1 = condition.charAt(0);
            char char2 = condition.charAt(2); // 상대방 (두 번째 캐릭터)
            char op = condition.charAt(3);    // 연산자 (=, <, >)
            int gap = condition.charAt(4) - '0'; // 간격 값 (문자 -> 정수 변환)

            // 현재 배치된 캐릭터 위치 찾기
            int pos1 = findPosition(order, char1, depth);
            int pos2 = findPosition(order, char2, depth);

            // 두 캐릭터가 모두 배치되지 않았다면, 조건 확인 생략
            if (pos1 == -1 || pos2 == -1) continue;

            int distance = Math.abs(pos1 - pos2) - 1;

            // 조건에 따라 유효성 판단
            if (op == '=' && distance != gap) return false;
            if (op == '<' && distance >= gap) return false;
            if (op == '>' && distance <= gap) return false;
        }
        return true;
    }

    private int findPosition(char[] order, char target, int depth) {
        // 현재 깊이까지만 탐색
        for (int i = 0; i <= depth; i++) {
            if (order[i] == target) return i;
        }
        return -1;
    }
}
