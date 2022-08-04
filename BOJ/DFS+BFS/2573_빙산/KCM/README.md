>상하좌우 탐색에 관한 문제는 그래프 이론상 BFS(너비우선탐색) 알고리즘으로 해결가능 하다는 것을 염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!염두하자!

## 🧩 풀이

1. 우선, 빙산이 1개로 이어져있는지부터 탐색한다. (BFS를 한 번 시행 했는데, map[i][j] > 0인 모든 곳에 대하여 visited[i][j] = true이면 빙산이 하나로 이어진 경우이다!!)

2. 1개로 이어져 있다면,  map[i][j] > 0 인 부분에 대하여 상하좌우에 0인 부분(바다)가 몇 개인지 파악한 후, 빙산의 높이를 다시 update 해준다.

3. 1번과 2번 과정을 while문을 통해 빙산이 2개로 나누어질 때까지 무한반복한다.

## 🚨예외🚨

>주어진 배열이 모두 0일 경우 : BFS를 탐색할 수 없으므로 따로 예외처리를 해준다.
>나같은 경우는 BFS탐색을 하는 횟수를 따로 변수에 저장하였는데 이 변수의 값이 의미하는 바는 다음과 같다.
>1. 변수 == 0 : BFS를 시행하지 않은 경우이므로, 예제로 주어진 배열의 모든 값이 0인 경우.
>2. 변수 == 1 : BFS를 한 번 시행한 경우이므로, 빙산이 하나로 연결되어있다는 뜻.
>3. 변수 > 1 : BFS를 두 번 이상 시행한 경우이므로, 빙산이 최소 2개로 나누어져있다는 뜻.

아래 코드의 bfs는 map[i][j] > 0인 부분을 처음 만나는 부분에서 처음 시작하는데 
만약, 빙산이 상하좌우로 이어져있어서 하나라면, map[i][j] > 0 인 부분의 visited[i][j]가 모두 true가 되어 BFS를 호출하는 조건문을 만족하지 않아 BFS는 한 번 밖에 호출되지 않을 것이다!

```java
static void bfs(int r, int c){
        Queue<Point> q = new LinkedList<>();
        visited[r][c] = true;
        q.add(new Point(r, c));

        while(!q.isEmpty()){
            //먼저 빙산을 모두 탐색한다. 만약 한 번의 bfs 호출을 통해 모든 빙산이 있는 부분이 true가 되면 모든 빙산이 상하좌우로 이어져있다는 뜻이므로 빙산은 1개!
            Point v = q.remove();
            for(int i=0;i<4;i++){
                int nr = v.row + mv[i][0];
                int nc = v.col + mv[i][1];
                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if(map[nr][nc] > 0 && !visited[nr][nc]){
                        q.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
```
위의 BFS를 아래의 경우일 때와 같이 시행하며 num을 계산해준다!

```java
//빙산이 2개인지 체크 -> 아니라면 높이 줄이기 과정을 무한반복!!
        year = 0;
        while(true){
            visited = new boolean[N][M]; // 매년 빙산의 모습이 바뀌므로, visited맵을 초기화해준다!
            num = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j]!=0 && !visited[i][j]){
                        bfs(i,j);
                        num++;
                    }
                }
            }
            if(num > 1){
                break;
            }
            if(num==0){
                break;
            }
```

정확한 이해를 위해 아래 전체 코드의 주석을 보며 이해하면 빠르다!!

마지막의 주의사항도 읽어주길 바란다.

---

## 🧩 전체 코드

```java
import java.util.*;
import java.io.*;
class Point{
    int row = 0;
    int col = 0;
    Point(int r, int c){
        row = r;
        col = c;
    }
}
public class P_2573 {
    static int [][] map;
    static boolean [][] visited;
    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우 이동.
    static int N;
    static int M;
    static  int year;
    static  int num;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int [N][M]; //빙산
        //초기 빙산 상태 입력
        for(int i=0;i<N;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(s.nextToken());
            }
        }
        //빙산이 2개인지 체크 -> 아니라면 높이 줄이기 과정을 무한반복!!
        year = 0;
        while(true){
            visited = new boolean[N][M]; // 매년 빙산의 모습이 바뀌므로, visited맵을 초기화해준다!
            num = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j]!=0 && !visited[i][j]){
                        bfs(i,j);
                        num++;
                    }
                }
            }
            if(num > 1){
                break;
            }
            if(num==0){
                break;
            }
            int [][] newmap = new int[N][M];
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j]>0){
                        int num_zero=0;
                        for(int k=0;k<4;k++){
                            int nnr = i + mv[k][0];
                            int nnc = j + mv[k][1];
                            if(map[nnr][nnc]==0){ //주변에 0개수 count!
                                num_zero++;
                            }
                        }
                        int new_num = map[i][j] - num_zero;
                        if(new_num<0){ //0개수를 뺏는데 음수면 그냥 0으로 업데이트!
                            newmap[i][j] = 0;
                        }
                        else{
                            newmap[i][j] = new_num;
                        }
                    }
                }
            }
            map = newmap.clone();
            year++; //빙산을 녹인후 1년을 ++ 해준다.
        }
        if(num == 0){
            System.out.println(0);
        }
        else{
            System.out.println(year);
        }
    }
    static void bfs(int r, int c){
        Queue<Point> q = new LinkedList<>();
        visited[r][c] = true;
        q.add(new Point(r, c));

        while(!q.isEmpty()){
            //먼저 빙산을 모두 탐색한다. 만약 한 번의 bfs 호출을 통해 모든 빙산이 있는 부분이 true가 되면 모든 빙산이 상하좌우로 이어져있다는 뜻이므로 빙산은 1개!
            Point v = q.remove();
            for(int i=0;i<4;i++){
                int nr = v.row + mv[i][0];
                int nc = v.col + mv[i][1];
                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if(map[nr][nc] > 0 && !visited[nr][nc]){
                        q.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
}
```

---

## 🧩 Review

**🚨주의🚨

처음에, 빙산의 높이를 줄여줄 때 하나 하나의 값에 대하여 상하좌우의 바다 개수를 구한 후 바로 그 개수만큼 빼주어서 update했다.

그러면 아래와 같은 문제가 생긴다.

![image](https://user-images.githubusercontent.com/80253559/182764495-08d85015-ac2c-4717-ab27-258ccda6fbe9.png)

>1. "2"는 원래 주변의 바다 개수가 2개이므로 1년후 높이는 0이 됨.
>2. "4"는 원래 주변의 바다 개수가 2개여서 "2"로 바뀌어야 하는데, 1번 과정에서 "2"가 "0"으로 바뀌어서 주변 바다개수를 3개로 인식하여 "4" -> "1" 이 되어버리는 문제 발생;;

**💡해결💡

기존의 map은 그대로 놔두고 newmap을 만들어서 바뀐 값만 저장한 후, 이 값들을 map에 다시 clone한다!

>느낀점 : 무언가를 수정 할 때는 원본을 건드려도 되는지 안되는지 항상 생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!생각하자;;!!!!!


