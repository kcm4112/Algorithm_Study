package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node16174{
    int x, y;
    int weight;

    public Node16174(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }
}

public class Q16174 {
    public static int N;
    public static int[][] graph;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;++i){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;++j){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        findPath(0,0);
    }

    public static void findPath(int x, int y) {
        Queue<Node16174> queue = new LinkedList<>();
        queue.offer(new Node16174(x, y, graph[x][y]));

        while (!queue.isEmpty()) {
            Node16174 node = queue.poll();
            x = node.x;
            y = node.y;

            if (node.weight == -1) {
                System.out.println("HaruHaru");
                return;
            }

            if (x + node.weight >= N && y + node.weight >= N)
                continue;

            if(x + node.weight < N && !visited[x+node.weight][y]) {
                queue.offer(new Node16174(x + node.weight, y, graph[x + node.weight][y]));
                visited[x+ node.weight][y] = true;
            }
            if(y + node.weight < N && !visited[x][y+node.weight]) {
                queue.offer(new Node16174(x, y + node.weight, graph[x][y + node.weight]));
                visited[x][y+ node.weight] = true;
            }
        }
        System.out.println("Hing");
    }
}