import java.util.*;

class User {
    private int minDiscount;
    private int threshold;

    public User(int minDiscount, int threshold) {
        this.minDiscount = minDiscount;
        this.threshold = threshold;
    }

    public int getMinDiscount() {
        return minDiscount;
    }

    public int getThreshold() {
        return threshold;
    }
}

class Emoticon {
    private int price;

    public Emoticon(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

class Result implements Comparable<Result> {
    private int subscribers;
    private int sales;

    public Result(int subscribers, int sales) {
        this.subscribers = subscribers;
        this.sales = sales;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public int getSales() {
        return sales;
    }

    @Override
    public int compareTo(Result o) {
        if (this.subscribers != o.subscribers) {
            return o.subscribers - this.subscribers; // 내림차순
        }
        return o.sales - this.sales; // 내림차순
    }
}

class Solution {
    private static final int[] DISCOUNTS = {10, 20, 30, 40};
    private List<User> users;
    private List<Emoticon> emoticons;
    private Result maxResult;

    public int[] solution(int[][] usersInput, int[] emoticonsInput) {
        // 사용자와 이모티콘 객체 생성
        users = new ArrayList<>();
        for (int[] u : usersInput) {
            users.add(new User(u[0], u[1]));
        }

        emoticons = new ArrayList<>();
        for (int price : emoticonsInput) {
            emoticons.add(new Emoticon(price));
        }

        maxResult = new Result(0, 0);

        // 할인율 조합을 저장할 배열
        int[] discountAssignment = new int[emoticons.size()];

        // 모든 할인율 조합 탐색
        dfs(0, discountAssignment);

        return new int[]{maxResult.getSubscribers(), maxResult.getSales()};
    }

    // 깊이 우선 탐색을 통한 모든 할인율 조합 탐색
    private void dfs(int idx, int[] assignment) {
        if (idx == assignment.length) {
            // 현재 할인율 조합으로 결과 계산
            Result currentResult = evaluate(assignment);
            updateMaxResult(currentResult);
            return;
        }

        for (int discount : DISCOUNTS) {
            assignment[idx] = discount;
            dfs(idx + 1, assignment);
        }
    }

    // 주어진 할인율 조합에 대한 가입자 수와 판매액 계산
    private Result evaluate(int[] assignment) {
        int subscribers = 0;
        int sales = 0;

        for (User user : users) {
            int total = 0;
            for (int i = 0; i < emoticons.size(); i++) {
                if (assignment[i] >= user.getMinDiscount()) {
                    int discountedPrice = emoticons.get(i).getPrice() * (100 - assignment[i]) / 100;
                    total += discountedPrice;
                }
            }

            if (total >= user.getThreshold()) {
                subscribers++;
            } else {
                sales += total;
            }
        }

        return new Result(subscribers, sales);
    }

    // 최적의 결과 업데이트
    private void updateMaxResult(Result current) {
        if (current.compareTo(maxResult) < 0) {
            // current가 maxResult보다 우수한 경우 (더 많은 가입자 또는 같은 가입자 수에 더 많은 판매액)
            maxResult = current;
        }
    }
}
