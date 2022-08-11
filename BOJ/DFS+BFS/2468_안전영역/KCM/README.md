## 🧩풀이
1. k이하의 높이는 물에 잠기는 지역이다. (k의 값은 0부터, 주어진 배열의 원소 중 최대값 까지 될 수 있다.)

2. k를 정해준 후, 2차원 배열을 탐색하며 k이하인 지점을 모두 -1로 바꾸어준다.

3. "2중 for문"을 돌며 map[i][j] > -1 인 지역을 기준으로 bfs를 진행한다. (상하좌우 중 map[i][j] > -1 && !visited[i][j]인 지역에 대해서만 이동)

4. 이동을 못 할 경우 bfs를 탈출시키고 count의 값을 1 증가시켜준다.

5. map의 모든 원소에 대하여 bfs를 다 돌았을 때의 count값이 안전영역의 개수이다.

6. 모든 k값에 대하여 "(2~4)" 과정을 진행하며 count의 최대값을 저장한다. 

>count의 최대값이 최종 답이다!!

---

## 🧩 전체 코드
```java
import java.util.*;
import java.io.*;

class Safe{
    int row=0;
    int col=0;
    Safe(int r, int c){
        row = r;
        col = c;
    }
}
public class P_2468 {
    static int [][] map, copy;
    static boolean [][] visited;
    static int N, cnt;
    static int max_num, ans = -999;
    static Queue<Safe> q = new LinkedList<>();
    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int [N][N];
        copy = new int [N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max_num = Math.max(max_num, map[i][j]); //입력값 중 최대값을 찾기위한 과정.
            }
        }

        for(int k = 0; k<=max_num;k++){
            copy = map.clone();
            visited = new boolean[N][N];
            //기준 높이 이하인 부분을 모두 -1로 바꿔주는 과정.
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(copy[i][j]<=k){
                        copy[i][j] = -1;
                    }
                }
            }

            //bfs를 통해 안전영역이 몇 군데인지 찾는 과정 (count의 개수를 찾는다.)
            cnt=0;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(copy[i][j]>-1 && !visited[i][j]){
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }
    static void bfs(int n, int m){
        q.add(new Safe(n,m));
        visited[n][m] = true;

        while(!q.isEmpty()){
            Safe v = q.remove();
            for(int i=0;i<4;i++){
                int nr = v.row + mv[i][0];
                int nc = v.col + mv[i][1];
                if(nr>=0 && nc >=0 && nr<N && nc<N){
                    if(copy[nr][nc]>-1 && !visited[nr][nc]){
                        q.add(new Safe(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
}

```

---

🧩 Review
매우 쉬운 문제였다.

시키는 대로만 하면 됨
!!
