import java.util.*;

class Solution {

    List<String> answerList = new ArrayList<>();
    Map<String, Integer> hashMap = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        /** orders: 고객이 주문한 메뉴 항목들
         * [ABCFG, AC, CDE, ACDE, BCFG, ACDEH] */

        /** course: 메뉴 항목들을 조합할 개수
         * [2, 3, 4] */

        // TODO: 고객이 주문한 메뉴들(orders) 중에서 특정 개수(course)의 맞게 메뉴 항목들을 조합하고,
        //  가장 많이 주문한 조합을 찾아내는 것.

        // [A, C] == [C, A]는 동일한 메뉴이다.
        // TODO: 1. Order 정렬 : 동일한 메뉴가 중복으로 처리되는 것을 방지하기 위함.
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        // TODO: 2. 각 order를 기준으로 courseLength 만큼의 조합 만들기
        for (int courseLength : course) {
            for (String order : orders) {
                combination("", order, courseLength);
            }

            // TODO: 3. 가장 많이 나온 조합을 answerList에 저장
            if (!hashMap.isEmpty()) {
                List<Integer> countList = new ArrayList<>(hashMap.values());
                int max = Collections.max(countList);

                if (max > 1) {
                    for (String key : hashMap.keySet()) {
                        if (hashMap.get(key) == max) {
                            answerList.add(key);
                        }
                    }
                }
                hashMap.clear();
            }
        }

        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    public void combination(String order, String others, int count) {

        // 탈출 조건: count가 0이면 조합 완성
        if (count == 0) {
            hashMap.put(order, hashMap.getOrDefault(order, 0) + 1);
            return;
        }

        // others의 각 문자에 대해 재귀적으로 조합 생성
        for (int i = 0; i < others.length(); i++) {
            combination(order + others.charAt(i), others.substring(i + 1), count - 1);
        }
    }
}
