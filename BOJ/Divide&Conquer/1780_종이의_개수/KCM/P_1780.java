import java.util.*;
import java.io.*;

public class P_1780 {
    static int N, one, zero, minus=0;
    static int [][] map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int [N][N];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N); //처음, 종이의 상태 파악!

        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);

    }

    //row와 col은 검사할 배열의 시작지점!! [0][0]과 같은 느낌?
    //area의 크기만 지정해주면 row와 col을 시작으로하여 나누어진 전체 배열을 탐색 가능!
    static void divide(int row, int col, int area){
        boolean issame;

        issame = check(row, col, area); //같은 색상인지 체크!

        if(issame){ //만약 현재 종이가 모두 같은 색상이라면!
            if(map[row][col]==1){
                one++;
            }
            else if(map[row][col]==0){
                zero++;
            }
            else{
                minus++;
            }

        }
        else{
            //그렇지 않다면? 9개의 영역으로 나누어 주어야 한다.
            //다음 area의 크기는 현재 area에서 3을 나눈 값일 거임!

            int new_area = area/3;

            //예를 들어 row+3 이렇게 하면 안됨! 배열의 크기가 3보다 작을 수도 있기 때문에!
            //단순하게 생각 금지.. 반드시 한 번 점검하기!

            divide(row, col, new_area); //왼쪽 위
            divide(row+new_area, col, new_area); //왼쪽 중앙
            divide(row+2*new_area, col, new_area); //왼쪽 아래

            divide(row, col+new_area, new_area); //가운데 위
            divide(row+new_area, col+new_area, new_area); //가운데
            divide(row+2*new_area, col+new_area, new_area); //가운데 아래

            divide(row, col+2*new_area, new_area); //오른쪽 위
            divide(row+new_area, col+2*new_area, new_area); //오른쪽 중앙
            divide(row+2*new_area, col+2*new_area, new_area); //오른쪽 아래

            //area가 1이 되면 무조건 같은 색상이므로, 종료조건을 생각할 필요는 없다!
        }


    }

    static boolean check(int row, int col, int area){
        int cur_col = map[row][col]; //기준이 될 색상 지정

        for(int i=row; i<row+area; i++){
            for(int j=col; j<col+area; j++){
                if(map[row][col] != map[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
