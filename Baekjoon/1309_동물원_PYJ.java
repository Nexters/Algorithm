package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * DP(2021.03.26)
 * 백준 #1309 동물원 (실버1)
 * https://www.acmicpc.net/problem/1309
 *
 * 동물원에 사자 배치하는 문제
 *
 * 입력 : 우리의 크기(2xN)
 * 출력 : 경우의 수 출력
 */
public class P1309 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n + 1][3];

        // 그 다음방 사자 위치는 이전 사자 위치에 따라 결정 됨
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= n; i++) {
            // 3가지 경우로 나눔
            // dp[n][0] -> 두 개의 방 중에 사자를 아예 넣지 않은 경우
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
            // dp[n][1] -> 두 개의 방 중에 사자를 왼쪽 방에 넣은 경우
            dp[i][1] = dp[i - 1][0] + dp[i - 1][2];
            // dp[n][2] -> 두 개의 방 중에 사자를 오른쪽 방에 넣은 경우
            dp[i][2] = dp[i - 1][0] + dp[i - 1][1];
            // 문제 조건
            dp[i][0] %= 9901;
            dp[i][1] %= 9901;
            dp[i][2] %= 9901;
        }
        int sum = 0;
        // 마지막 dp 테이블 값 더하기
        for (int i = 0; i < 3; i++) {
            sum += dp[n][i];
        }
        System.out.println(sum % 9901);
    }
}
