## 풀이 
---

1. 우선 주어진 입력값을 받은 다음 2중 for문을 돌며 .이 아닌 문자를 만날 때마다 bfs 탐색을 시작한다. </br>
2. 주어진 map의 (0,0)부터 (11, 5)까지 2중 for문을 한번 완료할 때마다 그 횟수를 체크한다. (변수명, pyuo) </br>
3. 1번 과정에서 bfs탐색을 수행하면서 bfs를 시작한 지점의 문자와 같은 문자가 상하좌우로 4개 이상 연결되어 있다면 지워준다. </br>
4. pyuo 과정 내에서 연쇄가 일어난다면 그 횟수를 체크한다 (변수명 , count) </br>
5. 만약에 pyuo 1회 수행 이후 즉, 2중 for문을 수행한 후에, count의 값이 0이상이라면 수행 중 연쇄가 적어도 한 번 일어났다는 뜻이므로 다음 pyuo과정의 수행을 위해 빈칸을 메꾸어주는 delete() 를 수행한 후, ans 변수를 +1 해준다. ans는 2중 for문을 완료한 횟수를 의미한다! </br>
6. 만약에 pyuo 를 1회 수행하였는데 count가 0이라면 즉, 애초에 연쇄할 조합이 없는 경우에는 0을 출력한다. </br>

---

## 코드

```java
  import java.io.*;
import java.util.*;
class Pyuo {
    int row;
    int col;
    Pyuo(int rr, int cc) {
        row = rr;
        col = cc;
    }
}
public class P_11559 {
    static char [][] map;
    static boolean [][] checked;
    static boolean flag;
    static int cnt = 0;
    static int count = 0;
    static int ans=0; //연쇄 횟수 탐색
    static int pyuo = 0; //연쇄 횟수 (bfs를 몇바퀴 돌았는지 체크하는 변수)
    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        checked = new boolean[12][6];
        for(int i=0;i<12;i++) {
            map[i] = br.readLine().toCharArray();
        }
        while(true) {
            count = 0;
            for(int i=0;i<12;i++) {
                for(int j=0;j<6;j++) {
                    if(map[i][j] != '.') {
                        char target = map[i][j];
                        cnt = 1;
                        pyuo(i, j, target);
                    }
                }
            }
            pyuo++;
            if(count > 0) {
                ans++; //연쇄 횟수 +1회
                delete();
            }
            else if(count == 0) { // 연쇄 1회차 부터 터트릴 것이 없다면?
                System.out.println(ans);
                break;
            }
            else if(pyuo == 1 && count == 0) {
                System.out.println(0);
                break;
            }

        }
    }
    public static void pyuo(int r, int c, char target) {
        Queue<Pyuo> q = new LinkedList<>();
        List<Pyuo> list = new ArrayList<>();
        list.add(new Pyuo(r, c));

        checked = new boolean[12][6];
        q.add(new Pyuo(r, c));
        checked[r][c] = true;
        while(!q.isEmpty()) {
            Pyuo v = q.remove();
            for(int k=0;k<4;k++) {
                int nr = v.row + mv[k][0];
                int nc = v.col + mv[k][1];
                if(nr >= 0 && nr < 12 && nc >= 0 && nc < 6) {
                    if(!checked[nr][nc] && map[nr][nc] == target) {
                        checked[nr][nc] = true;
                        q.add(new Pyuo(nr, nc));
                        list.add(new Pyuo(nr, nc));
                        cnt++;
                    }
                }
            }
        }
        if(cnt >= 4) {
            count++;
            for (int n = 0; n < list.size(); n++) {
                Pyuo p = list.get(n);
                map[p.row][p.col] = '.';
            }
        }
    }
    public static void delete() {
        for(int j=0; j<6;j++) {
            List<Character> arr = new ArrayList<>();
            for(int i=11;i>=0;i--) {
                if(map[i][j] != '.') {
                    arr.add(map[i][j]);
                    map[i][j] = '.';
                }
            }
            int index = 11;
            for(int i=0;i<arr.size();i++) {
                map[index][j] = arr.get(i);
                index--;
            }
        }
    }
}

```
---
## Review
구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 
구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 
구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 
구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 구현 문제는 조건을 생각할 것이 많아서 힘들다... 



