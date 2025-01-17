import java.util.ArrayList;
import java.util.List;

class MusicInfo {
    private String title;   // 제목
    private int playTime; // 재생 시간 (분 단위)
    private String playedMelody; // 재생된 멜로디

    public MusicInfo(String start, String end, String title, String melody) {
        this.title = title;

        // 재생 시간 계산
        this.playTime = calculatePlayTime(start, end);

        // 멜로디 변환 및 재생된 멜로디 생성
        String changedMelody = changeMelody(melody);
        this.playedMelody = generatePlayedMelody(changedMelody, this.playTime);
    }

    // 시간 문자열("HH:MM")을 분 단위로 변환하여 재생 시간 계산
    private int calculatePlayTime(String start, String end) {
        String[] startParts = start.split(":");
        String[] endParts = end.split(":");
        int startMinutes = Integer.parseInt(startParts[0]) * 60 + Integer.parseInt(startParts[1]);
        int endMinutes = Integer.parseInt(endParts[0]) * 60 + Integer.parseInt(endParts[1]);
        return endMinutes - startMinutes;
    }

    // 멜로디를 재생 시간에 맞춰 반복하여 생성
    private String generatePlayedMelody(String melody, int playTime) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < playTime; i++) {
            result.append(melody.charAt(i % melody.length()));
        }
        return result.toString();
    }

    // 멜로디 변환: #이 포함된 음표를 단일 문자로 치환
    static String changeMelody(String melody) {
        return melody.replaceAll("C#", "H")
                .replaceAll("D#", "I")
                .replaceAll("F#", "J")
                .replaceAll("G#", "K")
                .replaceAll("A#", "L")
                .replaceAll("B#", "M");
    }

    public String getTitle() {
        return title;
    }

    public int getPlayTime() {
        return playTime;
    }

    public boolean containsMelody(String melody) {
        return this.playedMelody.contains(melody);
    }
}

class Solution {
    public String solution(String m, String[] musicinfos) {
        List<MusicInfo> musicInfos = new ArrayList<>();

        // 입력된 음악 정보를 기반으로 MusicInfo 객체 생성
        for (String info : musicinfos) {
            String[] parts = info.split(","); // 문자열을 쉼표로 분리
            musicInfos.add(new MusicInfo(parts[0], parts[1], parts[2], parts[3]));
        }

        // 네오가 기억한 멜로디를 변환
        String changedMelody = MusicInfo.changeMelody(m);

        // 최적의 음악 제목을 저장할 변수 초기화
        String bestMatch = "(None)";
        int maxPlayTime = -1;

        // 모든 음악 정보 탐색
        for (MusicInfo musicInfo : musicInfos) {
            // 조건: 멜로디가 포함되어 있고, 재생 시간이 가장 긴 음악 선택
            if (musicInfo.containsMelody(changedMelody) && musicInfo.getPlayTime() > maxPlayTime) {
                bestMatch = musicInfo.getTitle();
                maxPlayTime = musicInfo.getPlayTime();
            }
        }

        // 결과 반환: 조건에 맞는 음악 제목 또는 "(None)"
        return bestMatch;
    }

}