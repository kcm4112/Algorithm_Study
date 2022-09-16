## 🧩 풀이

 

블럭이 쌓일 수있는 범위가 0이상 256이하인 것만 파악하면 쉽게 풀 수 있다.

 

정답이 되는 최종 높이는 알 수 없지만 어쨌든 위 범위 중 하나인 것은 분명하므로,

0~256층 각각의 케이스에 대하여 수행을 하면 된다.

 

예를 들어 현재 케이스가 55층 (즉, 모두 55층으로 된다고 가정)이라면 55층보다 낮은 블럭에 대해서는 새로 쌓아주고, 높은 블록에 대해서는 그 차이만큼 inventory에 넣어주는 과정을 0~256층에 대하여 모두 수행하며 시간이 더 짧은 케이스로 최신화시켜주면 된다.


예외에 대해선 크게 고민할 필요가 없다.

 

만약 중간중간 inventory가 음수가 되는 경우가 있지만 어쨋는 모든 영역을 탐색하고 난 후에 inventory가 양수가 된다면 어찌되었든 이 케이스는 가능한 케이스이기 때문에 적절히 조건만 달아주면 쉽게 풀 수 있다.

 

걸리는 시간은 같을 때, 더 높은 층이 정답이 되는 문제의 조건도 간단히 해결할 수 있다.

우리는 0층부터 256층까지 점점 층을 높여가며 해당 로직을 수행하기 때문에 시간이 똑같은 케이스가 등장하면 당연히 가장 최근의 케이스가 더 높은 층일것이고, 이것을 조건처리하여 답을 구하면 된다.

---
## 🧩 전체 코드
```java
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
```
