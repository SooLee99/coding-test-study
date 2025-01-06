import java.util.*;

class Solution {
    /**
     * 수식을 분석하여 주어진 진법(2~9) 중 유효한 진법을 찾고
     * 지워진 결과값을 채운 수식을 반환하는 메서드
     */
    public String[] solution(String[] expressions) {
        ArrayDeque<String> incompleteExpressions = new ArrayDeque<>();
        boolean[] validBases = initializeValidBases();

        for (String expression : expressions) {
            String[] parts = expression.split(" ");
            boolean isComplete = true;
            int maxValue = 0;

            for (int i = 0; i < 5; i += 2) {
                if (!"X".equals(parts[i])) {
                    maxValue = Math.max(maxValue, getMaxDigit(parts[i]));
                } else {
                    incompleteExpressions.offer(expression);
                    isComplete = false;
                }
            }
            updateValidBases(validBases, maxValue);
            if (!isComplete) continue;
            validateExpression(parts, validBases);
        }

        return resolveIncompleteExpressions(incompleteExpressions, validBases);
    }

    /**
     * 초기 진법 배열을 설정합니다 (2~9 진법만 유효).
     */
    private boolean[] initializeValidBases() {
        boolean[] valid = new boolean[10];
        Arrays.fill(valid, true);
        valid[0] = valid[1] = false;
        return valid;
    }

    /**
     * 수식의 각 부분을 진법 변환해 유효성을 검증합니다.
     */
    private void validateExpression(String[] parts, boolean[] validBases) {
        for (int base = 2; base <= 9; base++) {
            if (!validBases[base]) continue;
            int left = Integer.parseInt(parts[0], base);
            int right = Integer.parseInt(parts[2], base);
            int expected = Integer.parseInt(parts[4], base);
            int result = parts[1].equals("+") ? left + right : left - right;
            if (result != expected) validBases[base] = false;
        }
    }

    /**
     * 미완성 수식을 채워서 결과 배열을 반환합니다.
     */
    private String[] resolveIncompleteExpressions(ArrayDeque<String> incompleteExpressions, boolean[] validBases) {
        String[] answer = new String[incompleteExpressions.size()];
        int idx = 0;

        while (!incompleteExpressions.isEmpty()) {
            String expression = incompleteExpressions.poll();
            Set<String> results = new HashSet<>();
            String[] parts = expression.split(" ");

            for (int base = 2; base <= 9; base++) {
                if (!validBases[base]) continue;
                int left = Integer.parseInt(parts[0], base);
                int right = Integer.parseInt(parts[2], base);
                int result = parts[1].equals("+") ? left + right : left - right;
                results.add(Integer.toString(result, base));
            }

            parts[4] = results.size() >= 2 ? "?" : results.iterator().next();
            answer[idx++] = String.join(" ", parts);
        }

        return answer;
    }

    /**
     * 수식에서 가장 큰 숫자를 확인하여 진법 제한을 업데이트합니다.
     */
    private void updateValidBases(boolean[] validBases, int maxValue) {
        for (int i = 2; i <= maxValue; i++) {
            validBases[i] = false;
        }
    }

    /**
     * 주어진 수식에서 가장 큰 숫자를 찾습니다.
     */
    private int getMaxDigit(String num) {
        int maxDigit = 0;
        for (char c : num.toCharArray()) {
            maxDigit = Math.max(maxDigit, c - '0');
        }
        return maxDigit;
    }
}
