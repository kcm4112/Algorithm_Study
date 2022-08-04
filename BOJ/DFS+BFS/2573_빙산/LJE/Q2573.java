package Graph;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class iceberg{
    int row,col;

    iceberg(int x, int y){
        this.row = x;
        this.col = y;
    }
}

public class Q2573 {

    public static Queue<iceberg> queue = new LinkedList<>();//빙산 녹이기
    public static int[][] graph;
    public static boolean[][] visited, dfs_visited;
    public static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0;i<N;++i){
            String str2 = br.readLine();
            st = new StringTokenizer(str2, " ");
            for(int j=0;j<M;++j){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j]!=0) { // 빙산이 있다면 큐에 추가
                    queue.add(new iceberg(i, j));
                }
            }
        }

        int day = 0;
        while(true){
            int count = 0;//빙하의 개수
            dfs_visited = new boolean[N][M];
            for(iceberg i:queue){
                if(!dfs_visited[i.row][i.col] && graph[i.row][i.col]>0){
                    dfs_visited[i.row][i.col] = true;
                    DFS(i.row, i.col);
                    count++;
                }
            }
            if(count>1)
                break;

            day++;
            meltingIceberg();
            queue.clear();
            for(int i=0;i<N;++i)
                for(int j=0;j<M;++j)
                    if(graph[i][j]!=0)
                        queue.add(new iceberg(i, j));

            if(queue.isEmpty()){//빙하가 모두 녹았을 경우
                day = 0;
                break;
            }
        }

        System.out.println(day);
    }

    public static void meltingIceberg(){
        for(boolean[] temp:visited) Arrays.fill(temp, false);

        while(!queue.isEmpty()){
            iceberg ice = queue.poll();
            visited[ice.row][ice.col] = true;

            for(int i=0;i<4;++i) {
                int nx = ice.row + dx[i];
                int ny = ice.col + dy[i];

                if (!visited[nx][ny] && graph[nx][ny] == 0 && graph[ice.row][ice.col] > 0)
                    graph[ice.row][ice.col]--;
            }
        }
    }

    public static void DFS(int x, int y){
        for(int i=0;i<4;++i){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(graph[nx][ny]>0 && !dfs_visited[nx][ny]) {
                dfs_visited[nx][ny] = true;
                DFS(nx, ny);
            }
        }
    }

}