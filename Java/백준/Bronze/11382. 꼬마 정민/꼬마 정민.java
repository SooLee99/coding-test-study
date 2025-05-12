import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO: 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // TODO: 입력받은 숫자들을 나눔
        String[] input = br.readLine().split(" ");
        long A = Long.parseLong(input[0]);
        long B = Long.parseLong(input[1]);
        long C = Long.parseLong(input[2]);

        // A + B + C의 결과 출력
        System.out.println(A + B + C);
    }
}