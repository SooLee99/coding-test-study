class Solution {
    public String solution(int n, int t, int m, int p) {
        int totalLength = t * m;
        String fullSequence = generateFullSequence(n, totalLength);
        return selectTubeNumbers(fullSequence, m, p);
    }

    private String generateFullSequence(int n, int totalLength) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (sb.length() < totalLength) {
            String converted = Integer.toString(num, n).toUpperCase();
            sb.append(converted);
            num++;
        }
        return sb.substring(0, totalLength);
    }

    private String selectTubeNumbers(String fullSequence, int m, int p) {
        StringBuilder tubeAnswer = new StringBuilder();
        for (int i = p - 1; i < fullSequence.length(); i += m) {
            tubeAnswer.append(fullSequence.charAt(i));
        }
        return tubeAnswer.toString();
    }

}
