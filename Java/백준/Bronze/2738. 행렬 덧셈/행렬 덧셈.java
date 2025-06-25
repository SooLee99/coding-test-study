import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		readMatrixSum(br, n, m);
	}

	// 두 행렬을 읽으며 바로 합산하여 결과 반환
	private static int[][] readMatrixSum(BufferedReader br, int n, int m) throws IOException {
		int[][] result = new int[n][m];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 첫 번째 행렬 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 두 번째 행렬 입력 + 즉시 합산
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				result[i][j] += Integer.parseInt(st.nextToken());
				sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
		return result;
	}
}
