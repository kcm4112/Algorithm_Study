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
