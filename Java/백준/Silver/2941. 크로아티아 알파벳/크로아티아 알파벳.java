import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] inputChars = br.readLine().toCharArray();
		int count = 0;

		for (int i = 0; i < inputChars.length; i++) {
			// 3글자 크로아티아 문자: dz=
			if (i + 2 < inputChars.length &&
			    inputChars[i] == 'd' && inputChars[i + 1] == 'z' && inputChars[i + 2] == '=') {
				count++;
				i += 2;
			}
			// 2글자 크로아티아 문자
			else if (i + 1 < inputChars.length && isCroatian2Letter(inputChars[i], inputChars[i + 1])) {
				count++;
				i += 1;
			}
			// 일반 문자
			else {
				count++;
			}
		}

		System.out.println(count);
	}

	private static boolean isCroatian2Letter(char a, char b) {
		return (a == 'c' && (b == '=' || b == '-')) ||
		       (a == 'd' && b == '-') ||
		       (a == 'l' && b == 'j') ||
		       (a == 'n' && b == 'j') ||
		       (a == 's' && b == '=') ||
		       (a == 'z' && b == '=');
	}
}