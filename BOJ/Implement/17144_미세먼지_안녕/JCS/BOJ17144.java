package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17144 {
    static int R;
    static int C;
    static int T;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int [][][] map;
    static air up;
    static air down;
    static StringTokenizer stringTokenizer;
    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        T = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[R][C][T+1];
        for(int i =0;i<R;i++)
        {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for(int j =0;j<C;j++)
            {
                map[i][j][0] = Integer.parseInt(stringTokenizer.nextToken());
                if(map[i][j][0]==-1)
                {
                    if(up == null)
                    up = new air(i,j);
                    else down = new air(i,j);
                }
            }
        }

       int result =  sol();
        System.out.println(result);
    }

    private static int sol() {
        int count = 0;
        for(int i =0;i<T;i++)
        {
            for(int j =0;j<R;j++)
            {
                for(int k=0;k<C;k++)
                {
                    // 공기확산 실행
                    if(map[j][k][i]!=0)
                    {
                        spread(j,k,i);
                    }
                }
            }
            // 공기청정 실행
            clean_up(i);
            clean_down(i);
        }

        //남아 있는 미세먼지 합 구하기
        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                count += map[i][j][T];
            }
        }
        return count+2 ;
    }

    private static void clean_down(int cur_time) {
        int start_x = down.x;
        int start_y = down.y+1;
        int write = map[start_x][start_y][cur_time+1];
        map[start_x][start_y][cur_time+1] = 0;
        int save ;
        while (start_y+1<C)
        {
            save = map[start_x][start_y+1][cur_time+1];
            map[start_x][start_y+1][cur_time+1] = write;
            write = save;
            start_y++;
        }//start_y+1 => C-1 일 때까지 동작
        start_y = C-1;
        while (start_x+1<R)
        {
            save = map[start_x+1][start_y][cur_time+1];
            map[start_x+1][start_y][cur_time+1] = write;
            write = save;
            start_x++;
        }//start_x+1 => R-1
        start_x = R-1;
        while (start_y-1>=0)
        {
            save = map[start_x][start_y-1][cur_time+1];
            map[start_x][start_y-1][cur_time+1] = write;
            write = save;
            start_y--;
        }
        start_y = 0;
        while (start_x-1!= down.x)
        {
            save = map[start_x-1][start_y][cur_time+1];
            map[start_x-1][start_y][cur_time+1] = write;
            write = save;
            start_x--;
        }
    }

    private static void clean_up(int cur_time) {

        int start_x = up.x;
        int start_y = up.y+1;
        int write = map[start_x][start_y][cur_time+1];
        map[start_x][start_y][cur_time+1]=0;
        int save ;
        while (start_y+1<C)
        {
            save = map[start_x][start_y+1][cur_time+1];
            map[start_x][start_y+1][cur_time+1] = write;
            write = save;
            start_y++;
        }//start_y+1 => C-1 일 때까지 동작
        start_y = C-1;
        while (start_x-1>=0)
        {
            save = map[start_x-1][start_y][cur_time+1];
            map[start_x-1][start_y][cur_time+1] = write;
            write = save;
            start_x--;
        }//start_x-1 => 0일 때 까지
        start_x = 0;
        while (start_y-1>=0)
        {
            save = map[start_x][start_y-1][cur_time+1];
            map[start_x][start_y-1][cur_time+1] = write;
            write = save;
            start_y--;
        }
        start_y = 0;
        while (start_x+1!= up.x)
        {
            save = map[start_x+1][start_y][cur_time+1];
            map[start_x+1][start_y][cur_time+1] = write;
            write = save;
            start_x++;
        }


    }

    private static void spread(int x, int y,int cur_time) {
        if(x== up.x&&y==0) {
            map[x][y][cur_time+1]=-1;
            return;
        }
        if(x== down.x&&y==0){
            map[x][y][cur_time+1]=-1;
            return;
        }

        int cnt = 0;
        int [] x_dir = {0,0,-1,1};
        int [] y_dir = {1,-1,0,0};
        int spread_value = map[x][y][cur_time]/5;
        for(int i =0;i<4;i++)
        {
            int new_x = x+x_dir[i];
            int new_y = y+y_dir[i];
            if(new_x<0 || new_x>=R || new_y<0 || new_y>=C || (new_x==up.x&&new_y==0) || (new_x==down.x&&new_y==0))
                continue;
            map[new_x][new_y][cur_time+1] += spread_value;
            cnt++;
        }
        map[x][y][cur_time+1] +=(map[x][y][cur_time] - (spread_value)*cnt);
    }

    private static class air {
        int x;
        int y;

        public air(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
