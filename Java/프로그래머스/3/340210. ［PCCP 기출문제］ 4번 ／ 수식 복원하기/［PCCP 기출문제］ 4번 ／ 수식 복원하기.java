import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        List<String> completeExpression = new ArrayList<>();
        List<String> problemExpression = new ArrayList<>();

        for (String expression : expressions) {
            if (!expression.contains("X")) {
                completeExpression.add(expression);
            } else {
                problemExpression.add(expression);
            }
        }

        String[] result = new String[problemExpression.size()];
        Map<Integer, Integer> expressionMap = new HashMap<>();
        expressionMap.put(2, 0); expressionMap.put(3, 0); expressionMap.put(4, 0);
        expressionMap.put(5, 0); expressionMap.put(6, 0); expressionMap.put(7, 0);
        expressionMap.put(8, 0); expressionMap.put(9, 0);

        int maxDigitInComplete = 0;
        int minBaseInComplete = 0;

        for (String ex : completeExpression) {
            String[] parts = ex.split(" ");
            int maxDigit= Math.max(getMaxDigit(parts[0]), getMaxDigit(parts[2]));
            maxDigit = Math.max(maxDigit, getMaxDigit(parts[4]));
            maxDigitInComplete = Math.max(maxDigit, maxDigitInComplete);
        }
        minBaseInComplete = maxDigitInComplete + 1;


        for (String ex : completeExpression) {
            String[] parts = ex.split(" ");
            String op = parts[1];

            for (int base = minBaseInComplete; base <= 9; base++) {
                check(parts, base, op, expressionMap);
            }
        }


        int maxDigitInProblem = 0;
        int minBaseInProblem = 0;

        for (String ex : problemExpression) {
            String[] parts = ex.split(" ");
            int maxDigit= Math.max(getMaxDigit(parts[0]), getMaxDigit(parts[2]));
            maxDigitInProblem = Math.max(maxDigit, maxDigitInProblem);
        }
        minBaseInProblem = maxDigitInProblem + 1;


        for (int i = 0; i < problemExpression.size(); i++) {
            String[] parts = problemExpression.get(i).split(" ");
            String op = parts[1];

            Set<String> resultSet = new HashSet<>();

            for (int base = minBaseInProblem; base <= 9; base++) {
                int value = expressionMap.get(base);
                if (value == completeExpression.size()) {
                    checkSet(parts, base, op, resultSet);
                }
            }


            if (resultSet.size() != 1) {
                result[i] = parts[0] + " " + parts[1] + " " + parts[2] + " " + parts[3] + " " + "?";
            } else {
                Iterator<String> it = resultSet.iterator();
                if (it.hasNext()) {
                    String value = it.next();
                    result[i] = parts[0] + " " + parts[1] + " " + parts[2] + " " + parts[3] + " " + value;
                }
            }
        }
        return result;
    }

    private void checkSet(String[] parts, int base, String op, Set<String> resultSet) {
         if (!isValidNumber(parts[0], base) || !isValidNumber(parts[2], base)) {
            return;
        }
        int num1 = Integer.parseInt(parts[0], base);
        int num2 = Integer.parseInt(parts[2], base);


        int res = op.equals("+") ? num1 + num2 : num1 - num2;

        String ans = Integer.toString(res, base);
        resultSet.add(ans);
    }

    private int getMaxDigit(String number) {
        int maxDigit = 0;
        for (char c : number.toCharArray()) {
            if (Character.isDigit(c)) {
                maxDigit = Math.max(maxDigit, Character.getNumericValue(c));
            }
        }
        return maxDigit;
    }

    private boolean isValidNumber(String number, int base) {
        for (char c : number.toCharArray()) {
            int digit = Character.getNumericValue(c);
            if (digit < 0 || digit >= base) {
                return false;
            }
        }
        return true;
    }

    private void check(String[] parts, int base, String op, Map<Integer, Integer> expressionMap) {
        if (!isValidNumber(parts[0], base) || !isValidNumber(parts[2], base) || !isValidNumber(parts[4], base)){
            return;
        }
        int num1 = Integer.parseInt(parts[0], base);
        int num2 = Integer.parseInt(parts[2], base);
        int res = op.equals("+") ? num1 + num2 : num1 - num2;

        String ans = Integer.toString(res, base);

        if (ans.equals(parts[4])) {
            expressionMap.merge(base, 1, Integer::sum);
        }
    }
}
