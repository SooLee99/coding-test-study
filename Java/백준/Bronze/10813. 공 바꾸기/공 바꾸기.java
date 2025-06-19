import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 바구니 초기화: Basket 클래스에서 처리
        Basket[] baskets = Basket.init(N);

        // 스왑 연산 수행
        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine(), " ");
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            baskets[i].swapWith(baskets[j]);
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(baskets[i].getBall().getId());
            if (i < N) sb.append(' ');
        }
        System.out.println(sb.toString());
    }
}

class Ball {
    private final int id;

    public Ball(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Basket {
    private Ball ball;

    private Basket(int id) {
        this.ball = new Ball(id);
    }

    /**
     * 지정된 크기로 바구니 배열을 생성하고 초기 공을 배치한다
     */
    public static Basket[] init(int size) {
        Basket[] baskets = new Basket[size + 1];
        for (int i = 1; i <= size; i++) {
            baskets[i] = new Basket(i);
        }
        return baskets;
    }

    public Ball getBall() {
        return ball;
    }

    /**
     * 다른 바구니와 공을 교환한다
     */
    public void swapWith(Basket other) {
        Ball tmp = this.ball;
        this.ball = other.ball;
        other.ball = tmp;
    }
}
