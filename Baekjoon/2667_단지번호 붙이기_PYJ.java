package baekjoon;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DFS
 * 백준 #2667 단지번호 붙이기
 * https://www.acmicpc.net/submit/2667
 *
 * 1과 0으로 채워진 정사각형 모양의 지도
 * 1 = 집O, 0 = 집X
 * 연결된 집을 단지로 묶어서 번호를 붙임.
 *
 * 입력 : 지도
 * 출력 : 단지 수, 각 단지의 집 개수
 *
 */

public class P2667 {

    static int[][] map;             // 지도
    static int size;                // 지도 크기
    static boolean[][] checkHome;   // 집 체크 유무 저장 배열

    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 지도 크기 check 함수를 위해 +1로 선언
        size = sc.nextInt();
        map = new int[size+1][size+1];
        checkHome = new boolean[size+1][size+1];

        // 단지 별 집의 개수를 저장하는 list
        List<Integer> list = new ArrayList<>();

        // 2차원 배열 지도 만들기
        for(int i = 0; i < size; i++) {
            String line = sc.next();
            for(int j = 0; j < size; j++) {
                char c = line.charAt(j);
                map[i][j] =  Integer.parseInt(String.valueOf(c));
            }
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


        // dfs
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                // 집이 존재하고 단지 할당이 안되어 있는지 check
                if (check(i, j)) {
                    // complexNum : 하나의 단지에 집 개수
                    int complexNum = dfs(i, j);
                    list.add(complexNum);
                }
            }
        }
        // 집의 수를 오름차순 정렬
        Collections.sort(list);
        stringBuilder.append(list.size()).append("\n");
        for(int num : list) stringBuilder.append(num).append("\n");

        System.out.println(stringBuilder);
    }

    /**
     * 집의 좌표를 체크
     *
     * @param x 지도 x좌표
     * @param y 지도 y좌표
     * @return 좌표 값이 유효한지
     */
    public static boolean check(int x, int y){
        // 좌표 값 잘못되면 false
        if(x < 0 || x > size || y < 0 || y > size) return false;
        // 이미 지나간 경로인 경우 or 집이 아닌 경우
        if(checkHome[x][y] || map[x][y] == 0) return false;

        return true;
    }

    /**
     * DFS
     *
     * @param x 지도 x좌표
     * @param y 지도 y좌표
     * @return  단지의 집 개수
     */
    private static int dfs(int x, int y) {
        // 단지 별 집의 개수
        int val = 1;
        checkHome[x][y] = true;

        // 오른쪽 좌표 확인
        if(check(x, y + 1)) {
            val += dfs(x, y + 1);
        }
        // 아래 좌표
        if(check(x + 1, y)) {
            val += dfs(x + 1, y);
        }
        // 위쪽 좌표
        if(check(x - 1, y)) {
            val += dfs(x - 1, y);
        }
        // 왼쪽 좌표
        if(check(x, y - 1)) {
            val += dfs(x, y - 1);
        }
        return val;
    }

}
