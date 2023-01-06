package DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2630 {
    public static int[][] graph;
    public static int white=0, black=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Divide(0,0, N);
        System.out.println(white);
        System.out.println(black);

    }

    public static void Divide(int x, int y, int n){
        if(n==1 || Check(x, y, n)){
            if(graph[x][y]==0)
                white++;
            else
                black++;
            return;
        }

        else{
            Divide(x, y, n/2);
            Divide(x+n/2, y, n/2);
            Divide(x, y+n/2, n/2);
            Divide(x+n/2, y+n/2, n/2);
        }

    }

    public static boolean Check(int x, int y, int n){
        int color = graph[x][y];
        for (int i = x; i < x+n; i++) {
            for (int j = y; j < y+n; j++) {
                if(color!=graph[i][j])
                    return false;
            }
        }
        return true;
    }
}
