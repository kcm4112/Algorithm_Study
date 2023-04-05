package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] countNum = new int[100001];

        int L = 0, R = 0;
        int result = 0;
        while(R < N){
            if (countNum[numbers[R]] != K) {
                countNum[numbers[R]]++;
                R++;
            } else {
                countNum[numbers[L]]--;
                L++;
            }
            result = Math.max(result, R - L);
        }

        System.out.println(result);
    }
}
