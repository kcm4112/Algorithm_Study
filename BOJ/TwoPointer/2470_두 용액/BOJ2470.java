package BOJ;

import java.util.Arrays;
import java.util.Scanner;

class BOJ2470 {
    private static int N;
    private static int left;
    private static int right;
    private static int[] nums;
    private static int diff = Integer.MAX_VALUE;
    private static int answer1 = 0;
    private static int answer2 = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        left = 0;
        right = N - 1;
        Arrays.sort(nums);
        while (left < right) {
            int tmpDiff = Math.abs(nums[left] + nums[right]);
            if (tmpDiff < diff) {
                diff = tmpDiff;
                answer1 = nums[left];
                answer2 = nums[right];
            }
            if (nums[left] + nums[right] > 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(answer1 + " " + answer2);
    }
}