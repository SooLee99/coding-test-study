import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int groupWordCount = 0;

		for (int i = 0; i < n; i++) {
			String word = br.readLine();
			if (isGroupWord(word)) {
				groupWordCount++;
			}
		}

		System.out.println(groupWordCount);
	}

	public static boolean isGroupWord(String word) {
		Set<Character> seen = new HashSet<>();
		char prev = 0;

		for (int i = 0; i < word.length(); i++) {
			char current = word.charAt(i);

			if (current != prev) {
				if (seen.contains(current)) {
					return false; // 끊겼다가 다시 나타남 → 그룹 단어 아님
				}
				seen.add(current);
			}

			prev = current;
		}

		return true;
	}
}