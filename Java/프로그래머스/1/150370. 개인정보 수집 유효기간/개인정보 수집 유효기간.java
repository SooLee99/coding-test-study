import java.util.*;
import java.text.ParseException;

public class Solution {

    public int[] solution(String today, String[] terms, String[] privacies) throws ParseException {
        PrivacyExpirationChecker checker = new PrivacyExpirationChecker(today, terms);
        return checker.getExpiredPrivacyNumbers(privacies);
    }
}

// 날짜 계산 관련 클래스
class DateCalculator {
    private static final int DAYS_IN_MONTH = 28;

    // 날짜를 총 일수로 변환
    public static int convertDateToDays(String date) {
        String[] parts = date.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        return (year * 12 * DAYS_IN_MONTH) + (month * DAYS_IN_MONTH) + day;
    }

    // 총 일수에서 날짜로 변환 (필요 시)
    public static String convertDaysToDate(int days) {
        int day = days % DAYS_IN_MONTH;
        days /= DAYS_IN_MONTH;
        int month = days % 12;
        int year = days / 12;
        return String.format("%04d.%02d.%02d", year, month, day);
    }
}

// 약관 정보를 담는 클래스
class PrivacyTerm {
    private final String type;
    private final int validityInMonths;

    public PrivacyTerm(String type, int validityInMonths) {
        this.type = type;
        this.validityInMonths = validityInMonths;
    }

    public String getType() {
        return type;
    }

    public int getValidityInMonths() {
        return validityInMonths;
    }
}

// 개인정보 정보를 담는 클래스
class PrivacyInfo {
    private final int collectionDateInDays;
    private final String termType;

    public PrivacyInfo(String collectionDate, String termType) {
        this.collectionDateInDays = DateCalculator.convertDateToDays(collectionDate);
        this.termType = termType;
    }

    public int getCollectionDateInDays() {
        return collectionDateInDays;
    }

    public String getTermType() {
        return termType;
    }
}

// 개인정보 파기 여부를 체크하는 클래스
class PrivacyExpirationChecker {
    private final int todayInDays;
    private final Map<String, Integer> termValidityMap;

    public PrivacyExpirationChecker(String today, String[] terms) {
        this.todayInDays = DateCalculator.convertDateToDays(today);
        this.termValidityMap = new HashMap<>();
        initializeTerms(terms);
    }

    private void initializeTerms(String[] terms) {
        for (String term : terms) {
            String[] parts = term.split(" ");
            String type = parts[0];
            int validityInMonths = Integer.parseInt(parts[1]);
            termValidityMap.put(type, validityInMonths);
        }
    }

    public int[] getExpiredPrivacyNumbers(String[] privacies) {
        List<Integer> expiredNumbers = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            PrivacyInfo privacyInfo = new PrivacyInfo(parts[0], parts[1]);

            int expirationDate = privacyInfo.getCollectionDateInDays()
                    + termValidityMap.get(privacyInfo.getTermType()) * 28 - 1;

            if (expirationDate < todayInDays) {
                expiredNumbers.add(i + 1);
            }
        }

        return expiredNumbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
