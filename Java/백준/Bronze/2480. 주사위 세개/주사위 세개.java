import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받기
        String[] input = br.readLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);
        long c = Long.parseLong(input[2]);
        
        long prizeMoney = 0;  // prizeMoney를 long 타입으로 변경
        
        // 동일한 값이 3개일 때
        if (a == b && b == c) {
            prizeMoney = 10000 + a * 1000;
        }
        // 두 개의 값이 같을 때
        else if (a == b || b == c || a == c) {
            if (a == b) {
                prizeMoney = 1000 + a * 100;
            } else if (b == c) {
                prizeMoney = 1000 + b * 100;
            } else {
                prizeMoney = 1000 + a * 100;
            }
        } else {
            // 모두 다른 값일 때 가장 큰 값 찾아서 상금 계산
            long max = Math.max(a, Math.max(b, c));
            prizeMoney = max * 100;
        }
        
        // 상금 출력
        System.out.println(prizeMoney);      
    }
}