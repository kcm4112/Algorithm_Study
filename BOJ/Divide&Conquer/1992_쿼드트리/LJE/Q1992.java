package DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1992 {
    public static int[][] graph;
    public static String result="";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for(int i=0;i<N;++i){
            String str[] = br.readLine().split("");
            for(int j=0;j<N;++j){
                graph[i][j] = Integer.parseInt(str[j]);
            }
        }

        checkGraph(0,0,N);
        System.out.println(result);
    }

    public static void checkGraph(int x, int y, int c){
        int pivot = graph[x][y];
        for(int i=x;i<x+c;++i){
            for(int j=y;j<y+c;++j){
                if(pivot!=graph[i][j]){
                    makeNewGraph(x,y,c);
                    result += ")";
                    return;
                }
            }
        }
        result+=pivot;
    }

    public static void makeNewGraph(int x, int y, int c){
        result += "(";
        for(int i=x;i<x+c;i+=c/2){
            for(int j=y;j<y+c;j+=c/2){
                checkGraph(i,j,c/2);
            }
        }
    }
}
