import java.io.*;
import java.util.*;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        // 첫째 줄 입력 받기: 도현이가 가진 바구니의 총 개수 N (1번 ~ N번까지)
        int n = Integer.parseInt(st.nextToken());

        // 1) 바구니들을 담을 리스트 선언 및 초기화
        List<List<Integer>> baskets = new ArrayList<>(n);

        // 2) n개의 빈 ArrayList<Integer> 생성해서 추가
        for (int i = 0; i < n; i++) {
            baskets.add(new ArrayList<>());
            // 가장 처음 바구니에는 공이 들어가지 않음. => 0
            baskets.get(i).add(0);
        }
        
        // 둘째 줄 입력 받기: 더 입력받을 줄의 개수 M
        int m = Integer.parseInt(st.nextToken());
        
        // 3) 셋째 줄부터 M줄: 각 명령(i, j, k)을 읽어서 처리
        for (int cmd = 0; cmd < m; cmd++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());  // 시작 바구니 번호
            int j = Integer.parseInt(st.nextToken());  // 끝 바구니 번호
            int k = Integer.parseInt(st.nextToken());  // 채울 값

            // i번~j번 바구니에 k로 덮어쓰기
            // 배열 인덱스는 0부터 시작하므로 (i-1)에서 (j-1)까지
            for (int idx = i - 1; idx <= j - 1; idx++) {
                baskets.get(idx).add(k);
            }
        }
        
         // 3) 결과: 각 바구니의 “마지막 값”만 꺼내서 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            List<Integer> list = baskets.get(i);
            // list.size()-1 위치가 마지막으로 추가된 값
            sb.append(list.get(list.size() - 1)).append(' ');
        }
        // 맨 끝 공백 제거하고 출력
        System.out.println(sb.toString().trim());
    }
}