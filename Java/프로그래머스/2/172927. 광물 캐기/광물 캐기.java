import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        MiningOptimizer optimizer = new MiningOptimizer(picks, minerals);
        return optimizer.calculateMinFatigue();
    }
}

class MiningOptimizer {
    private final int[] picks;
    private final String[] minerals;
    private final int[][] fatigue;
    private List<MineralGroup> groups;

    public MiningOptimizer(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = minerals;
        this.fatigue = new int[][]{
                {1, 1, 1},
                {5, 1, 1},
                {25, 5, 1}
        };
        this.groups = new ArrayList<>();
        groupMinerals();
    }

    private void groupMinerals() {
        int totalPicks = Arrays.stream(picks).sum();
        for (int i = 0; i < minerals.length && totalPicks > 0; i += 5) {
            int diamond = 0, iron = 0, stone = 0;
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                switch (minerals[j]) {
                    case "diamond": diamond++; break;
                    case "iron": iron++; break;
                    case "stone": stone++; break;
                }
            }
            groups.add(new MineralGroup(diamond, iron, stone));
            totalPicks--;
        }

        // 돌 곡괭이 기준으로 내림차순 정렬
        groups.sort((g1, g2) -> g2.calculateFatigue(fatigue[2]) - g1.calculateFatigue(fatigue[2]));
    }

    public int calculateMinFatigue() {
        int totalFatigue = 0;

        for (MineralGroup group : groups) {
            int pickIndex = getAvailablePickIndex();
            if (pickIndex == -1) break;
            totalFatigue += group.calculateFatigue(fatigue[pickIndex]);
            picks[pickIndex]--;
        }

        return totalFatigue;
    }

    private int getAvailablePickIndex() {
        for (int i = 0; i < picks.length; i++) {
            if (picks[i] > 0) return i;
        }
        return -1;
    }
}

class MineralGroup {
    private final int diamond;
    private final int iron;
    private final int stone;

    public MineralGroup(int diamond, int iron, int stone) {
        this.diamond = diamond;
        this.iron = iron;
        this.stone = stone;
    }

    public int calculateFatigue(int[] pickFatigue) {
        return diamond * pickFatigue[0] + iron * pickFatigue[1] + stone * pickFatigue[2];
    }
}
