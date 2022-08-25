import java.util.*;
import java.io.*;

class People{
    int row = 0;
    int col = 0;
    People(int v1, int v2){
        row = v1;
        col = v2;
    }
}
class Index{
    int v1=0;
    int v2=0;
    Index(int s1, int s2){
        v1 = s1;
        v2 = s2;
    }
}
public class P_16234 {
    static int N, L, R, day=0;
    static int flag = 1;
    static int [][] map;
    static boolean [][] visited;
    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int total = 0;
    static List<Index> list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        //맵 구성하기.
        for(int i=0;i<N;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(s.nextToken());
            }
        }

        while(true){
            boolean isUnion = false;
            visited = new boolean[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    flag=1;
                    total = 0;
                    if(!visited[i][j]){ //원래 bfs시에는 인구이동이 안됐는데, 값이 update되어 for문을 돌다가 값자기 인구이동이 되는 경우 방지.
                        bfs(i,j);
                        if(flag>1){ //연합이 되었다면 숫자 바꿔주기.
                            int temp = total / flag;
                            for(int k=0;k<list.size();k++){
                                int rr = list.get(k).v1;
                                int cc = list.get(k).v2;
                                map[rr][cc] = temp;
                            }
                            isUnion = true; //오늘 하루 안에 연합이 만들어 졌다는 뜻.
                        }
                    }
                }
            }
            if(!isUnion){
                System.out.println(day);
                break;
            }
            day++;
        }
    }
    static void bfs(int r, int c){
        Queue<People> q = new LinkedList<>();
        list = new ArrayList<>();
        q.add(new People(r, c));
        visited[r][c] = true;
        list.add(new Index(r,c));
        total = total + map[r][c];

        while(!q.isEmpty()){
            People v = q.remove();
            for(int i=0;i<4;i++){
                int nr = v.row + mv[i][0];
                int nc = v.col + mv[i][1];
                if(nr>=0 && nc >=0 && nr<N && nc<N && !visited[nr][nc]){
                    int diff = Math.abs(map[v.row][v.col] - map[nr][nc]);
                    if(diff <= R && diff >= L){
                        visited[nr][nc] = true;
                        q.add(new People(nr, nc));
                        flag++;
                        total = total + map[nr][nc];
                        list.add(new Index(nr,nc));
                    }
                }
            }
        }
    }
}
