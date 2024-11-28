public class Solution {
    public String solution(String new_id) {
        // 각 단계를 개별 메서드로 분리하여 처리
        IdProcessor idProcessor = new IdProcessor();
        return idProcessor.processId(new_id);
    }
}

// SOLID 원칙 적용: OCP를 적용하여 새로운 처리 단계가 필요할 경우 확장 가능
class IdProcessor {
    
    public String processId(String new_id) {
        String id = new_id;

        id = toLowerCase(id);        // 1단계: 소문자로 변환
        id = removeInvalidChars(id); // 2단계: 허용되지 않은 문자 제거
        id = replaceConsecutiveDots(id); // 3단계: 연속된 마침표 치환
        id = removeEdgeDots(id);     // 4단계: 처음과 끝의 마침표 제거
        id = replaceEmptyWithA(id);  // 5단계: 빈 문자열 처리
        id = truncateToMaxLength(id); // 6단계: 길이 초과 처리
        id = appendCharsIfTooShort(id); // 7단계: 길이 보정

        return id;
    }

    // 1단계: 소문자로 변환
    private String toLowerCase(String id) {
        return id.toLowerCase();
    }

    // 2단계: 허용된 문자만 남김
    private String removeInvalidChars(String id) {
        return id.replaceAll("[^a-z0-9-_.]", "");
    }

    // 3단계: 연속된 마침표 하나로 치환
    private String replaceConsecutiveDots(String id) {
        return id.replaceAll("\\.{2,}", ".");
    }

    // 4단계: 처음과 끝의 마침표 제거
    private String removeEdgeDots(String id) {
        return id.replaceAll("^\\.|\\.$", "");
    }

    // 5단계: 빈 문자열 처리
    private String replaceEmptyWithA(String id) {
        return id.isEmpty() ? "a" : id;
    }

    // 6단계: 길이가 16자 이상이면 15자로 잘라내고 끝의 마침표 제거
    private String truncateToMaxLength(String id) {
        if (id.length() >= 16) {
            id = id.substring(0, 15);
        }
        return id.replaceAll("\\.$", "");
    }

    // 7단계: 길이가 2자 이하라면 마지막 문자를 길이가 3이 될 때까지 반복
    private String appendCharsIfTooShort(String id) {
        while (id.length() < 3) {
            id += id.charAt(id.length() - 1);
        }
        return id;
    }
}