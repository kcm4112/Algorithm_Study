## 🧩풀이

1. 행, 열을 N, M으로 하고 행과 열의 Layer 수를 H로 할당해주었다.

2. 주어진 입력값에서 1인 부분을 큐에 삽입하였다.

3. 삽입이 끝나면 dfs를 진행한다.

4. dfs구현 시 중점은 다음과 같다.

>- 큐에 삽입된 인덱스에 대하여 상하좌우위아래를 탐색하고 만약 0을 만나게 된다면 현재 index값에서 1을 더한 값으로 update해준다 (1을 더하는 이유는 1일이 지났다고 가정하는 것임.)

---

## 🧩전체 코드

```java
import java.nio.Buffer;
import java.util.*;
import java.io.*;

class Point{
    int r, c, h;
    Point(int row, int col, int high){
        r = row;
        c = col;
        h = high;
    }
}


public class P_7569 {
    static int N, M, H;
    static int[][][] map;
    static int[][] mv = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}}; //상,하,좌,우,위,아래.
    static int allzero, total, res = 0;
    static Queue<Point> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M][H];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                StringTokenizer s = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[j][k][i] = Integer.parseInt(s.nextToken());
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k][i] == 1) {
                        q.add(new Point(j, k, i));
                    }
                }
            }
        }
        bfs(); //1이 있다면 거기서부터 최대한 만들 수 있는 만큼 주변의 토마토를 1로 만든다!
        int min = -999;
        //이제 전체 map 중에 안익은 것이 있는지 판단!
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    res = Math.max(map[j][k][i], res);
                    if (map[j][k][i] == 0) {
                        total = -1;
                    }
                }
            }
        }
        if (total == -1) {
            System.out.println(-1);
        } else if (allzero == 0) {
            System.out.println(0);
        } else {
            System.out.println(res - 1);
        }
    }

    static void bfs(){

        while (!q.isEmpty()) {
            Point v = q.remove();
            for (int a = 0; a < 6; a++) {
                int nr = v.r + mv[a][0];
                int nc = v.c + mv[a][1];
                int nh = v.h + mv[a][2];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && nh >= 0 && nh < H) {
                    if (map[nr][nc][nh] == 0) {
                        map[nr][nc][nh] = map[v.r][v.c][v.h] + 1;
                        q.add(new Point(nr, nc, nh));
                        allzero = 1;
                    }
                }
            }
        }
    }
}
```

---

## 🧩 Review

자세한 내용은 전체 코드를 참고하길 바란다. 주의깊게 생각해야했던 부분에 대해서만 이야기 하겠다!

 

- 처음 나의 생각 : map에서 1인곳을 기준으로 bfs를 통해 탐색하다가 0을 만나면 안익은 토마토의 주변을 탐색하여 익은 토마토로 바꾸어 주는 과정 반복 (day++ 해주면서!) -> 이후 map에서 1인곳을 기준으로 bfs를 통해 탐색하다가 0을 더이상 만나지 않으면 day 출력!

🚨실패 이유🚨

>처리해주어야 할 조건들이 너무 많아서 나중에 결국 어디를 손봐야 될지 몰라서 포기하였다.

- 두번째 나의 생각 : map을 탐색하며 1을 찾으면 그곳에서부터 시작하여 "- 큐에 삽입된 인덱스에 대하여 상하좌우위아래를 탐색하고 만약 0을 만나게 된다면 현재 index값에서 1을 더한 값으로 update해준다 (1을 더하는 이유는 1일이 지났다고 가정하는 것임.)" 다음과 같은 방법으로 map전체를 update한다.

🚨실패 이유🚨

>map에 1이 하나일 경우에는 가능하지만 여러개 있을 경우는 성립되지 못함.
>너무 단순하게 생각을 했다.
