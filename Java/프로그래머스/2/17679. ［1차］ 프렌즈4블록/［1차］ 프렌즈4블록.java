import java.util.*;

public class Solution {
    public int solution(int m, int n, String[] board) {
        char[][] grid = new char[m][n];
        boolean[][] toRemove = new boolean[m][n];
        int totalRemoved = 0;

        // board를 char[][]로 변환
        for (int i = 0; i < m; i++) {
            grid[i] = board[i].toCharArray();
        }

        while (true) {
            // 1. 2x2 블록 체크해서 제거할 블록 위치를 기록
            boolean found = false;
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char c = grid[i][j];
                    // 2x2가 같은 블록으로 이루어져 있는지 확인
                    if (c != ' ' && c == grid[i+1][j] && c == grid[i][j+1] && c == grid[i+1][j+1]) {
                        toRemove[i][j] = toRemove[i+1][j] = toRemove[i][j+1] = toRemove[i+1][j+1] = true;
                        found = true;
                    }
                }
            }

            // 2. 지워진 블록의 개수 세기
            int removed = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (toRemove[i][j]) {
                        grid[i][j] = ' '; // 지워진 블록은 빈칸으로 설정
                        removed++;
                        toRemove[i][j] = false; // 다시 초기화
                    }
                }
            }
            totalRemoved += removed;

            // 3. 더 이상 지울 블록이 없다면 종료
            if (!found) break;

            // 4. 블록 내리기
            for (int j = 0; j < n; j++) {
                // 내릴 블록을 담을 리스트
                List<Character> column = new ArrayList<>();
                // 현재 열에서 빈칸이 아닌 블록들을 column 리스트에 담기
                for (int i = 0; i < m; i++) {
                    if (grid[i][j] != ' ') {
                        column.add(grid[i][j]);
                    }
                }

                // 나머지는 빈칸으로 채우고, 떨어진 블록을 맨 아래에 넣기
                int idx = m - 1;
                for (int i = column.size() - 1; i >= 0; i--) {
                    grid[idx--][j] = column.get(i);
                }
                // 나머지 공간은 빈칸으로 채우기
                while (idx >= 0) {
                    grid[idx--][j] = ' ';
                }
            }
        }

        return totalRemoved;
    }
}
