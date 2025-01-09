import java.util.*;
import java.util.stream.*;

class Solution {
    // ANSI 색상 코드
    final String RESET = "\u001B[0m"; // 색상 초기화
    final String RED = "\u001B[31m"; // 빨간색
    final String GREEN = "\u001B[32m"; // 초록색
    final String BLUE = "\u001B[34m"; // 파란색

    public int[] solution(String msg) {
        // TODO: 메시지를 한 글자씩 큐에 넣음
        Queue<String> queue = new LinkedList<>();
        for (String word : msg.split("")) {
            queue.offer(word);
        }
        // System.out.println("큐: " + queue);

        // TODO: A-Z로 초기화된 사전을 리스트로 생성
        List<String> list = IntStream.range('A', 'Z' + 1)
                .mapToObj(val -> String.valueOf((char) val))
                .collect(Collectors.toList());

        // TODO: 사전에 있는 값인지 확인하고, 없으면 사전에 추가
        /*
         * queue.peek(): 큐에서 다음 글자를 확인만 하고, 제거하지는 않음.
         * queue.poll(): 큐에서 다음 글자를 가져오고 제거.
         */
        List<Integer> result = new ArrayList<>();
        String value = "";

        // 큐가 비어있지 않거나, value가 비어있지 않을 때까지 반복
        while (!queue.isEmpty() || !"".equals(value)) {
            System.out.println();
            System.out.println(BLUE + "value: " + value + ", queue: " + queue.peek() + RESET);
            if (list.contains(value + queue.peek())) {
                value = value + queue.poll();
                System.out.println(GREEN + "사전에 있음: " + value + RESET);
                continue;
            }

            System.out.println(RED + "사전에 없음, 추가: " + value + queue.peek() + RESET);
            list.add(value + queue.peek());

            System.out.println("사전 추가됨: " + (value + queue.peek()) + " (색인: " + list.size() + ")");
            result.add(list.indexOf(value) + 1);

            System.out.println("출력값 추가: " + (list.indexOf(value) + 1));
            value = "";
        }

        return result.stream()
                .mapToInt(val -> val.intValue())
                .toArray();
    }

}
