import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] arr = new boolean[31];

		// 총 30명 중에 과제 제출한 28명 확인 후 안낸 2명 확인
		for(int i = 0; i < 28; i++) {
			// 입력받은 번호를 배열의 인덱스로 사용하여 해당 인덱스의 값을 true로 설정
			int attendanceNo = Integer.parseInt(br.readLine());
			arr[attendanceNo] = true;
		}

		// 1부터 30까지 반복하면서 제출하지 않은 학생의 번호를 출력
		for(int i = 1; i <= 30; i++) {
			// 배열의 해당 인덱스가 false이면 제출하지 않은 학생
			if(Boolean.FALSE.equals(arr[i]))
				System.out.println(i);
		}
	}
}