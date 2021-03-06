import java.util.*;

/**
 * 시뮬레이션(2021.02.25)
 * 백준 #16113 시그널(실버2)
 * https://www.acmicpc.net/problem/16113
 *
 * # 또는 . 로 이루어진 문자열을 5등분하여 나오는 숫자 출력
 *
 * 입력 : 시그널 길이 / 해석할 문자열
 * 출력 : 해석한 숫자
 */
public class P16113 {

    public static void main(String[] args) {
        StringBuilder answer = new StringBuilder();
        // 첫 줄 : 시그널의 길이
        Scanner scanner = new Scanner(System.in);
        int signalLength = scanner.nextInt();
        int oneLine = signalLength/5;
        String secret = scanner.next();
        // 문자열 나누기
        String[] secretArray = new String[5];
        for (int i = 0; i < 5; i++) {
            secretArray[i] = secret.substring(i*oneLine, (i+1)*oneLine);
        }

        // 패턴1) 모든 문자는 첫번째 줄 상단에서 시작한다.
        // 패턴2) 첫번째 # 뒤 . 이나오면 1 또는 4
        // 패턴3) 패턴 2와 같이 모든 문자 분류 가능
        for (int j = 0; j < oneLine-2; j++) {
            char temp = secretArray[0].charAt(j);
            if (temp == '#') {
                // 패턴2) 1 또는 4 인 경우
                if (secretArray[0].charAt(j+1) == '.') {
                    if (secretArray[2].charAt(j+1) == '#') {
                        answer.append("4");
                        j = j+2;
                    } else {
                        answer.append("1");
                        j++;
                    }
                }
                // 0, 2, 3, 5, 6, 7, 8, 9
                // 2, 3, 7 분류
                else if (secretArray[1].charAt(j) == '.') {
                    if (secretArray[3].charAt(j) == '#') {
                        answer.append("2");
                        j = j+2;
                    } else if (secretArray[4].charAt(j) == '#') {
                        answer.append("3");
                        j = j+2;
                    } else if (secretArray[4].charAt(j) == '.') {
                        answer.append("7");
                        j = j+2;
                    }
                }
                // 0, 5, 6, 8, 9 분류
                else {
                    if (secretArray[2].charAt(j+1) == '.') {
                        answer.append("0");
                        j = j+2;
                    } else if (secretArray[3].charAt(j) == '.') {
                        if (secretArray[1].charAt(j+2) == '#') {
                            answer.append("9");
                        } else {
                            answer.append("5");
                        }
                        j = j+2;
                    } else if (secretArray[3].charAt(j) == '#'){
                        if (secretArray[1].charAt(j+2) == '#') {
                            answer.append("8");
                        } else {
                            answer.append("6");
                        }
                        j = j+2;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
