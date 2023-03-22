package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Q2529 {
    static int N;
    static boolean[] used;
    static String[] operator;
    static ArrayList<String> number = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        operator = br.readLine().split(" ");

        for (int i = 0; i < 10; i++) {
            used = new boolean[10]; // 범위 0~9
            used[i] = true;
            rec_func(i, 0, i+"");
            used[i] = false;
        }

        // 정렬한 뒤, 첫 번째 원소 = 최솟값, 마지막 원소 = 최댓값
        Collections.sort(number);
        System.out.println(number.get(number.size()-1) + "\n" + number.get(0));
    }

    static void rec_func(int start, int count, String num) {
        // num의 길이가 주어진 N보다 1이 클 경우 숫자가 완성됐으므로 종료
        if (num.length() == N + 1) {
            number.add(num);
        } else {
            for (int i = 0; i < 10; i++) {
                // 사용되지 않은 숫자일 경우만 사용 가능
                if (!used[i]) {
                    if (operator[count].equals("<")) {
                        if (start < i) {
                            // 조건을 만족하면 사용했다는 것을 체크
                            used[i] = true;
                            rec_func(i, count + 1, num + i);
                            // 재귀 함수를 빠져나오면 사용 해제
                            used[i] = false;
                        }
                    } else {
                        if (start > i) {
                            used[i] = true;
                            rec_func(i, count + 1, num + i);
                            used[i] = false;
                        }
                    }
                }
            }
        }
    }
}
