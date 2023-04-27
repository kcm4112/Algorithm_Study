package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3197 {
    static int R;
    static int C;
    static char [][] map ;
    static Queue<node> waterQ =new LinkedList<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static node start,end;
    static int[] dir_x = {0,0,-1,1};
    static int[] dir_y = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int i=0;i<R;i++)
        {
            String line = br.readLine();
            for (int j=0;j<C;j++)
            {
                map[i][j] = line.charAt(j);
                if(map[i][j]=='L')
                {
                    if(start==null)
                    start = new node(i,j);
                    else end = new node(i,j);
                }
                if (map[i][j]!='X') {
                    waterQ.offer(new node(i,j));
                }
            }
        }

        System.out.println(solution());
    }

    private static int solution() {

        int day =0;
        boolean meet = false;
        boolean [][] visited = new boolean[R][C];
        Queue<node> queue = new LinkedList<>();

        queue.offer(start);
        visited[start.x][start.y] = true;

        while (true) {
            Queue<node> next = new LinkedList<>();
            while (!queue.isEmpty()) {
                node cur = queue.poll();

                if (cur.x == end.x && cur.y == end.y)
                {
                    meet = true;
                    break;
                }

                for(int i=0;i<4;i++) {
                    int nx = cur.x + dir_x[i];
                    int ny = cur.y + dir_y[i];

                    if(nx<0 || ny <0 || nx>=R || ny>=C||visited[nx][ny]) continue;

                    visited[nx][ny]= true;
                    if(map[nx][ny]=='X') {
                        next.offer(new node(nx, ny));
                        continue;
                    }
                    queue.offer(new node(nx,ny));
                }
            }
            if(meet) break;
            queue = next;
            int len = waterQ.size();
            for(int i=0;i<len;i++)
            {
                node temp = waterQ.poll();

                for(int j=0;j<4;j++)
                {
                    int x = temp.x+dir_x[j];
                    int y = temp.y+dir_y[j];

                    if(x<0 || y<0 || x>=R || y>=C || visited[x][y])continue;

                    if(map[x][y]=='X')
                    {
                        map[x][y]='.';
                        waterQ.offer(new node(x,y));
                    }
                }
            }
            day++;
        }

        return day;
    }

    static class node{
        int x;
        int y;
        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
