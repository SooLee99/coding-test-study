import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long input = Long.parseLong(br.readLine());  // 입력값을 long 타입으로 받음
        long value = input / 4;  // "long"의 개수를 계산
        
        // StringBuilder를 사용하여 문자열을 생성
        StringBuilder sb = new StringBuilder();
        
        // "long"을 value 번 추가
        for (int i = 0; i < value; i++) {
            sb.append("long ");  // "long"을 추가
        }
        
        // 마지막에 "int" 추가
        sb.append("int");
        
        // 최종 결과 출력
        System.out.print(sb.toString());
    }
}
