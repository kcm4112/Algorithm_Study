package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1987 {
    public static int R, C;
    public static String[][] graph;
    public static boolean[][] visited;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new String[R][C];
        visited = new boolean[R][C];
        for(int i=0;i<R;++i){
            graph[i] = br.readLine().split("");
        }

        DFS(0,0, graph[0][0], 1);
        System.out.println(result);
    }

    public static void DFS(int x, int y, String str, int count){

        result = Math.max(result, count);

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= R || ny < 0 || ny >= C||visited[nx][ny]) {
                continue;
            }
            if (!str.contains(String.valueOf(graph[nx][ny]))) {
                visited[nx][ny] = true;
                DFS(nx, ny, str+graph[nx][ny],count + 1);
                visited[nx][ny] = false;
            }
        }

    }

}