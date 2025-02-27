class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int m = q.length;
        // 각 시도의 입력 숫자들을 비트마스크로 변환하여 저장 (숫자 x는 1<<(x-1)) 
        int[] queryMasks = new int[m];
        for (int i = 0; i < m; i++) {
            int mask = 0;
            for (int j = 0; j < 5; j++) {
                mask |= (1 << (q[i][j] - 1));
            }
            queryMasks[i] = mask;
        }
        
        int count = 0;
        // 모든 5개 숫자 조합을 1부터 n까지 탐색 (오름차순으로 선택)
        for (int a = 1; a <= n - 4; a++) {
            for (int b = a + 1; b <= n - 3; b++) {
                for (int c = b + 1; c <= n - 2; c++) {
                    for (int d = c + 1; d <= n - 1; d++) {
                        for (int e = d + 1; e <= n; e++) {
                            // 후보 조합을 비트마스크로 표현 (각 비트는 해당 숫자의 포함 여부)
                            int candidateMask = (1 << (a - 1)) | (1 << (b - 1)) | (1 << (c - 1))
                                    | (1 << (d - 1)) | (1 << (e - 1));
                            boolean valid = true;
                            // 각 시도에 대해 후보 조합과의 교집합 크기가 응답과 일치하는지 확인
                            for (int i = 0; i < m; i++) {
                                if (Integer.bitCount(candidateMask & queryMasks[i]) != ans[i]) {
                                    valid = false;
                                    break;
                                }
                            }
                            if (valid) count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
