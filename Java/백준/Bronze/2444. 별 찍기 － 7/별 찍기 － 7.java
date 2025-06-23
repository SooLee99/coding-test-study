import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		// 위쪽 피라미드
		for (int i = 1; i <= n; i++) {
			sb.append(" ".repeat(n - i));
			sb.append("*".repeat(2 * i - 1));
			sb.append("\n");
		}

		// 아래쪽 역피라미드
		for (int i = n - 1; i >= 1; i--) {
			sb.append(" ".repeat(n - i));
			sb.append("*".repeat(2 * i - 1));
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
