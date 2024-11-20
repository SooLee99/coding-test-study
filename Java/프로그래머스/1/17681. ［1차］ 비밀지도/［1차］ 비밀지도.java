class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] resultMap = new String[n];

        for (int i = 0; i < n; i++) {
            // OR 연산으로 두 지도를 합침
            int combinedMap = arr1[i] | arr2[i];

            // OR 연산 결과를 지도 문자열("#", " ")로 변환
            resultMap[i] = toMapRow(combinedMap, n);
        }

        return resultMap;
    }

    // OR 연산 결과를 지도 문자열("#", " ")로 변환
    private String toMapRow(int combinedMap, int length) {
        StringBuilder row = new StringBuilder();

        for (int bit = length - 1; bit >= 0; bit--) {
            // 현재 비트가 1이면 "#", 아니면 " "
            if ((combinedMap & (1 << bit)) != 0) {
                row.append('#');
            } else {
                row.append(' ');
            }
        }

        return row.toString();
    }
}