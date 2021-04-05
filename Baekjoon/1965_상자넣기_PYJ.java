package baekjoon;

import java.util.Scanner;

/**
 * DP(2021.03.27)
 * 백준 #1965 상자넣기 (실버2)
 * https://www.acmicpc.net/problem/1965
 *
 */
public class P1965 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 상자 개수
        int boxCount = scanner.nextInt();

        // dp 테이블
        int[] boxSize = new int[boxCount + 2];
        int[] dp = new int[boxCount + 2];
        dp[1] = 1;

        // 가장 큰 값
        int max = 0;

        // 상자 리스트 받기
        for (int i = 1; i <= boxCount; i++) {
            boxSize[i] = scanner.nextInt();
            // dp 테이블 1로 초기화
            dp[i] = 1;
        }

        // 기준 Box Loop
        for (int i = 1; i <= boxCount; i++) {
            // 비교할 Box Loop
            for (int j = 1; j < i; j++) {
                // 기준 box 보다 다음 box 가 더 작을 때
                if (boxSize[j] < boxSize[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
