import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br   = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        BasketGroup group = new BasketGroup(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end   = Integer.parseInt(st.nextToken());
            int k     = Integer.parseInt(st.nextToken());
            group.applyCommand(start, end, k);
        }

        StringBuilder sb = new StringBuilder();
        for (int val : group.getFinalStates()) {
            sb.append(val).append(' ');
        }
        System.out.println(sb.toString().trim());
    }

    // 한 개의 바구니 슬롯
    static class Basket {
        private int lastValue;

        public Basket() {
            this.lastValue = 0;  // 초기값 0
        }

        // k 로 덮어쓰기
        public void setValue(int k) {
            this.lastValue = k;
        }

        public int getLast() {
            return lastValue;
        }
    }

    // 여러 바구니를 한데 묶어 관리하는 그룹
    static class BasketGroup {
        private final List<Basket> baskets;

        public BasketGroup(int n) {
            baskets = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                baskets.add(new Basket());
            }
        }

        /**
         * 1-based 구간 [start..end] 의 모든 바구니를 k 로 덮어쓴다
         */
        public void applyCommand(int start, int end, int k) {
            for (int idx = start - 1; idx < end; idx++) {
                baskets.get(idx).setValue(k);
            }
        }

        /** 각 바구니의 마지막 값을 리스트로 반환 */
        public List<Integer> getFinalStates() {
            List<Integer> result = new ArrayList<>(baskets.size());
            for (Basket b : baskets) {
                result.add(b.getLast());
            }
            return result;
        }
    }
}
