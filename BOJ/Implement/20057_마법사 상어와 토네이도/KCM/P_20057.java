import java.util.*;
import java.io.*;

public class P_20057 {
    static int N=0;
    static int [][] map;
    static int total = 0;
    static boolean [][] checked;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int [N][N];
        checked = new boolean[N][N];
        //map init.
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //반시계방향은 좌(Left), 하(Under), 우(Right), 상(Over)
        char state = 'L';
        int row = N/2;
        int col = N/2;
        checked[N/2][N/2] = true; //출발지점은 방문처리함.
        while(true){
            if(row==0 && col==0){
                break;
            }
            switch(state){
                case 'L':
                    int sand = map[row][col-1];
                    int awaysand = 0;
                    if(row<N && row>=0 && (col-3)<N && (col-3) >=0 ){
                        map[row][col-3] += (int)(sand*0.05); //5%부분 모래양
                        awaysand += (int)(sand*0.05);
                    }
                    else{
                        total += (int)(sand * 0.05);
                        awaysand += (int)(sand*0.05);
                    }
                    if(row-1<N && row-1>=0 && (col-2)<N && (col-2) >=0 ){
                        map[row-1][col-2] += (int)(sand * 0.1); //위쪽 10%부분
                        awaysand += (int)(sand*0.1);
                    }
                    else{
                        total += (int)(sand * 0.1);
                        awaysand += (int)(sand*0.1);
                    }
                    if(row+1<N && row+1>=0 && (col-2)<N && (col-2) >=0 ){
                        map[row+1][col-2] += (int)(sand * 0.1);//아래쪽 10%부분
                        awaysand += (int)(sand*0.1);
                    }
                    else{
                        total += (int)(sand * 0.1);
                        awaysand += (int)(sand*0.1);
                    }
                    if(row-1<N && row-1>=0 && (col-1)<N && (col-1) >=0 ){
                        map[row-1][col-1] += (int)(sand * 0.07);//위쪽 7%부분
                        awaysand += (int)(sand*0.07);
                    }
                    else{
                        total += (int)(sand * 0.07);
                        awaysand += (int)(sand*0.07);
                    }
                    if(row+1<N && row+1>=0 && (col-1)<N && (col-1) >=0 ){
                        map[row+1][col-1] += (int)(sand * 0.07);//아래쪽 7%부분
                        awaysand += (int)(sand*0.07);
                    }
                    else{
                        total += (int)(sand * 0.07);
                        awaysand += (int)(sand*0.07);
                    }
                    if(row-1<N && row-1>=0 && col<N && col>=0 ){
                        map[row-1][col] += (int)(sand * 0.01);//위쪽 1%부분
                        awaysand += (int)(sand*0.01);
                    }
                    else{
                        total += (int)(sand * 0.01);
                        awaysand += (int)(sand*0.01);
                    }
                    if(row+1<N && row+1>=0 && col<N && col>=0 ){
                        map[row+1][col] += (int)(sand * 0.01);//아래쪽 1%부분
                        awaysand += (int)(sand*0.01);
                    }
                    else{
                        total += (int)(sand * 0.01);
                        awaysand += (int)(sand*0.01);
                    }
                    if(row-2<N && row-2>=0 && (col-1)<N && (col-1) >=0 ){
                        map[row-2][col-1] += (int)(sand * 0.02);//위쪽 2%부분
                        awaysand += (int)(sand*0.02);
                    }
                    else{
                        total += (int)(sand * 0.02);
                        awaysand += (int)(sand*0.02);
                    }
                    if(row+2<N && row+2>=0 && (col-1)<N && (col-1) >=0 ){
                        map[row+2][col-1] += (int)(sand * 0.02);//아래쪽 2%부분
                        awaysand += (int)(sand*0.02);
                    }
                    else{
                        total += (int)(sand * 0.02);
                        awaysand += (int)(sand*0.02);
                    }
                    if(row<N && row>=0 && (col-2)<N && (col-2) >=0 ){
                        map[row][col-2] += (sand-awaysand); //a 부분 모래양
                    }
                    else{
                        total += (sand-awaysand);
                    }
                    map[row][col-1] = 0;
                    col = col - 1; //토네이도 발생 지역 한 칸 이동
                    if(col>=0){
                        checked[row][col] = true;
                    }
                    if(row+1<N && row+1>=0 && col<N && col>=0){
                        if(checked[row+1][col]){ //다음 반시계방향으로 가려면 아래쪽인데, 만약 막혀있으면 그대로 왼쪽으로 한 칸 더 이동
                            state = 'L';
                        }
                        else{
                            state = 'U';
                        }
                    }
                    break;
                case 'U':
                    int sand2 = map[row+1][col];
                    int awaysand2 = 0;
                    if(row+3<N && row+3>=0 && col<N && col >=0 ){
                        map[row+3][col] += (int)(sand2*0.05); //5%부분 모래양
                        awaysand2 += (int)(sand2*0.05);
                    }
                    else{
                        total += (int)(sand2*0.05);
                        awaysand2 += (int)(sand2*0.05);
                    }
                    if(row+2<N && row+2>=0 && (col-1)<N && (col-1) >=0 ){
                        map[row+2][col-1] += (int)(sand2 * 0.1); //왼쪽 10%부분
                        awaysand2 += (int)(sand2*0.1);
                    }
                    else{
                        total += (int)(sand2 * 0.1);
                        awaysand2 += (int)(sand2*0.1);
                    }
                    if(row+2<N && row+2>=0 && (col+1)<N && (col+1) >=0 ){
                        map[row+2][col+1] += (int)(sand2 * 0.1);//오른쪽 10%부분
                        awaysand2 += (int)(sand2*0.1);
                    }
                    else{
                        total += (int)(sand2 * 0.1);
                        awaysand2 += (int)(sand2*0.1);
                    }
                    if(row+1<N && row+1>=0 && (col-1)<N && (col-1) >=0 ){
                        map[row+1][col-1] += (int)(sand2 * 0.07);//왼쪽 7%부분
                        awaysand2 += (int)(sand2*0.07);
                    }
                    else{
                        total += (int)(sand2 * 0.07);
                        awaysand2 += (int)(sand2*0.07);
                    }
                    if(row+1<N && row+1>=0 && (col+1)<N && (col+1) >=0 ){
                        map[row+1][col+1] += (int)(sand2 * 0.07);//오른쪽 7%부분
                        awaysand2 += (int)(sand2*0.07);
                    }
                    else{
                        total += (int)(sand2 * 0.07);
                        awaysand2 += (int)(sand2*0.07);
                    }
                    if(row<N && row>=0 && col-1<N && col-1>=0 ){
                        map[row][col-1] += (int)(sand2 * 0.01);//왼쪽 1%부분
                        awaysand2 += (int)(sand2*0.01);
                    }
                    else{
                        total += (int)(sand2 * 0.01);
                        awaysand2 += (int)(sand2*0.01);
                    }
                    if(row<N && row>=0 && col+1<N && col+1>=0 ){
                        map[row][col+1] += (int)(sand2 * 0.01);//오른쪽 1%부분
                        awaysand2 += (int)(sand2*0.01);
                    }
                    else{
                        total += (int)(sand2 * 0.01);
                        awaysand2 += (int)(sand2*0.01);
                    }
                    if(row+1<N && row+1>=0 && (col-2)<N && (col-2) >=0 ){
                        map[row+1][col-2] += (int)(sand2 * 0.02);//왼쪽 2%부분
                        awaysand2 += (int)(sand2*0.02);
                    }
                    else{
                        total += (int)(sand2 * 0.02);
                        awaysand2 += (int)(sand2*0.02);
                    }
                    if(row+1<N && row+1>=0 && (col+2)<N && (col+2) >=0 ){
                        map[row+1][col+2] += (int)(sand2 * 0.02);//오른쪽 2%부분
                        awaysand2 += (int)(sand2*0.02);
                    }
                    else{
                        total += (int)(sand2 * 0.02);
                        awaysand2 += (int)(sand2*0.02);
                    }
                    if(row+2<N && row+2>=0 && col<N && col >=0 ){
                        map[row+2][col] += (sand2-awaysand2); //a 부분 모래양
                    }
                    else{
                        total += (sand2-awaysand2);
                    }
                    map[row+1][col] = 0;
                    row = row + 1; //토네이도 발생 지역 한 칸 이동
                    checked[row][col] = true;
                    if(row<N && row>=0 && col+1<N && col+1 >=0){
                        if(checked[row][col+1]){ //다음 반시계방향으로 가려면 아래쪽인데, 만약 막혀있으면 그대로 왼쪽으로 한 칸 더 이동
                            state = 'U';
                        }
                        else{
                            state = 'R';
                        }
                    }

                    break;
                case 'R':
                    int sand3 = map[row][col+1];
                    int awaysand3 = 0; //이까지 했음
                    if(row<N && row>=0 && (col+3)<N && (col+3) >=0 ){
                        map[row][col+3] += (int)(sand3*0.05); //5%부분 모래양
                        awaysand3 += (int)(sand3*0.05);
                    }
                    else{
                        total += (int)(sand3*0.05);
                        awaysand3 += (int)(sand3*0.05);
                    }
                    if(row-1<N && row-1>=0 && (col+2)<N && (col+2) >=0 ){
                        map[row-1][col+2] += (int)(sand3 * 0.1); //위쪽 10%부분
                        awaysand3 += (int)(sand3*0.1);
                    }
                    else{
                        total += (int)(sand3 * 0.1);
                        awaysand3 += (int)(sand3*0.1);
                    }
                    if(row+1<N && row+1>=0 && (col+2)<N && (col+2) >=0 ){
                        map[row+1][col+2] += (int)(sand3 * 0.1);//아래쪽 10%부분
                        awaysand3 += (int)(sand3*0.1);
                    }
                    else{
                        total += (int)(sand3 * 0.1);
                        awaysand3 += (int)(sand3*0.1);
                    }
                    if(row-1<N && row-1>=0 && (col+1)<N && (col+1) >=0 ){
                        map[row-1][col+1] += (int)(sand3 * 0.07);//위쪽 7%부분
                        awaysand3 += (int)(sand3*0.07);
                    }
                    else{
                        total += (int)(sand3 * 0.07);
                        awaysand3 += (int)(sand3*0.07);
                    }
                    if(row+1<N && row+1>=0 && (col+1)<N && (col+1)>=0 ){
                        map[row+1][col+1] += (int)(sand3 * 0.07);//아래쪽 7%부분
                        awaysand3 += (int)(sand3*0.07);
                    }
                    else{
                        total += (int)(sand3 * 0.07);
                        awaysand3 += (int)(sand3*0.07);
                    }
                    if(row-1<N && row-1>=0 && col<N && col>=0 ){
                        map[row-1][col] += (int)(sand3 * 0.01);//위쪽 1%부분
                        awaysand3 += (int)(sand3*0.01);
                    }
                    else{
                        total += (int)(sand3 * 0.01);
                        awaysand3 += (int)(sand3*0.01);
                    }
                    if(row+1<N && row+1>=0 && col<N && col>=0 ){
                        map[row+1][col] += (int)(sand3 * 0.01);//아래쪽 1%부분
                        awaysand3 += (int)(sand3*0.01);
                    }
                    else{
                        total += (int)(sand3 * 0.01);
                        awaysand3 += (int)(sand3*0.01);
                    }
                    if(row-2<N && row-2>=0 && (col+1)<N && (col+1) >=0 ){
                        map[row-2][col+1] += (int)(sand3 * 0.02);//위쪽 2%부분
                        awaysand3 += (int)(sand3*0.02);
                    }
                    else{
                        total += (int)(sand3 * 0.02);
                        awaysand3 += (int)(sand3*0.02);
                    }
                    if(row+2<N && row+2>=0 && (col+1)<N && (col+1) >=0 ){
                        map[row+2][col+1] += (int)(sand3 * 0.02);//아래쪽 2%부분
                        awaysand3 += (int)(sand3*0.02);
                    }
                    else{
                        total += (int)(sand3 * 0.02);
                        awaysand3 += (int)(sand3*0.02);
                    }
                    if(row<N && row>=0 && col+2<N && col+2 >=0 ){
                        map[row][col+2] += (sand3 - awaysand3); //a 부분 모래양
                    }
                    else{
                        total += (sand3 - awaysand3);
                    }
                    map[row][col+1] = 0;
                    col = col + 1; //토네이도 발생 지역 한 칸 이동
                    checked[row][col] = true;
                    if(row-1<N && row-1>=0 && col<N && col >=0){
                        if(checked[row-1][col]){ //다음 반시계방향으로 가려면 아래쪽인데, 만약 막혀있으면 그대로 왼쪽으로 한 칸 더 이동
                            state = 'R';
                        }
                        else{
                            state = 'O';
                        }
                    }
                    break;
                case 'O':
                    int sand4 = map[row-1][col];
                    int awaysand4 = 0;
                    if(row-3<N && row-3>=0 && col<N && col >=0 ){
                        map[row-3][col] += (int)(sand4*0.05); //5%부분 모래양
                        awaysand4 += (int)(sand4*0.05);
                    }
                    else{
                        total += (int)(sand4*0.05);
                        awaysand4 += (int)(sand4*0.05);
                    }
                    if(row-2<N && row-2>=0 && (col-1)<N && (col-1) >=0 ){
                        map[row-2][col-1] += (int)(sand4 * 0.1); //왼쪽 10%부분
                        awaysand4 += (int)(sand4*0.1);
                    }
                    else{
                        total += (int)(sand4 * 0.1);
                        awaysand4 += (int)(sand4*0.1);
                    }
                    if(row-2<N && row-2>=0 && (col+1)<N && (col+1) >=0 ){
                        map[row-2][col+1] += (int)(sand4 * 0.1);//오른쪽 10%부분
                        awaysand4 += (int)(sand4*0.1);
                    }
                    else{
                        total += (int)(sand4 * 0.1);
                        awaysand4 += (int)(sand4*0.1);
                    }
                    if(row-1<N && row-1>=0 && (col-1)<N && (col-1) >=0 ){
                        map[row-1][col-1] += (int)(sand4 * 0.07);//왼쪽 7%부분
                        awaysand4 += (int)(sand4*0.07);
                    }
                    else{
                        total += (int)(sand4 * 0.07);
                        awaysand4 += (int)(sand4*0.07);
                    }
                    if(row-1<N && row-1>=0 && (col+1)<N && (col+1) >=0 ){
                        map[row-1][col+1] += (int)(sand4 * 0.07);//오른쪽 7%부분
                        awaysand4 += (int)(sand4*0.07);
                    }
                    else{
                        total += (int)(sand4 * 0.07);
                        awaysand4 += (int)(sand4*0.07);
                    }
                    if(row<N && row>=0 && col-1<N && col-1>=0 ){
                        map[row][col-1] += (int)(sand4 * 0.01);//왼쪽 1%부분
                        awaysand4 += (int)(sand4*0.01);
                    }
                    else{
                        total += (int)(sand4 * 0.01);
                        awaysand4 += (int)(sand4*0.01);
                    }
                    if(row<N && row>=0 && col+1<N && col+1>=0 ){
                        map[row][col+1] += (int)(sand4 * 0.01);//오른쪽 1%부분
                        awaysand4 += (int)(sand4*0.01);
                    }
                    else{
                        total += (int)(sand4 * 0.01);
                        awaysand4 += (int)(sand4*0.01);
                    }
                    if(row-1<N && row-1>=0 && (col-2)<N && (col-2) >=0 ){
                        map[row-1][col-2] += (int)(sand4 * 0.02);//왼쪽 2%부분
                        awaysand4 += (int)(sand4*0.02);
                    }
                    else{
                        total += (int)(sand4 * 0.02);
                        awaysand4 += (int)(sand4*0.02);
                    }
                    if(row-1<N && row-1>=0 && (col+2)<N && (col+2) >=0 ){
                        map[row-1][col+2] += (int)(sand4 * 0.02);//오른쪽 2%부분
                        awaysand4 += (int)(sand4*0.02);
                    }
                    else{
                        total += (int)(sand4 * 0.02);
                        awaysand4 += (int)(sand4*0.02);
                    }
                    if(row-2<N && row-2>=0 && col<N && col >=0 ){
                        map[row-2][col] += (sand4 - awaysand4); //a 부분 모래양
                    }
                    else{
                        total += (sand4 - awaysand4);
                    }
                    map[row-1][col] = 0;
                    row = row - 1; //토네이도 발생 지역 한 칸 이동
                    if(row>=0){
                        checked[row][col] = true;
                    }
                    if(row<N && row>=0 && col-1<N && col-1 >=0){
                        if(checked[row][col-1]){ //다음 반시계방향으로 가려면 아래쪽인데, 만약 막혀있으면 그대로 왼쪽으로 한 칸 더 이동
                            state = 'O';
                        }
                        else{
                            state = 'L';
                        }
                    }
                    break;
            }
        }
        System.out.println(total);
    }
}
