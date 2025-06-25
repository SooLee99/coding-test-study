import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] matrices1 = loadMatrixFromInput(br, n, m);
		int[][] matrices2 = loadMatrixFromInput(br, n, m);
		sumMatrices(matrices1, matrices2, n, m);

		br.close();

	}

	private static int[][] loadMatrixFromInput(BufferedReader br, int n, int m) throws IOException {
		int[][] matrices = new int[n][m];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int j = 0; j < m; j++) {
				matrices[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return matrices;
	}

	private static void sumMatrices(int[][] matrices1, int[][] matrices2, int n, int m) {
		// 두 행렬의 합을 계산하고 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(matrices1[i][j] + matrices2[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}