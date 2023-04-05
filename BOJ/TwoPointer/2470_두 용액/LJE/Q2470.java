package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] solutions = new int[N];
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solutions); // 용액들을 정렬

        int best_sum = Integer.MAX_VALUE;
        int L = 0, R = N - 1;
        int v1 = 0, v2 = 0;

        while (L < R) { // L == R 인 상황이면 용액이 한 개 뿐이기 떄문에 종료
            int sum = solutions[L] + solutions[R];

            if (Math.abs(sum) < best_sum) { // 새롭게 계산한 sum이 기존의 best_sum보다 좋은 경우(0에 가까운 경우)
                best_sum = Math.abs(sum);
                v1 = solutions[L];
                v2 = solutions[R];
            }

            if (sum > 0) {
                R--;
            } else {
                L++;
            }
        }

        System.out.println(v1 + " " + v2);

    }

}
