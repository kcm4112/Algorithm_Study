## 📕풀이


아주아주 재미있는 문제였음

우선 입력값을 이해하는데 오래걸렸다. 입력값이 예를 들어서 3 3 0 1 이렇게 들어오면 그 의미는 아래와 같다.
> 0세대의 시작 위치가 (3,3) 이고 시작 방향은 오른쪽이며 1세대 까지 그려라!

입력 값이 3개이므로, 우리는 서로 다른 드래곤커브 놈들을 3개 모두 하나의 좌표평면위에 그려야 한다!

그럼 드래곤 커브는 어떻게 그릴까?
1세대는 0세대를 시계방향으로 회전하고, 2세대는 1세대 전체는 시계방향으로 회전하고, 이렇게 회전한 것을 이전 세대의 끝점에 이어붙어야 한다.

자 그럼 각 세대는 무엇을 의미할까?
각각의 의미는 아래와 같다.


1세대 = 0세대 + 0세대 회전한거 붙인거
2세대 = 1세대 + 1세대 회전한거 붙인거
3세대 = 2세대 + 2세대 회전한거 붙인거

자 그럼 방향을 우-상-좌-하 (반시계) : 0-1-2-3 으로 표현할 수 있다면 각 세대의 방향은 어떻게 될까?

우선 문제 설명에서 보여준 것을 참조해서 설명하면 0세대에서 처음 방향이 오른쪽(0)이었고
1세대 : 0 - 1
2세대 : 0 - 1 - 2 - 1
3세대 : 0 - 1 - 2 - 1 - 2 - 3 - 2 - 1 이 될 것이다. 규칙을 찾으면 문제를 빨리 풀 수 있다. 어쨋든 시계방향으로 회전시킨다는 것은 각 드래곤 커브의 방향에 규칙이 생길 수 있다는 것을 짐작해볼 수 있다.
K세대는 K-1세대의 방향의 리스트를 끝에서부터 1씩 더하여 이어붙인 것과 같다!

이렇게 한 줄을 입력받을 때마다 주어진 세대까지의 방향을 다 구하여, 처음 세대의 좌표부터 map을 탐색하며 true를 만들어주고,
마지막에는 브루트포스 방식으로 완전탐색을 하여 네 꼭지점이 다 true인 사각형의 개수를 구해주면 된다!

---

## 📕코드

```java
import java.io.*;
import java.util.*;

//하나씩 입력받을 때마다 map에 드래곤 커브 그려주기!
public class P_15685 {
    static int N;
    static List<Integer> list; //방향을 담아놓는 변수
    static int answer = 0; //정답의 개수를 구하는 변수
    static boolean [][] map = new boolean[101][101]; //해당 꼭짓점이 드래곤 커브의 일부인지 아닌지 저장하는 배열
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        //0 - 오른쪽, 1 - 위쪽, 2 - 왼쪽, 3 - 아래쪽
        for(int i=0;i<N;i++) {
            list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); //방향
            int g = Integer.parseInt(st.nextToken()); //세대
            list.add(d); //시작 방향 저장하고, 입력받은 세대까지의 모든 방향 list 구해주기
            getDirection(g);
            makeMap(x, y); //입력받은 시작 위치를 바탕으로 방향이 저장된 list를 이용하여 드래곤 커브를 그린다.
        }
        findDragon();
        System.out.println(answer);

    }

    public static void getDirection(int g) {
        //list에 저장된 시작방향을 토대로 입력받은 g세대 까지의 모든 방향을 구해주어야 한다.
        for(int i=1;i<=g;i++) {//입력 받은게 0세대 방향이니깐, 1세대부터 g세대까지.
            int size = list.size();
            for(int j=0;j<size;j++) {
                //0세대 - {1}
                //1세대 - {1 2}
                //2세대 - {1 2  3 2} 잘 보면 반으로 나누고 거꾸로 탐색하며 1씩 더해준 모습임
                list.add((list.get(size-j-1) + 1) % 4);
            }
        }
    }
    public static void makeMap(int x, int y) {
        //Map에 그릴 때 만약 (x,y) = (4,2)라면 Map 행렬에서는 2행 4열임을 주의하자!!! 생각생각생각
        //우선 최초 시작 지점은 true로 바꾸어 주어야 한다!
        map[y][x] = true;
        //실시간으로 가장 최근 선분의 끝 지점을 저장해야하므로 변수를 생성한다.
        int ny = y;
        int nx = x;
        for(int i=0;i<list.size();i++) {
            int direction = list.get(i); //그릴 방향을 먼저 찾는다.
            switch (direction) {
                case 0 : // 오른쪽
                {
                    nx = nx+1;
                    map[ny][nx] = true;
                    break;
                }
                case 1 : // 위쪽
                {
                    ny = ny - 1;
                    map[ny][nx] = true;
                    break;
                }
                case 2 : // 왼쪽
                {
                    nx = nx - 1;
                    map[ny][nx] = true;
                    break;
                }
                case 3 : //아래쪽
                {
                    ny = ny + 1;
                    map[ny][nx] = true;
                    break;
                }
                default:
                    break;
            }
        }
    }
    public static void findDragon() {
        //행과 열은 99까지 탐색해야한다.
        //만약 100까지 탐색을하면 +1을 했을 때 index 101을 참조하게 되므로 런타임에러남!!
        for(int i=0; i<=99;i++) {
            for(int j=0;j<=99;j++) {
                if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
                    answer++;
                }
            }
        }
    }
}

```
