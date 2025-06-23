import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] inputChars = br.readLine().toCharArray();
		int count = 0;                // 문자 개수

		for (int i = 0; i < inputChars.length; i++) {
			if (inputChars.length-2 > i) {
				if (isCroatian3Letter(inputChars[i], inputChars[i + 1], inputChars[i + 2])) {
					count++;
					i += 2; // 3글자이므로 인덱스를 2 증가
				}
				// 2글자 비교
				else if (isCroatian2Letter(inputChars[i], inputChars[i + 1])) {
					count++;
					i += 1; // 2글자이므로 인덱스를 1 증가
				}
				// 일반 문자
				else {
					count += 1;
				}
			} else if(inputChars.length - 1 > i) {
				// 2글자 비교
				if (isCroatian2Letter(inputChars[i], inputChars[i + 1])) {
					count++;
					i += 1;
				}
				// 일반 문자
				else {
					count += 1;
				}
			} else {
				// 마지막 문자 처리
				count += 1;
			}
		}

		System.out.println(count);
	}

	public static boolean isCroatian3Letter(char a, char b, char c){
		String comparativeString3 = String.valueOf(a) + String.valueOf(b) + String.valueOf(c);
		return comparativeString3.equals("dz=");
	}

	public static boolean isCroatian2Letter(char a, char b){
		String comparativeString2 = String.valueOf(a) + String.valueOf(b);
		return comparativeString2.equals("lj") || comparativeString2.equals("nj") ||
			comparativeString2.equals("c=") || comparativeString2.equals("c-") ||
			comparativeString2.equals("d-") || comparativeString2.equals("s=") ||
			comparativeString2.equals("z=");
	}
}