import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().sol();
    }

    void sol() throws IOException {
        HashMap<Integer, Integer> map = new HashMap<>();

        // 첫 번째 입력 (정수의 개수 N)
        int n = read();

        // n개의 정수를 입력받아 그 등장 횟수 세기
        for (int i = 0; i < n; i++) {
            int num = read();
            map.put(num, map.getOrDefault(num, 0) + 1);  // 등장 횟수 갱신
        }

        // 찾고자 하는 숫자 v 입력받기
        int v = read();

        // 결과 출력 (v가 등장한 횟수 출력)
        System.out.println(map.getOrDefault(v, 0));
    }

    // 숫자 입력을 빠르게 받기 위한 메서드
    int read() throws IOException {
        int c = System.in.read(), n = 0, minus = 1;

        // 음수 처리
        if (c == '-') {
            minus = -1;
        } else {
            n = c & 15;  // 첫 번째 숫자 처리
        }

        // 숫자가 여러 자릿수일 경우 처리
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n * minus;
    }
}
