package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15685 {
    public static boolean[][] graph = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;++i){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            makeGraph(x, y, getDirection(d, g));
        }

        int answer=0;
        for(int i=0;i<100;++i){
            for(int j=0;j<100;++j){
                if(graph[i][j] && graph[i+1][j] && graph[i][j+1] && graph[i+1][j+1])
                    answer++;
            }
        }

        System.out.println(answer);
    }

    public static ArrayList<Integer> getDirection(int d, int g){
        ArrayList<Integer> direction = new ArrayList<>();
        direction.add(d);
        while(g>0){
            for(int i= direction.size()-1;i>=0;--i){
                int dir = (direction.get(i)+1)%4;
                direction.add(dir);
            }
            g--;
        }
        return direction;
    }

    public static void makeGraph(int x, int y, ArrayList<Integer> dir){
        graph[x][y] = true;
        for (Integer integer : dir) {
            switch (integer){
                case 0:
                    graph[++x][y] = true;
                    break;
                case 1:
                    graph[x][--y] = true;
                    break;
                case 2:
                    graph[--x][y] = true;
                    break;
                case 3:
                    graph[x][++y] = true;
                    break;
            }
        }
    }
}
