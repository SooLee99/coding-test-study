import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);
        long c = Long.parseLong(input[2]);
        
        long prizeMoney = 0; 
        
        if(a == b && b == c) {
            prizeMoney = 10000 + a * 1000;
        } else if(a == b || b == c || a == c) {
            if (a == b) {
                prizeMoney = 1000 + a * 100;
            } else if (b == c) {
                prizeMoney = 1000 + b * 100;
            } else {
                prizeMoney = 1000 + a * 100;
            }
        } else {
            long max = Math.max(a, Math.max(b, c));
            prizeMoney = (int)(max * 100);
        }
        
        // 상금 출력
        System.out.println(prizeMoney);      
    }
}