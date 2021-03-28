import java.io.*;
import java.util.Arrays;

public class BOJ_16113 {
    static int[][] signal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split("");

        signal = new int[5][n / 5];

        int[][] zero = {{1, 1, 1}, {1, 0, 1}, {1, 0, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] two = {{1, 1, 1}, {0, 0, 1}, {1, 1, 1}, {1, 0, 0}, {1, 1, 1}};
        int[][] three = {{1, 1, 1}, {0, 0, 1}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}};
        int[][] four = {{1, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 0, 1}, {0, 0, 1}};
        int[][] five = {{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}};
        int[][] six = {{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] seven = {{1, 1, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}};
        int[][] eight = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] nine = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}};

        for (int i = 0; i < str.length; i++) {
            signal[i / (n / 5)][i % (n / 5)] = str[i].equals("#") ? 1 : 0;
        }

        int idx = 0;
        while (idx < signal[0].length) {
            if (signal[0][idx] == 0) {
                idx++;
                continue;
            }

            int[][] temp = cloneArray(idx);
            if (temp == null) {
                bw.write("1");
                idx++;
                continue;
            }
            idx += 3;

            if (Arrays.deepEquals(temp, zero)) {
                bw.write("0");
            } else if (Arrays.deepEquals(temp, two)) {
                bw.write("2");
            } else if (Arrays.deepEquals(temp, three)) {
                bw.write("3");
            } else if (Arrays.deepEquals(temp, four)) {
                bw.write("4");
            } else if (Arrays.deepEquals(temp, five)) {
                bw.write("5");
            } else if (Arrays.deepEquals(temp, six)) {
                bw.write("6");
            } else if (Arrays.deepEquals(temp, seven)) {
                bw.write("7");
            } else if (Arrays.deepEquals(temp, eight)) {
                bw.write("8");
            } else if (Arrays.deepEquals(temp, nine)) {
                bw.write("9");
            } else {
                bw.write("1");
            }
        }

        bw.flush();
        bw.close();
    }

    public static int[][] cloneArray(int idx) {
        // 1인 경우 null 리턴
        if (idx + 1 < signal[0].length) {
            if (signal[0][idx + 1] == 0 && signal[2][idx + 1] == 0) return null;
        }

        int[][] result = new int[5][3];
        for (int i = 0; i < 5; i++) {
            result[i] = Arrays.copyOfRange(signal[i], idx, idx + 3);
        }

        return result;
    }
}
