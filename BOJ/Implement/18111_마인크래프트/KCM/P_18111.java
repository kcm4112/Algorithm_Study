import java.util.*;
import java.io.*;

public class P_18111 {
    static int N,M,B,sum,avg;
    static int time = 0;
    static int map [][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
//        System.out.printf("%d %d %d", N, M, B);
        map = new int [N][M];
        for(int i=0;i<N;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(s.nextToken());
            }
        }
        int res_time=Integer.MAX_VALUE;
        int res_floor=0;
        //정답이 가능한 층은 주어진 map의 최소층 ~ 최대층 까지이다.
        for(int floor = 0; floor<= 256; floor++){
            int inventory = B;
            time = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j] > floor){ //현재층이 목표층보다 높은 경우 그 차이를 inventory에 넣어준다.
                        inventory += (map[i][j] - floor);
                        time += 2*(map[i][j] - floor);
                    }
                    else if(map[i][j] < floor){
                        inventory -= (floor-map[i][j]);
                        time += (floor-map[i][j]);
                    }
                }
            }
            if(inventory >= 0 && time <= res_time){
                res_time = time;
                res_floor = floor;
//                System.out.printf("%d %d\n", res_time, res_floor);
            }
        }
        System.out.printf("%d %d", res_time, res_floor);
    }

}
