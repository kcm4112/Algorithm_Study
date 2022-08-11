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
