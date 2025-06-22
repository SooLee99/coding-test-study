import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N: 도현이가 가지고 있는 바구니 개수
		final int N = Integer.parseInt(st.nextToken());

		// 앞으로 입력받을 줄의 개수
		int lines = Integer.parseInt(st.nextToken());

		// 바구니를 초기화
		BasketLine basketLine = new BasketLine(N);

		// 입력받은 줄의 개수만큼 반복
		for(int i = 0; i < lines; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1; // 0-based index
			int end = Integer.parseInt(st.nextToken()) - 1;   // 0-based index
			basketLine.reverse(start, end);
		}
		
		// 최종 바구니 상태 출력
		basketLine.printBaskets();
	}

	// 바구니 
	static class BasketLine {
		private int n;
		private int[] baskets;

		public BasketLine(int n) {
			this.n = n;
			this.baskets = new int[n];
			for (int i = 0; i < n; i++) {
				baskets[i] = i + 1; // 초기화: 1부터 N까지
			}
		}

		public void reverse(int start, int end) {
			while (start < end) {
				int temp = baskets[start];
				baskets[start] = baskets[end];
				baskets[end] = temp;
				start++;
				end--;
			}
		}

		public void printBaskets() {
			for (int basket : baskets) {
				System.out.print(basket + " ");
			}
			System.out.println();
		}
	}
}