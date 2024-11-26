import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int continuousBandageTime = 0;                      // 붕대를 감는 시간
        int currentHealth = health;                         // 캐릭터의 현재 체력
        int lastAttackTime = 0;                             // 마지막 공격 시각 추적
        Map<Integer, Integer> attackMap = new HashMap<>();  // 공격 정보를 저장할 맵

        // TODO: 1. 공격 정보를 Map에 저장하고 마지막 공격 시간을 추적
        // 예시 데이터(공격 정보, 피해량): [[2, 10], [9, 15], [10, 5], [11, 5]]
        for (int[] attack : attacks) {
            attackMap.put(attack[0], attack[1]);                    // 공격 시간과 피해량을 Map에 저장
            lastAttackTime = Math.max(lastAttackTime, attack[0]);   // 마지막 공격 시간 업데이트
        }

        // TODO: 2. 1초부터 마지막 공격 시각까지 시뮬레이션
        for (int currentTime = 1; currentTime <= lastAttackTime; currentTime++) {
            // (1) 공격이 발생한 경우
            if (attackMap.containsKey(currentTime)) {
                int damage = attackMap.get(currentTime);    // 피해량 가져오기
                currentHealth -= damage;                    // 체력 감소
                continuousBandageTime = 0;                  // 붕대 감는 시간 초기화 (공격받아 취소됨)
            }

            /* (2) 공격이 없는 경우: 체력 회복
             * bandage: [시전 시간 t, 1초당 회복량 x, 추가 회복량 y]
             *   - t: 연속으로 붕대를 감아야 하는 시간 (초)
             *   - x: 1초당 체력 회복량
             *   - y: t초 동안 연속 성공 시 추가로 회복되는 체력량
             */
            else {
                currentHealth += bandage[1];                // 1초당 회복량만큼 체력 회복
                continuousBandageTime++;                    // 연속 붕대 감기 시간 증가

                // (2.1) 연속 붕대 감기 성공 처리 [붕대를 감는 시간과 붕대를 감는데 걸리는 시간을 비교]
                if (continuousBandageTime == bandage[0]) {
                    currentHealth += bandage[2];            // 추가 회복량 적용
                    continuousBandageTime = 0;              // 연속 성공 초기화
                }

                // (2.2) 체력이 최대 체력을 초과한 경우
                if (currentHealth > health) {
                    currentHealth = health;                 // 최대 체력으로 설정
                }
            }

            // (3) 체력이 0 이하로 떨어진 경우
            if (currentHealth <= 0) {
                return -1; // 캐릭터 사망
            }
        }

        // TODO: 3. 남은 체력 반환
        return currentHealth;
    }
}