import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader를 사용하여 입력을 빠르게 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력된 한 줄을 공백 기준으로 split하고 각각 long으로 변환하여 더함
        String[] input = br.readLine().split(" ");
        long A = Long.parseLong(input[0]);
        long B = Long.parseLong(input[1]);
        long C = Long.parseLong(input[2]);

        // A + B + C의 결과 출력
        System.out.println(A + B + C);
    }
}
