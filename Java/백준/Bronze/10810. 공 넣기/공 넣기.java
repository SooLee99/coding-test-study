import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();

        // 1) N(바구니 개수), M(명령 개수)
        int n = fr.nextInt();
        int m = fr.nextInt();

        // 2) 바구니 초기화: new int[n] 은 자동으로 모두 0으로 채워짐
        int[] baskets = new int[n];

        // 3) M개의 명령 처리: [i..j] 구간을 k로 덮어쓰기
        for (int cmd = 0; cmd < m; cmd++) {
            int i = fr.nextInt() - 1;
            int j = fr.nextInt() - 1;
            int k = fr.nextInt();
            for (int idx = i; idx <= j; idx++) {
                baskets[idx] = k;
            }
        }

        // 4) 결과 출력: 한 번에 조합
        StringBuilder sb = new StringBuilder(n * 2);
        for (int v : baskets) {
            sb.append(v).append(' ');
        }
        System.out.println(sb.toString().trim());
    }

    // BufferedReader + StringTokenizer 조합으로 빠르게 토큰 단위 읽기
    static class FastReader {
        final BufferedReader br;
        StringTokenizer st;
        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine(), " ");
            }
            return st.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
