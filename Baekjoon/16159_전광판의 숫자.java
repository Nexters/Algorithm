package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 시뮬레이션(2021.03.07)
 * 백준 #16159 전광판의 숫자(실버2)
 * https://www.acmicpc.net/problem/16159
 *
 * 순열에서 다음 전광판 번호 맞추기
 *
 * 입력 : 전광판 숫자
 * 출력 : 다음 전광판 숫자 or The End
 */
public class P16159 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.next();       // 첫 라인
        int width = line.length();          // 전광판 넓이
        int numCount = width/6;             // 숫자 개수
        char[][] inputLine = new char[7][width];
        for (int i = 0; i < width; i++) {
            inputLine[0][i] = line.charAt(i);
        }
        for (int i = 1; i < 7; i++) {
            line = scanner.next();
            for (int j = 0; j < width; j++) {
                inputLine[i][j] = line.charAt(j);
            }
        }
        if (numCount == 1) {
            System.out.println("The End");
            return;
        }

        int[][] allLight = new int[10][42];
        allLight[0][8] = 1;
        allLight[0][9] = 1;
        allLight[0][13] = 1;
        allLight[0][16] = 1;
        allLight[0][19] = 1;
        allLight[0][22] = 1;
        allLight[0][25] = 1;
        allLight[0][28] = 1;
        allLight[0][32] = 1;
        allLight[0][33] = 1;

        allLight[1][9] = 1;
        allLight[1][14] = 1;
        allLight[1][15] = 1;
        allLight[1][21] = 1;
        allLight[1][27] = 1;
        allLight[1][33] = 1;

        for (int i = 7; i < 11; i++) {
            allLight[2][i] = 1;
            allLight[2][i+12] = 1;
            allLight[2][i+24] = 1;
            allLight[5][i] = 1;
            allLight[6][i+12] = 1;
            allLight[6][i+24] = 1;
            allLight[7][i] = 1;
            allLight[8][i] = 1;
            allLight[8][i+12] = 1;
            allLight[8][i+24] = 1;
            allLight[9][i+12] = 1;
        }
        allLight[2][16] = 1;
        allLight[2][25] = 1;

        for (int i = 7; i < 10; i++) {
            allLight[3][i] = 1;
            allLight[3][i+24] = 1;
        }
        allLight[3][16] = 1;
        allLight[3][21] = 1;
        allLight[3][28] = 1;

        for (int i = 24; i < 30; i++) {
            allLight[4][i] = 1;
        }
        allLight[4][9] = 1;
        allLight[4][14] = 1;
        allLight[4][15] = 1;
        allLight[4][19] = 1;
        allLight[4][21] = 1;
        allLight[4][33] = 1;

        allLight[5][13] = 1;
        allLight[5][19] = 1;
        allLight[5][20] = 1;
        allLight[5][21] = 1;
        allLight[5][28] = 1;
        allLight[5][31] = 1;
        allLight[5][38] = 1;
        allLight[5][39] = 1;

        allLight[6][7] = 1;
        allLight[6][13] = 1;
        allLight[6][25] = 1;
        allLight[6][28] = 1;

        allLight[7][16] = 1;
        allLight[7][21] = 1;
        allLight[7][27] = 1;
        allLight[7][33] = 1;

        allLight[8][13] = 1;
        allLight[8][16] = 1;
        allLight[8][25] = 1;
        allLight[8][28] = 1;

        allLight[9][1] = 1;
        allLight[9][2] = 1;
        allLight[9][3] = 1;
        allLight[9][4] = 1;
        allLight[9][7] = 1;
        allLight[9][10] = 1;
        allLight[9][13] = 1;
        allLight[9][16] = 1;
        allLight[9][28] = 1;
        allLight[9][34] = 1;
        allLight[9][40] = 1;

        List<Integer> input = new ArrayList<>();
        // 전체 전광판 숫자만큼 loop
        for (int i = 0; i < numCount; i++) {
            // 전광판 숫자 판별
            if (inputLine[0][1+(i*6)] == '1') {
                input.add(9);
            } else if (inputLine[1][1+(i*6)] == '0') {
                if (inputLine[1][2+(i*6)] == '1') {
                    input.add(0);
                } else if (inputLine[4][(i*6)] == '1') {
                    input.add(4);
                } else {
                    input.add(1);
                }
            } else if(inputLine[1][2+(i*6)] == '0') {
                input.add(6);
            } else if(inputLine[2][4+(i*6)] == '0') {
                input.add(5);
            } else if(inputLine[3][4+(i*6)] == '0') {
                input.add(7);
            } else if(inputLine[4][4+(i*6)] == '0') {
                input.add(2);
            } else {
                input.add(8);
            }
        }
        int[] number = input.stream().mapToInt(i->i).toArray();

        number = nextPermutation(number);

        int[][] chars = new int[7][width];

        for (int j = 0; j < numCount; j++) {
            for (int k = 0; k < 6; k++) {
                chars[0][k+(j*6)] = allLight[number[j]][k];
                chars[1][k+(j*6)] = allLight[number[j]][k+6];
                chars[2][k+(j*6)] = allLight[number[j]][k+12];
                chars[3][k+(j*6)] = allLight[number[j]][k+18];
                chars[4][k+(j*6)] = allLight[number[j]][k+24];
                chars[5][k+(j*6)] = allLight[number[j]][k+30];
                chars[6][k+(j*6)] = allLight[number[j]][k+36];
            }
        }

        for (int i = 0; i < 7; i++) {
            StringBuilder answer = new StringBuilder();
            for (int j = 0; j < width; j++) {
                answer.append(chars[i][j]);
            }
            System.out.println(answer);
        }
    }

    public static int[] nextPermutation(int[] a) {
        //1. a[i-1] < a[i]를 만족하는 첫 번째 수 찾기
        int i = a.length-1;
        while (i > 0 && a[i-1] >= a[i]) {
            i -= 1;
        }

        // 마지막 순열인 경우
        if (i <= 0) {
            return a;
        }

        //2. a[i-1] < a[j]를 만족하는 첫 번째 수 찾기
        int j = a.length-1;
        while (a[j] <= a[i-1]) {
            j -= 1;
        }

        //3. a[i-1]과 a[j] swap
        swap(a, i-1, j);

        //4 i부터 a.length-1까지 순열 뒤집기
        j = a.length-1;
        while (i < j) {
            swap(a, i, j);
            i += 1;
            j -= 1;
        }
        return a;
    }

    public static void swap(int [] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

