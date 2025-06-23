import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		int count = 0;

		for (int i = 0; i < word.length(); ) {
			// 3글자 크로아티아 알파벳: dz=
			if (i + 2 < word.length() && word.charAt(i) == 'd' && word.charAt(i + 1) == 'z' && word.charAt(i + 2) == '=') {
				count++;
				i += 3;
			}
			// 2글자 크로아티아 알파벳
			else if (i + 1 < word.length()) {
				String pair = word.substring(i, i + 2);
				if (pair.equals("c=") || pair.equals("c-") || pair.equals("d-") ||
				    pair.equals("lj") || pair.equals("nj") || pair.equals("s=") || pair.equals("z=")) {
					count++;
					i += 2;
				} else {
					count++;
					i++;
				}
			}
			// 일반 문자
			else {
				count++;
				i++;
			}
		}

		System.out.println(count);
	}
}
