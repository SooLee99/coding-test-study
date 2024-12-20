import java.util.*;

class Solution {
    // 2~9진법중 하나
    // 덧셈, 뺄셈 수식만 나옴
    // 불확실한 결과값은 ?로 표시
    public String[] solution(String[] expressions) {
        // 결과값 지워진 수식 저장필요
        ArrayDeque<String> uncompleteExpression = new ArrayDeque<>();

        // 초기
        boolean[] valid = new boolean[10];
        Arrays.fill(valid, true);
        valid[0] = false;
        valid[1] = false;

        // 각 자리수 최대 숫자 + 1 진법 ~ 9 진법
        for (String expression : expressions) {
            String[] e = expression.split(" ");
            // System.out.println(Arrays.toString(e));

            // A +- B = C 이므로 0, 1, 2, 3, 4
            boolean complete = true;
            int maxValue = 0;
            for (int i = 0; i < 5; i += 2) {
                if (!"X".equals(e[i])) {
                    maxValue = Math.max(maxValue, getMaxValue(e[i]));
                } else {
                    // X 있으면 큐에 저장
                    uncompleteExpression.offer(expression);
                    complete = false;
                }
            }

            // maxValue를 통해 진법 갱신
            for (int i = 2; i <= maxValue; i++) {
                valid[i] = false;
            }

            // 현재 진법 범위로 실제 계산 해봄
            if (!complete) {
                continue;
            }

            for (int jinbup = 2; jinbup <= 9; jinbup++) {
                if (!valid[jinbup]) {
                    continue;
                }

                int result = Integer.parseInt(e[0], jinbup);
                if ("+".equals(e[1])) {
                    result += Integer.parseInt(e[2], jinbup);
                } else {
                    result -= Integer.parseInt(e[2], jinbup);
                }
                int trueResult = Integer.parseInt(e[4], jinbup);

                // System.out.printf("진법 %d : %d = %d\n", jinbup, result, trueResult);

                if (trueResult != result) {
                    valid[jinbup] = false;
                }
            }
        }

        // System.out.println(Arrays.toString(valid));

        String[] answer = new String[uncompleteExpression.size()];
        int idx = 0;
        // 남은 수식 진법으로 계산
        while (!uncompleteExpression.isEmpty()) {
            String expression = uncompleteExpression.poll();
            Set<String> resultSet = new HashSet<>();
            String[] e = expression.split(" ");

            for (int jinbup = 2; jinbup <= 9; jinbup++) {
                if (!valid[jinbup]) {
                    continue;
                }

                int result = Integer.parseInt(e[0], jinbup);
                if ("+".equals(e[1])) {
                    result += Integer.parseInt(e[2], jinbup);
                } else {
                    result -= Integer.parseInt(e[2], jinbup);
                }

                e[4] = Integer.toString(result, jinbup);

                resultSet.add(e[4]);
            }

            // resultSet이 2이상이면 ?로 결과
            if (resultSet.size() >= 2) {
                answer[idx] = expression.replace("X", "?");
            } else {
                answer[idx] = expression.replace("X", e[4]);
            }
            idx++;
        }

        return answer;
    }

    private String convert(int num, int jinbup) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % jinbup);
            num /= jinbup;
        }
        return sb.reverse().toString();
    }

    private int getMaxValue(String num) {
        int n = Integer.parseInt(num);
        int maxValue = 0;
        while (n > 0) {
            maxValue = Math.max(maxValue, n % 10);
            n = n / 10;
        }
        return maxValue;
    }
}
