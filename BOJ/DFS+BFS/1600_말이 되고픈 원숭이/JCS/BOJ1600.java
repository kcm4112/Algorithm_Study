package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {

    static int K;
    static int W, H ;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean [][] map;
    static int[] M_movex = {0,1,0,-1};
    static int [] M_movey = {1,0,-1,0};
    static int[] H_movex = {-1,-1,1,1,-2,-2,2,2};
    static int [] H_movey = {-2,2,-2,2,1,-1,-1,1};
    public static void main(String[] args) throws IOException {
        StringTokenizer st ;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new boolean[H][W];
        for(int i =0;i<H;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<W;j++)
            {
                map[i][j] = (Integer.parseInt(st.nextToken())==1) ? true : false;
            }

        }

        System.out.println(solution());

    }

    private static int solution() {
        boolean [][][] visited = new boolean[H][W][K+1];
        visited[0][0][K] = true;
        Queue<SPOT> bfs = new LinkedList<>();
        bfs.add(new SPOT(0,0,0,K));

        int min = -1;
        while (!bfs.isEmpty()) {
            SPOT cur = bfs.poll();
            if (cur.x == H - 1 && cur.y == W - 1) return cur.mCnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + M_movex[i];
                int ny = cur.y + M_movey[i];

                if (nx >=0 && ny >=0  && nx < H && ny < W && map[nx][ny] != true&& !visited[nx][ny][cur.hCnt]) {
                    visited[nx][ny][cur.hCnt] = true;
                    bfs.offer(new SPOT(nx, ny, cur.mCnt + 1, cur.hCnt));
                }
            }

            if (cur.hCnt > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + H_movex[i];
                    int ny = cur.y + H_movey[i];

                    if (nx >= 0 && ny >= 0 && nx < H && ny < W &&map[nx][ny] != true && !visited[nx][ny][cur.hCnt-1]) {
                        visited[nx][ny][cur.hCnt - 1] = true;
                        bfs.offer(new SPOT(nx, ny, cur.mCnt + 1, cur.hCnt - 1));
                    }
                }
            }

        }
        return min;
    }
  private static class SPOT
  {
      int x;
      int y;
      //원숭이처럼 몇 번 이동해서 왔는지
      int mCnt;
      int hCnt;

      public SPOT(int i, int i1, int i2, int k) {
          this.x = i;
          this.y = i1;
          this.mCnt = i2;
          this.hCnt = k;
      }
  }


}
