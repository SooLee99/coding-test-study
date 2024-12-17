import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        // FileInfo 객체 리스트 생성
        List<FileInfo> fileList = new ArrayList<>();
        
        for (int i = 0; i < files.length; i++) {
            fileList.add(new FileInfo(files[i], i));
        }
        
        // 커스텀 Comparator로 정렬
        Collections.sort(fileList, new Comparator<FileInfo>() {
            @Override
            public int compare(FileInfo o1, FileInfo o2) {
                // HEAD 비교 (대소문자 무시)
                int headCompare = o1.head.toLowerCase().compareTo(o2.head.toLowerCase());
                if (headCompare != 0) {
                    return headCompare;
                }
                // NUMBER 비교 (정수 값으로)
                if (o1.number != o2.number) {
                    return o1.number - o2.number;
                }
                // 원본 인덱스 비교 (입력 순서 유지)
                return o1.index - o2.index;
            }
        });
        
        // 정렬된 파일명 배열 생성
        String[] answer = new String[files.length];
        for (int i = 0; i < fileList.size(); i++) {
            answer[i] = fileList.get(i).original;
        }
        
        return answer;
    }
    
    // FileInfo 클래스 정의
    class FileInfo {
        String original; // 원본 파일명
        String head;     // HEAD 부분
        int number;      // NUMBER 부분
        int index;       // 원본 인덱스
        
        FileInfo(String file, int index) {
            this.original = file;
            this.index = index;
            parseFile(file);
        }
        
        // 파일명을 HEAD, NUMBER, TAIL로 분리
        private void parseFile(String file) {
            int len = file.length();
            int i = 0;
            // HEAD 추출
            StringBuilder headBuilder = new StringBuilder();
            while (i < len && !Character.isDigit(file.charAt(i))) {
                headBuilder.append(file.charAt(i));
                i++;
            }
            this.head = headBuilder.toString();
            
            // NUMBER 추출 (최대 5자리)
            StringBuilder numberBuilder = new StringBuilder();
            int numberCount = 0;
            while (i < len && Character.isDigit(file.charAt(i)) && numberCount < 5) {
                numberBuilder.append(file.charAt(i));
                i++;
                numberCount++;
            }
            this.number = Integer.parseInt(numberBuilder.toString());
            
            // TAIL은 필요 없으므로 생략
        }
    }
}
