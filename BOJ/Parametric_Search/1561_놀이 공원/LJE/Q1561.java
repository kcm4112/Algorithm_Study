package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이분탐색
// 놀이 공원 문제
public class Q1561 {
    static int maxM;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] ridesTime = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            ridesTime[i] = Integer.parseInt(st.nextToken());
            maxM = Math.max(maxM, ridesTime[i]);
        }

        long result = binarySearch(ridesTime, n, m);

        long prevTime = result - 1;
        long prevCount = m;
        long[] prevRidesTime = new long[m];
        for(int i = 0; i < m ; i++) {
            prevRidesTime[i] = prevTime / ridesTime[i];
            prevCount += prevRidesTime[i];
        }

        // 마지막 학생이 타는 놀이기구 찾기
        long nowNeedsCount = n - prevCount;
        long nowCount = 0;
        long ridesNum = n;	// 만약, n < m 인 경우에는 처음 시작할 때 n명을 탑승시킬수 있어 n번째 사람은 n번 놀이기구를 탄다.
        for(int i = 0; i < m; i++) {
            if(result / ridesTime[i] != prevRidesTime[i]) {
                nowCount++;
                if(nowCount == nowNeedsCount) {
                    ridesNum = i + 1;	// 놀이기구의 번호는 1 ~ M 번 까지이므로
                    break;
                }
            }
        }

        System.out.println(ridesNum);
    }

    static public long binarySearch(int[] ridesTime, long n, int m) {
        long left = 0;
        long right = (n / m) * maxM; // 최대 나올 수 있는 시간
        long result = 0;

        // 이분탐색 시작
        // index : x분이 되었을 때의 x분
        // value : x분이 되었을 때 가능한 총 인원 수
        // 최소 N명 이상을 태울 수 있는 시간을 얻기 위해 사용
        while(left <= right) {
            long mid = (left + right) / 2;
            long count = m;
            for(int i = 0; i < m ; i++) {
                count += mid / ridesTime[i];
            }

            if(n <= count) {
                result = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return result;
    }
}
