package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2468 {

    public static int[][] graph;
    public static boolean[][] visited;
    public static int N;
    public static int[] dx={-1,0,1,0}, dy={0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];

        String str;
        int max_height=0;
        for(int i=0;i<N;++i){
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for(int j=0;j<N;++j){
                graph[i][j] = Integer.parseInt(st.nextToken());
                max_height = Math.max(max_height, graph[i][j]);
            }
        }

        int result=0;
        for(int i=0;i<max_height;++i){
            for(boolean[] temp:visited) Arrays.fill(temp, false);
            makeIsland(i);
            result = Math.max(result, checkIsland());
        }

        System.out.println(result);
    }

    public static void makeIsland(int h){
        for(int i=0;i<N;++i)
            for(int j=0;j<N;++j)
                if(h>=graph[i][j])
                    graph[i][j]-=h;
    }

    public static void DFS(int x, int y){
        visited[x][y] = true;
        for(int i=0;i<4;++i){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && graph[nx][ny]>0){
                visited[nx][ny] = true;
                DFS(nx, ny);
            }
        }
    }

    public static int checkIsland(){
        int count = 0;
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j){
                if(!visited[i][j] && graph[i][j]>0) {
                    DFS(i, j);
                    count++;
                }
            }
        }

        return count;
    }

}
