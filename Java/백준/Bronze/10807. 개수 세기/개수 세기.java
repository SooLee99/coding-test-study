import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 번째 줄에서 N 입력받기
        int N = Integer.parseInt(br.readLine());
        
        // 두 번째 줄에서 N개의 정수 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 세 번째 줄에서 찾고자 하는 값 v 입력받기
        int v = Integer.parseInt(br.readLine());
        
        // N개의 정수 중에서 v의 개수 세기
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (Integer.parseInt(st.nextToken()) == v) {
                count++;
            }
        }
        
        // 결과 출력
        System.out.println(count);
    }
}
