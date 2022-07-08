import java.util.*;
import java.io.*;

// 100 100 0 일때 3674307795577560168
// 50  50  0 일때 1184508333840160104
public class P_1577 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        long [][] dp = new long [101][101];
        //[도착좌표][도착좌표][왼or오]
        int [][][] road = new int [101][101][2];
        for(int i=0;i<K;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(s.nextToken());
            int y1 = Integer.parseInt(s.nextToken());
            int x2 = Integer.parseInt(s.nextToken());
            int y2 = Integer.parseInt(s.nextToken());

            // road 3차원 배열의 세번째 인자 값 : 왼->오 == 0, 위->아래 == 1
            //다음 목적지의 x,y에 대해 왼->오와 위->아래의 경우 이동가능한지 판단.
            if(x1 == x2){
                if(y1>y2){
                    road[y1][x1][1] = -1;
                }
                else{
                    road[y2][x1][1] = -1;
                }
            }
            if(y1==y2){
                if(x1>x2){
                    road[y1][x1][0] = -1;
                }
                else{
                    road[y1][x2][0] = -1;
                }
            }
        }

        dp[0][0] = 1;
        //확률과통계에 나오는 경로 구하는 방식과 동일하게 진행
        for(int i=0;i<=M;i++){
            for(int j=0;j<=N;j++){
                //같은 다음 목적지에 갈 수 있는 경우의 수를 더한다.
                //같은 좌표 i,j에 대하여 왼쪽의 값과, 위쪽의 값을 더하면 됨.
                //위쪽의 값을 더할 때는 i-1의 인덱스가 반드시 0이상.
                //왼쪽의 값을 더할 때는 j-1의 인덱스가 반드시 0이상.

                //위 -> 아래로 가는 경우
                if(road[i][j][1] != -1 && i-1>=0){
                    dp[i][j] = dp[i][j] + dp[i-1][j];
                }
                //왼-->오 로 가는 경우
                if(road[i][j][0] != -1 && j-1>=0){
                    dp[i][j] = dp[i][j] + dp[i][j-1];
                }
            }
        }
        System.out.println(dp[M][N]);
    }
}
