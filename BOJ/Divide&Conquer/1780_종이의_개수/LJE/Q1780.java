package DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780 {
    public static int[][] graph;
    public static int range;
    public static int[] result = new int[3];//-1, 0, 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        range = N;
        graph = new int[N][N];
        for(int i=0;i<N;++i){
            String str = br.readLine();
            st = new StringTokenizer(str, " ");
            for(int j=0;j<N;++j){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        checkGraph(0,0,range);

        for(int i:result)
            System.out.println(i);

    }

    public static void checkGraph(int x, int y, int c){
        int pivot = graph[x][y];

        for(int i=x;i<x+c;++i){
            for(int j=y;j<y+c;++j){
                if(pivot!=graph[i][j]){
                    makeNewGraph(x,y,c);
                    return;
                }
            }
        }
        countNumber(x,y);
    }

    public static void makeNewGraph(int x, int y, int c){
        for(int i=x;i<x+c;i+=c/3){
            for(int j=y;j<y+c;j+=c/3){
                checkGraph(i,j,c/3);
            }
        }
    }

    public static void countNumber(int x, int y){
        int pivot = graph[x][y];
        switch (pivot){
            case -1:
                result[0]++;
                break;
            case 0:
                result[1]++;
                break;
            case 1:
                result[2]++;
                break;
        }
    }
}
