package Backjoon;

import java.util.Scanner;

/**
 * 완전탐색 - 연산자 끼워넣기
 * https://www.acmicpc.net/problem/14888
 * 백준 #14888
 *
 * N개의 수 & N-1개의 연산자
 * 최대 값 & 최소 값 찾기
 */
public class P14888 {
    public static int MAX = Integer.MIN_VALUE;	// 최대값
    public static int MIN = Integer.MAX_VALUE;	// 최소값
    public static int[] operator = new int[4];	// 연산자 개수
    public static int[] num;					// 숫자
    public static int numberCount;			    // 숫자 개수

    public static void main(String[] args) {

        // input
        Scanner sc = new Scanner(System.in);
        numberCount = sc.nextInt();
        num = new int[numberCount];
        // 숫자 받아오기
        for (int i = 0; i < numberCount; i++) {
            num[i] = sc.nextInt();
        }
        // 연산자 받아오기
        for (int i = 0; i < 4; i++) {
            operator[i] = sc.nextInt();
        }

        bruteForce(num[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);

    }

    private static void bruteForce(int number, int idx) {
        // 탈출 조건(모든 숫자 연산 끝)
        if (idx == numberCount) {
            MAX = Math.max(MAX, number);
            MIN = Math.min(MIN, number);
            return;
        }

        // 연산자 기준 계산 후 재귀함수 호출
        for (int i = 0; i < 4; i++) {
            // 연산자 개수가 1개 이상인 경우
            if (operator[i] > 0) {
                // 해당 연산자를 1 감소
                operator[i]--;

                switch (i) {
                    case 0:	bruteForce(number + num[idx], idx + 1);	break;
                    case 1:	bruteForce(number - num[idx], idx + 1);	break;
                    case 2:	bruteForce(number * num[idx], idx + 1);	break;
                    case 3:	bruteForce(number / num[idx], idx + 1);	break;
                }
                // 재귀함수 호출 이후 연산자 복구
                operator[i]++;
            }
        }
    }
}
