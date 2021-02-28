package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2021.01.22
 * https://programmers.co.kr/learn/courses/30/lessons/42584?language=java
 * 스택 큐 문제가 아님 / 지문이해가 잘 안되었음 / 유저가 남긴 테스트케이스 오류 존재 (https://programmers.co.kr/questions/15437)
 */
public class riflockle7_stock_price {
    public static void main(String[] args) {
        int[] value1 = solution(new int[]{1, 2, 3, 2, 3});      // 4, 3, 1, 1, 0
        int[] value2 = solution(new int[]{1, 2, 3, 2, 3, 1});   // 5, 3, 1, 2, 1, 0
        System.out.println(Arrays.toString(value1));
    }

    public static int[] solution(int[] prices) {
        List<Integer> list = new ArrayList<>();

        // 각 answer 에 대한 답
        for (int i = 0; i < prices.length - 1; i++) {
            int count = 0;

            for (int j = i + 1; j < prices.length; j++) {
                // i초 시점에서 만약 주식이 아직 까진 개이득 또는 평타라면
                if (prices[i] <= prices[j]) {
                    count++;
                }

                // 만약 바로 떡락했다면 1초로 둠
                else {
                    count++;
                    break;
                }
            }

            list.add(count);
        }

        list.add(0);

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
