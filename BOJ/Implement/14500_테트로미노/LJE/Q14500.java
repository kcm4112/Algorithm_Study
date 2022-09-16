package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14500 {

    public static int[][] graph;
    public static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};
    public static int[][] dx2 = {{0,1,2,1}, {0,0,0,-1}, {0,0,0,1},{0,-1,0,1}};
    public static int[][] dy2 = {{0,0,0,1}, {0,1,2,1}, {0,1,2,1},{0,1,1,1}};
    public static boolean[][] visited;
    public static int result, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0;i<N;++i){
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for(int j=0;j<M;++j){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                visited[i][j] = true;
                DFS(i,j, graph[i][j], 1);
                visited[i][j] = false;
                CheckExtra(i,j);
            }
        }

        System.out.println(result);

    }

    public static void DFS(int x, int y, int num, int length){
        if(length>=4){
            result = Math.max(result, num);
            return;
        }
        for(int i=0;i<4;++i){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0||nx>=N||ny<0||ny>=M) continue;

            if(!visited[nx][ny]){
                visited[nx][ny] = true;
                DFS(nx, ny, num + graph[nx][ny], length+1);
                visited[nx][ny] = false;
            }
        }
    }

    public static void CheckExtra(int x, int y){
        for(int i=0;i<4;++i){
            boolean check = false;
            int sum=0;
            for(int j=0;j<4;++j){
                int nx = x + dx2[i][j];
                int ny = y + dy2[i][j];

                if(nx<0||nx>=N||ny<0||ny>=M) {
                    check = true;
                    break;
                }
                else{
                    sum += graph[nx][ny];
                }
            }
            if(!check){
                result = Math.max(result, sum);
            }
        }
    }

}