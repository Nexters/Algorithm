import java.util.Scanner;

public class BOJ_1292 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int[] seq = new int[10001];

        int idx = 1;
        int count = 0;
        for (int i = 1; i < 1001; i++) {
            seq[i] = idx;
            count++;

            if (idx == count) {
                count = 0;
                idx++;
            }
        }

        int sum = 0;
        for (int i = a; i <= b; i++) {
            sum += seq[i];
        }

        System.out.println(sum);
    }
}
