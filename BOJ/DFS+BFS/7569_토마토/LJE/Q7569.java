package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Tomato{
    int height, row, col;

    Tomato(int z, int x, int y){
        this.height = z;
        this.row = x;
        this.col = y;
    }
}
public class Q7569 {
    public static int M,N,H;
    public static int[][][] graph;
    public static int[][][] day;
    public static Queue<Tomato> queue = new LinkedList<>();
    public static int[] dx = {-1,1,0,0,0,0}, dy = {0,0,-1,1,0,0}, dz = {0,0,0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        graph = new int[H][N][M];
        day = new int[H][N][M];
        for(int[][] temp:day) for(int[] temp2:temp) Arrays.fill(temp2, -1);
        for(int i=0;i<H;++i){ //그래프 초기화
            for(int j=0;j<N;++j){
                String str2 = br.readLine();
                st = new StringTokenizer(str2, " ");
                for(int k=0;k<M;++k){
                    graph[i][j][k] = Integer.parseInt(st.nextToken());
                    if(graph[i][j][k] == 1) {
                        queue.add(new Tomato(i, j, k));
                        day[i][j][k] = 0;
                    }
                }
            }
        }

        makeTomato();

        int result = 0;
        for(int i=0;i<H;++i){
            for(int j=0;j<N;++j){
                for(int k=0;k<M;++k){
                    if(graph[i][j][k] == 0){
                        result = -1;
                        System.out.println(result);
                        return;
                    }
                    result = Math.max(result, day[i][j][k]);
                }
            }
        }

        System.out.println(result);
    }

    public static void makeTomato(){
        while(!queue.isEmpty()){
            Tomato t = queue.poll();
            int height = t.height, row = t.row, col = t.col;

            for(int i=0;i<6;++i){
                int nz = height + dz[i], nx = row + dx[i], ny = col + dy[i];

                if(nz>=0 && nz<H && nx>=0 && nx<N && ny>=0 && ny<M && graph[nz][nx][ny]==0 && day[nz][nx][ny]==-1){
                    graph[nz][nx][ny] = 1;
                    day[nz][nx][ny] = day[height][row][col] + 1;
                    queue.add(new Tomato(nz, nx, ny));
                }
            }
        }
    }
}
