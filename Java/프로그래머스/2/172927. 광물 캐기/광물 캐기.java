import java.util.*;

class Solution {
    public static int solution(int[] picks, String[] minerals) {
        Mining mining = new Mining(picks, minerals);
        return mining.calculateMinFatigue();
    }
}


class Mining {
    private final int[] picks;                      // 각 곡괭이의 개수
    private final String[] minerals;                // 광물 배열
    private final int[][] fatigue;                  // 곡괭이별 피로도 테이블
    private final List<int[]> mineralGroups;        // 그룹화된 광물 데이터

    public Mining(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;

        // 피로도 테이블 (곡괭이별 광물 피로도)
        this.fatigue = new int[][]{
                {1, 1, 1},    // 다이아몬드 곡괭이
                {5, 1, 1},    // 철 곡괭이
                {25, 5, 1}    // 돌 곡괭이
        };

        this.mineralGroups = new ArrayList<>();
        groupMinerals(); // 광물을 그룹화
    }

    /**
     * 1. 광물을 그룹화하여 그룹당 다이아, 철, 돌의 개수를 배열로 저장
     */
    private void groupMinerals() {
        int totalPicks = Arrays.stream(picks).sum(); // 사용할 수 있는 곡괭이 수 계산

        // 광물을 5개씩 그룹화
        for (int i = 0; i < minerals.length && totalPicks > 0; i += 5) {
            int[] group = new int[3]; // [diamond, iron, stone]
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                switch (minerals[j]) {
                    case "diamond": group[0]++; break;
                    case "iron": group[1]++; break;
                    case "stone": group[2]++; break;
                }
            }
            mineralGroups.add(group);
            totalPicks--; // 그룹 하나당 곡괭이 하나 사용
        }

    }

    /**
     * 2. 최적의 곡괭이를 선택해 그룹을 처리하고 최소 피로도를 계산
     */
    public int calculateMinFatigue() {
        int totalFatigue = 0;

        // 각 그룹에 대해 최적의 곡괭이를 선택
        for (int[] group : mineralGroups) {
            int pickIndex = getAvailablePickIndex();
            if (pickIndex == -1) break; // 사용할 곡괭이가 없는 경우 중단
            totalFatigue += calculateFatigue(group, fatigue[pickIndex]);
            picks[pickIndex]--; // 사용한 곡괭이 개수 감소
        }

        return totalFatigue;
    }

    /**
     * 현재 사용할 수 있는 곡괭이의 인덱스를 반환 (우선순위: 다이아몬드 > 철 > 돌)
     */
    private int getAvailablePickIndex() {
        for (int i = 0; i < picks.length; i++) {
            if (picks[i] > 0) return i;
        }
        return -1; // 사용할 곡괭이가 없음
    }

    /**
     * 특정 그룹에서 주어진 곡괭이로 채굴했을 때의 피로도 계산
     */
    private int calculateFatigue(int[] group, int[] pickFatigue) {
        return group[0] * pickFatigue[0] + group[1] * pickFatigue[1] + group[2] * pickFatigue[2];
    }
}