import java.util.*;
import java.io.*;

public class P_2630 {
    static int N;
    static int white = 0;
    static int blue = 0;
    static int [][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //처음 NxN 색종이에 대해 체크!
        partitionPaper(0, 0, N);

        System.out.println(white);
        System.out.println(blue);
    }

    public static void partitionPaper(int row, int col, int size) {
        if (checkPaper(row, col, size)) {
            //만약 모두 같은 색일 경우
            if(map[row][col] == 0) {
                white++;
            }
            else {
                blue++;
            }
        }
        else {
            partitionPaper(row, col, size/2);
            partitionPaper(row+size/2, col, size/2);
            partitionPaper(row, col+size/2, size/2);
            partitionPaper(row+size/2, col+size/2, size/2);
        }
    }
    public static boolean checkPaper(int row, int col, int size) {
        int color = map[row][col];
        for(int i=row; i<row+size;i++) {
            for(int j=col; j<col+size; j++) {
                if(map[i][j] != color) { //하나라도 다른 색깔이 있으면 false
                    return false;
                }
            }
        }
        return true;
    }
}
