package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6087 {


    static int W;
    static int H;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char [][] map;

    static int []Cpos_x= new int[2];
    static int []Cpos_y=new int[2];
    static int [] dir_x = {1,-1,0,0};
    static int [] dir_y = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        int cnt =0;
        for(int i =0;i<H;i++)
        {
            String line = br.readLine();
            for(int j = 0;j< W;j++)
            {
                map[i][j] = line.charAt(j);
                if(map[i][j]=='C')
                {
                    Cpos_x[cnt]=i;
                    Cpos_y[cnt++]=j;
                }
            }
        }

        System.out.println(solution());
    }

    private static int solution() {

        int min = Integer.MAX_VALUE;
        int start_x = Cpos_x[0];
        int start_y = Cpos_y[0];
        int [][][]visited = new int[4][H][W];
        for(int i=0;i<4;i++)
        {
            for (int j=0;j<H;j++)
                Arrays.fill(visited[i][j] , Integer.MAX_VALUE);
        }
        Queue<spot> queue = new PriorityQueue<>();
        queue.offer(new spot(start_x,start_y,5,-1));

        while (!queue.isEmpty())
        {
            spot cur = queue.poll();

            if(cur.x==Cpos_x[1] && cur.y==Cpos_y[1])
            {
                min = Math.min(min, cur.miror);
                continue;
            }
            for (int i =0;i<4;i++)
            {
                if(cur.pre_dir==0 && i ==1) continue;
                if(cur.pre_dir==1 && i ==0) continue;
                if(cur.pre_dir==2 && i ==3) continue;
                if(cur.pre_dir==3 && i ==2) continue;
                int nx = cur.x + dir_x[i];
                int ny = cur.y + dir_y[i];
                if(check(nx,ny))
                {
                    int temp = cur.miror;
                    if(cur.pre_dir!=i)
                    {
                        temp++;
                    }
                    if(visited[i][nx][ny] > temp)
                    {
                        visited[i][nx][ny] = temp;
                        queue.offer(new spot(nx,ny,i,temp));
                    }
                }

            }
        }


        return min;
    }
    private static boolean check(int x,int y)
    {
        if(x>=H || x<0) return false;
        if(y>=W || y<0)return false;
        if(map[x][y]=='*') return false;
        return true;
    }

    private static class spot implements Comparable<spot>
    {
        int x;
        int y;
        int pre_dir;
        int miror;

        public spot(int x, int y, int pre_dir, int miror) {
            this.x = x;
            this.y = y;
            this.pre_dir = pre_dir;
            this.miror = miror;
        }

        @Override
        public int compareTo(spot o) {
            return miror -o.miror;
        }
    }
}
