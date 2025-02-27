import java.util.*;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        List<Set<Integer>> possibleCodes = new ArrayList<>();

        generateCombinations(n, 5, new HashSet<>(), 1, possibleCodes);

        return filterValidCodes(possibleCodes, q, ans);
    }

    private void generateCombinations(int n, int size, Set<Integer> current, int start, List<Set<Integer>> result) {
        if (current.size() == size) {
            result.add(new HashSet<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            generateCombinations(n, size, current, i + 1, result);
            current.remove(i);
        }
    }

    private int filterValidCodes(List<Set<Integer>> possibleCodes, int[][] q, int[] ans) {
        int count = 0;

        for (Set<Integer> code : possibleCodes) {
            boolean isValid = true;

            for (int i = 0; i < q.length; i++) {
                int matchCount = 0;
                for (int num : q[i]) {
                    if (code.contains(num)) {
                        matchCount++;
                    }
                }
                if (matchCount != ans[i]) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                count++;
            }
        }

        return count;
    }
}
