# 풀이

N번의 반복을 하며 매 반복 시행마다 3번의 반복을 해주어야 한다. (열의 개수가 3개이므로 계산하는 방법이 3가지가 있기 때문.)

한 행에서 0번째, 1번째, 2번째 열을 계산하면 아래와 같은 결과가 생긴다.
1. [i][0] 번째 원소는 [i+1][0]과 [i+1][1]과 더할 수 있다.
2. [i][1] 번째 원소는 [i+1][0]과 [i+1][1]과 [i+1][2]와 더할 수 있다.
3. [i][2] 번째 원소는 [i+1][1]과 [i+1][2]와 더할 수 있다.

1번, 2번, 3번 각각의 과정이 끝날 때마다 i행에 들어갈 수 있는 수는 3개의 수는, 내가 구하려고하는 값이 min인지, max인지에 따라 달라진다.
가령, 내가 구하고자 하는 정답이 min일 경우 0, 1, 2번째 열에는 위에서 계산한 값들 중 최소값만 위치해야한다. 그리고 지금 최종적으로 구한 최소값들은 다음 행의 계산에 필요하므로 계속해서 누적 업데이트 되어야 한다.

아래의 코드를 보면서 이해를 하면 좀 더 쉬울 것이다.

🚨주의할 점?🚨
N==1로 입력이 주어지는 경우에 주의를 하자. 

---
# 코드
```java
import java.io.*;
import java.util.*;

public class P_2096 {
    static BufferedReader br;
    static int N;
    static int [][] map;
    static int globalMin_first, globalMin_second, globalMin_third;
    static int globalMax_first, globalMax_second, globalMax_third;
    static int [] global_min = new int[3];
    static int [] global_max = new int[3];
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];

        getInput(); //입력 받는 메소드
        if(N==1) {
            int min_ans = Math.min(map[0][0], Math.min(map[0][1], map[0][2]));
            int max_ans = Math.max(map[0][0], Math.max(map[0][1], map[0][2]));
            System.out.printf("%d %d", max_ans, min_ans);
            return;
        }
        else {
            find_global_minmax();
            int min_ans = Math.min(global_min[0], Math.min(global_min[1], global_min[2]));
            int max_ans = Math.max(global_max[0], Math.max(global_max[1], global_max[2]));
            System.out.printf("%d %d", max_ans, min_ans);
        }
    }
    public static void getInput() throws IOException{
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }
    }
    public static void find_global_minmax() {

        //i번째 행에 대해 시행 중 i+1행을 고려하므로 i는 N-1보다 작은 범위 내에서 시행되어야 함.
        for(int i=0;i<N-1;i++) {
            if(i==0) {
                for(int j=0;j<3;j++) {
                    if(j==0) {
                        //min값
                        globalMin_first = map[i][j] + map[i+1][j];
                        globalMin_second = map[i][j] + map[i+1][j+1];
                        //max값
                        globalMax_first = map[i][j] + map[i+1][j];
                        globalMax_second = map[i][j] + map[i+1][j+1];

                    }
                    else if(j==1) {
                        //min값
                        globalMin_first = Math.min(globalMin_first, map[i][j] + map[i+1][j-1]);
                        globalMin_second = Math.min(globalMin_second, map[i][j] + map[i+1][j]);
                        globalMin_third = map[i][j] + map[i+1][j+1];
                        //max값
                        globalMax_first = Math.max(globalMax_first, map[i][j] + map[i+1][j-1]);
                        globalMax_second = Math.max(globalMax_second, map[i][j] + map[i+1][j]);
                        globalMax_third = map[i][j] + map[i+1][j+1];
                    }
                    else if(j==2) {
                        //min값
                        globalMin_second = Math.min(globalMin_second, map[i][j] + map[i+1][j-1]);
                        globalMin_third = Math.min(globalMin_third, map[i][j] + map[i+1][j]);
                        //max값
                        globalMax_second = Math.max(globalMax_second, map[i][j] + map[i+1][j-1]);
                        globalMax_third = Math.max(globalMax_third, map[i][j] + map[i+1][j]);
                    }
                }
                global_min[0] = globalMin_first;
                global_min[1] = globalMin_second;
                global_min[2] = globalMin_third;

                global_max[0] = globalMax_first;
                global_max[1] = globalMax_second;
                global_max[2] = globalMax_third;
//                System.out.printf("%d %d %d\n", global_max[0], global_max[1], global_max[2]);
            }
            else {
                for(int j=0;j<3;j++) {
                    if(j==0) {
                        //min값
                        globalMin_first = global_min[0] + map[i+1][j];
                        globalMin_second = global_min[0] + map[i+1][j+1];

                        //max값
                        globalMax_first = global_max[0] + map[i+1][j];
                        globalMax_second = global_max[0] + map[i+1][j+1];
                    }
                    else if(j==1) {
                        //min값
                        globalMin_first = Math.min(globalMin_first, global_min[1] + map[i+1][j-1]);
                        globalMin_second = Math.min(globalMin_second, global_min[1] + map[i+1][j]);
                        globalMin_third = global_min[1] + map[i+1][j+1];
                        //max값
                        globalMax_first = Math.max(globalMax_first, global_max[1] + map[i+1][j-1]);
                        globalMax_second = Math.max(globalMax_second, global_max[1] + map[i+1][j]);
                        globalMax_third = global_max[1] + map[i+1][j+1];
                    }
                    else if(j==2) {
                        //min값
                        globalMin_second = Math.min(globalMin_second, global_min[2] + map[i+1][j-1]);
                        globalMin_third = Math.min(globalMin_third, global_min[2] + map[i+1][j]);

                        //max값
                        globalMax_second = Math.max(globalMax_second, global_max[2] + map[i+1][j-1]);
                        globalMax_third = Math.max(globalMax_third, global_max[2] + map[i+1][j]);
                    }
                }
                global_min[0] = globalMin_first;
                global_min[1] = globalMin_second;
                global_min[2] = globalMin_third;

                global_max[0] = globalMax_first;
                global_max[1] = globalMax_second;
                global_max[2] = globalMax_third;
//                System.out.printf("%d %d %d\n", global_max[0], global_max[1], global_max[2]);
            }
        }
    }
}

```
