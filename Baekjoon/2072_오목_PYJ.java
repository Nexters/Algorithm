
import java.util.*;

/**
 * 시뮬레이션(2021.02.26)
 * 백준 #2072 오목(실버2)
 * https://www.acmicpc.net/problem/2072
 *
 * 오목에서 게임이 끝나는 돌의 개수를 출력
 *
 * 입력 : 오목 돌 좌표
 * 출력 : 게임 종료되는 돌의 개수
 */
public class P2072 {

    // [행렬][줄번호][최소/최대]
    static int [][] stoneInLineA = new int[2][20];
    static int [][] stoneInLineB = new int[2][20];
    static int [][][] countA = new int[2][20][2];
    static int [][][] countB = new int[2][20][2];

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            countA[0][i][0] = 20;
            countA[0][i][1] = 0;
            countA[1][i][0] = 20;
            countA[1][i][1] = 0;
            countB[0][i][0] = 20;
            countB[0][i][1] = 0;
            countB[1][i][0] = 20;
            countB[1][i][1] = 0;
        }
        Scanner scanner = new Scanner(System.in);
        int stoneCount = scanner.nextInt();
        for (int i = 0; i < stoneCount; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if ((i+1)%2 == 1) {
//                // 흑돌
//                System.out.println("흑돌x : " + stoneInLineA[0][x]);
//                System.out.println("흑돌y : " + stoneInLineA[1][y]);
                boolean check = checkStoneA(x, y);
                System.out.println("흑돌 : " + i + " check : " + check);
                if (check) {
                    System.out.println(i+1);
                    break;
                }
            }else if ((i+1)%2 == 0) {
//                System.out.println("백돌x : " + stoneInLineB[0][x]);
//                System.out.println("백돌y : " + stoneInLineB[1][y]);
                // 백돌
                boolean check = checkStoneB(x, y);
                System.out.println("백돌 : " + i + " check : " + check);
                if (check){
                    System.out.println(i+1);
                    break;
                }
            }
        }
    }
    public static boolean checkStoneA(int x, int y) {
        if (x < countA[0][x][0]) {
            countA[0][x][0] = x;
            stoneInLineA[0][x]++;
        } else if (x >= countA[0][x][1]) {
            countA[0][x][1] = x;
            stoneInLineA[0][x]++;
        }
        if (y < countA[0][y][0]) {
            countA[1][y][0] = y;
            stoneInLineA[1][y]++;
        } else if (x > countA[0][y][1]) {
            countA[1][y][1] = y;
            stoneInLineA[1][y]++;
        }

        System.out.println("흑돌x : " + (countA[0][x][1] - countA[0][x][0]) + " Count : " + stoneInLineA[0][x]);
        System.out.println("흑돌y : " + (countA[1][y][1] - countA[1][y][0]) + " Count : " + stoneInLineA[1][y]);
//        System.out.println("흑돌x : " + stoneInLineA[0][x]);
//        System.out.println("흑돌y : " + stoneInLineA[1][y]);

        return ((countA[0][x][1] - countA[0][x][0] == 5 && stoneInLineA[0][x] == 5) ||
                (countA[1][y][1] - countA[1][y][0] == 5 && stoneInLineA[1][y] == 5));
    }

    public static boolean checkStoneB(int x, int y) {
        if (x < countB[0][x][0]) {
            countB[0][x][0] = x;
            stoneInLineB[0][x]++;
        } else if (x >= countB[0][x][1]) {
            countB[0][x][1] = x;
            stoneInLineB[0][x]++;
        }
        if (y < countB[0][y][0]) {
            countB[1][y][0] = y;
            stoneInLineB[1][y]++;
        } else if (x > countB[0][y][1]) {
            countB[1][y][1] = y;
            stoneInLineB[1][y]++;
        }
        System.out.println("백돌x : " + (countB[0][x][1] - countB[0][x][0]) + " Count : " + stoneInLineB[0][x]);
        System.out.println("백돌y : " + (countB[1][y][1] - countB[1][y][0]) + " Count : " + stoneInLineB[1][y]);
//        System.out.println("백돌y : " + stoneInLineB[1][y]);
        return ((countB[0][x][1] - countB[0][x][0] == 5 && stoneInLineB[0][x] == 5) ||
                (countB[1][y][1] - countB[1][y][0] == 5 && stoneInLineB[1][y] == 5));
    }
}
