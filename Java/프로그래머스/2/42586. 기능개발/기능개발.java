import java.util.*;

class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> deploymentCounts = new ArrayList<>();
        Queue<Integer> remainingDaysQueue = calculateRemainingDays(progresses, speeds);

        while (!remainingDaysQueue.isEmpty()) {
            int currentMaxDays = remainingDaysQueue.poll();
            int deployCount = 1;

            while (!remainingDaysQueue.isEmpty() && remainingDaysQueue.peek() <= currentMaxDays) {
                deployCount++;
                remainingDaysQueue.poll();
            }
            deploymentCounts.add(deployCount);
        }
        return convertToArray(deploymentCounts);
    }

    /**
     * 남은 작업 일수를 계산하여 Queue에 저장
     */
    private Queue<Integer> calculateRemainingDays(int[] progresses, int[] speeds) {
        Queue<Integer> remainingDaysQueue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int remainingProgress = 100 - progresses[i];
            int requiredDays = (int) Math.ceil((double) remainingProgress / speeds[i]);
            remainingDaysQueue.offer(requiredDays);
        }
        return remainingDaysQueue;
    }

    /**
     * 리스트를 배열로 변환하는 메서드
     */
    private int[] convertToArray(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}