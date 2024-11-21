import java.util.*;

public class Solution {

    public int[] solution(String[] id_list, String[] report, int k) {
        // 1. 사용자 초기화
        UserRepository userRepository = new UserRepository(id_list);

        // 2. 신고 처리 및 정지 대상 판단
        ReportManager reportManager = new ReportManager(userRepository);
        Set<String> bannedUsers = reportManager.processReports(report, k); // 정지된 사용자 목록 반환

        // 3. 결과 메일 계산
        NotificationService notificationService = new NotificationService(userRepository, bannedUsers);
        return notificationService.calculateNotifications(id_list); // id_list를 전달하여 순서 보장
    }
}

// 사용자 관리 클래스
class User {
    private final String id;
    private final Set<String> reportedBy = new HashSet<>(); // 자신을 신고한 사용자 ID
    private final Set<String> reports = new HashSet<>();    // 자신이 신고한 사용자 ID

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addReport(String reporterId) {
        reportedBy.add(reporterId);
    }

    public void reportUser(String reportedId) {
        reports.add(reportedId);
    }

    public Set<String> getReportedBy() {
        return reportedBy;
    }

    public Set<String> getReports() {
        return reports;
    }

    public int getReportCount() {
        return reportedBy.size();
    }
}

// 사용자 저장소
class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepository(String[] id_list) {
        for (String id : id_list) {
            users.put(id, new User(id));
        }
    }

    public User getUser(String id) {
        return users.get(id);
    }

    // 모든 사용자 반환
    public Collection<User> getAllUsers() {
        return users.values();
    }
}

// 신고 처리 및 정지 대상 판단 클래스
class ReportManager {
    private final UserRepository userRepository;

    public ReportManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<String> processReports(String[] reports, int k) {
        // 신고 처리
        for (String report : reports) {
            String[] parts = report.split(" ");
            String reporterId = parts[0];
            String reportedId = parts[1];

            User reporter = userRepository.getUser(reporterId);
            User reported = userRepository.getUser(reportedId);

            if (reporter != null && reported != null) {
                reported.addReport(reporterId);
                reporter.reportUser(reportedId);
            }
        }

        // 정지 대상 판단
        Set<String> bannedUsers = new HashSet<>();
        for (User user : userRepository.getAllUsers()) {
            if (user.getReportCount() >= k) {
                bannedUsers.add(user.getId());
            }
        }
        return bannedUsers; // 정지된 사용자 목록 반환
    }
}

// 결과 메일 계산 클래스
class NotificationService {
    private final UserRepository userRepository;
    private final Set<String> bannedUsers; // 정지된 사용자 목록

    public NotificationService(UserRepository userRepository, Set<String> bannedUsers) {
        this.userRepository = userRepository;
        this.bannedUsers = bannedUsers;
    }

    public int[] calculateNotifications(String[] id_list) {
        int[] notifications = new int[id_list.length];

        for (int i = 0; i < id_list.length; i++) {
            String userId = id_list[i];
            User user = userRepository.getUser(userId);
            int mailCount = 0;

            for (String reportedUser : user.getReports()) {
                if (bannedUsers.contains(reportedUser)) {
                    mailCount++;
                }
            }
            notifications[i] = mailCount;
        }

        return notifications;
    }
}
