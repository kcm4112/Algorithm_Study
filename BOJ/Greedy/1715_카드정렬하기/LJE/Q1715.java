package Greedy;

import java.util.*;

public class Q1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 카드 묶음이 1개라면 비교 횟수가 0번이다.
        if (N == 1) {
            System.out.println(0);
            return;
        }

        // 카드 묶음을 오름차순으로 정렬하기 위해 우선순위 큐를 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(sc.nextInt());
        }

        int result = 0;
        while (pq.size() > 2) {
            int num1 = pq.poll();
            int num2 = pq.poll();

            result += num1 + num2;

            pq.offer(num1 + num2);

        }

        // 우선순위 큐에 남아있는 원소들 모두 더해주기
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        System.out.println(result);

    }



}