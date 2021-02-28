import java.util.*;

/**
 * 시뮬레이션(2021.02.24)
 * 백준 #8911 거북이(실버2)
 * https://www.acmicpc.net/problem/8911
 *
 * 거북이가 지나간 영역을 포함하는 직사각형 넓이 구하는 문제
 * 앞/뒤 이동, 방향 전환 가능
 *
 * 입력 : 이동경로
 * 출력 : 직사각형 넓이
 */
public class P8911 {
    // 방향 0:상 1:우 2:하 3:좌
    static int head = 0;

    public static void main(String[] args) {
        // 지나간 영역을 포함하는 직사각형 넓이
        Scanner scanner = new Scanner(System.in);
        // 첫 줄 : 테스트 케이스 T
        int testCase = scanner.nextInt();
        for (int i = 0; i < testCase; i++) {
            // 좌표 이동할 때 마다 최소, 최대 비교
            int minX = 0;
            int minY = 0;
            int maxX = 0;
            int maxY = 0;
            // 현재 좌표
            int[] curr = {0, 0};

            String course = scanner.next();
            char[] path = course.toCharArray();
            for (char move: path) {
                moveTurtle(move, curr);
                if (curr[0] < minX) minX = curr[0];
                else if (curr[0] > maxX) maxX = curr[0];
                else if (curr[1] < minY) minY = curr[1];
                else if (curr[1] > maxY) maxY = curr[1];
            }
            int width = maxX - minX;
            int height = maxY - minY;
            System.out.println(width * height);
        }
    }

    /**
     * 거북이 움직이는 함수
     */
    public static void moveTurtle(char move, int[] curr) {
        if (move == 'L') {
            if (head == 0) head = 3;
            else head--;
        } else if (move == 'R') {
            if (head == 3) head = 0;
            else head++;
        } else if(move == 'F') {
            if (head == 0) {
                curr[1]++;
            } else if (head == 1) {
                curr[0]++;
            } else if (head == 2) {
                curr[1]--;
            } else if (head == 3) {
                curr[0]--;
            }
        } else {
            if (head == 0) {
                curr[1]--;
            } else if (head == 1) {
                curr[0]--;
            } else if (head == 2) {
                curr[1]++;
            } else if (head == 3) {
                curr[0]++;
            }
        }
    }
}

