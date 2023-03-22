package BOJ;

import javax.naming.PartialResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2580 {
    static int[][] board = new int[9][9];
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        for(int i=0;i<9;i++)
        {
            String line = bufferedReader.readLine().replaceAll(" ","");
            for(int j =0;j<9;j++)
            {
                board[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        solution(0,0);

    }
    private static void solution(int row, int col)
    {

        if(col ==9) {
            solution(row+1,0);
            return;
        }
        if(row==9)
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(board[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);

            System.exit(0);
        }

        if(board[row][col]==0)
        {
            for(int i=1;i<10;i++)
            {
                if(check(row,col,i))
                {
                    board[row][col] = i;
                    solution(row,col+1);
                }
            }
            board[row][col]=0;
            return;
        }

        solution(row,col+1);

    }
    private static boolean check(int row,int col,int value)
    {
        for(int i=0;i<=8;i++)
        {
            if(board[row][i]==value)return false;
        }
        for(int i=0;i<=8;i++)
        {
            if(board[i][col]==value)return false;
        }
        int box_row = (row/3)*3;
        int box_col = (col/3)*3;
        for(int i =box_row;i<box_row+3;i++)
        {
            for(int j=box_col;j<box_col+3;j++)
            {
                if(board[i][j]==value)return false;
            }
        }
        return true;
    }
}
