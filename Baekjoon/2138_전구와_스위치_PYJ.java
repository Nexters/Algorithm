import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 #2138 전구와 스위치 (실버 2)
 * https://www.acmicpc.net/problem/2138
 *
 * 처음 전구 상태에서 기대하는 전구 상태가 되기 위해 스위치를 몇번 최소 누르면 되는지 출력
 * 스위치는 i-1, i, i+1 번째 전구를 켜고 끌 수 있음
 */
class Main {

    // 전구 총 개수
    private static int totalLightsCount;

    // 전구 배열 비교를 위해 2개 선언
    private static char[] nowLightArray1;
    private static char[] nowLightArray2;

    // 기대하는 전구
    private static char[] expectLightArray;

    // 첫 번째 스위치 켜는 배열과 안 켜는 배열 count 저장
    private static int count1 = 1;
    private static int count2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 전구 총 개수 받아오기
        totalLightsCount = Integer.parseInt(bufferedReader.readLine());

        String nowLights = bufferedReader.readLine();
        String expectLights = bufferedReader.readLine();

        nowLightArray1 = nowLights.toCharArray();
        nowLightArray2 = nowLightArray1.clone();
        expectLightArray = expectLights.toCharArray();

        // 첫 번째 경우는 1번 스위치 켜기
        switchOn(nowLightArray1, 0);

        for (int i = 1; i < totalLightsCount; i++) {
            if (nowLightArray1[i - 1] != expectLightArray[i - 1]) {
                count1++;
                switchOn(nowLightArray1, i);
            }
            // 두 번째 경우는 1번 스위치 안켜기
            if (nowLightArray2[i - 1] != expectLightArray[i - 1]) {
                count2++;
                switchOn(nowLightArray2, i);
            }
        }

        int ret = checkAndReturn();
        System.out.println(ret == Integer.MAX_VALUE ? -1 : ret);

    }

    /**
     * 스위치 켜고 끄는 함수
     *
     * @param arr 스위치 배열
     * @param idx 바꿀 버튼 인덱스
     */
    static void switchOn(char[] arr, int idx) {
        for (int i = idx - 1; i <= idx + 1; i++) {
            if (i >= 0 && i < totalLightsCount) {
                if (arr[i] == '0') {
                    arr[i] = '1';
                } else {
                    arr[i] = '0';
                }
            }
        }
    }

    /**
     * 둘 중 작은 값 찾아서 리턴
     *
     * @return 두 값 중 작은 값
     */
    static int checkAndReturn() {
        // 최댓값으로 선언
        int ret = Integer.MAX_VALUE;
        boolean ok1 = true;
        boolean ok2 = true;

        for (int i = 0; i < totalLightsCount; i++) {
            if (nowLightArray1[i] != expectLightArray[i]) {
                ok1 = false;
            }
            if (nowLightArray2[i] != expectLightArray[i]) {
                ok2 = false;
            }
        }

        if (ok1) ret = count1;
        if (ok2) ret = Math.min(ret, count2);

        return ret;
    }
}