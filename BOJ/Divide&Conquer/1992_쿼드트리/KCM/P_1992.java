import java.util.*;
import java.io.*;

public class P_1992 {
    static int N;
    static int [][] map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            String s = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }

        partition(0, 0, N);

        System.out.println(sb);
    }
    static void partition(int row, int col, int area){
        boolean flag;

        flag = check(row, col, area);
        if(flag){ //만약 영역 전체가 같은 숫자이면!
            if(map[row][col] == 1){
                sb.append("1");
            }
            else{
                sb.append("0");
            }
        }

        else{
            sb.append("(");
            int size = area/2;
            partition(row, col, size); //왼쪽 위
            partition(row, col+size, size); //오른쪽 위
            partition(row+size, col, size); //왼쪽 아래
            partition(row+size, col+size, size); //오른쪽 아래
            sb.append(")");
        }
    }

    static boolean check(int row, int col, int area){ //영역 전체가 같은 숫자인지 체크하는 메소드
        int cur_num = map[row][col];
        for(int i=row;i<row+area;i++){
            for(int j=col; j<col+area; j++){
                if(cur_num != map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
