import java.io.*;
import java.util.*;

class Solution {
    static int zero = 0;
    static int one = 0;
    
    static int[] answer = new int[2];
    static int init_size;
    
    public static int[] solution(int[][] arr) {
        init_size = arr.length;
        
        partition(0, 0, init_size, arr);
        
        
        answer[0] = zero;
        answer[1] = one;
        
        return answer;
    }
    
    
    public static void partition(int row, int col, int size, int [][] arr) {
        if(arrCheck(row, col, size, arr)) { //모두 같은 수일 경우에
            if(arr[row][col] == 0) {
                zero++;
            }
            else {
                one++;
            }
        }
        else {
            partition(row, col, size/2, arr);
            partition(row + size/2, col, size/2, arr);
            partition(row, col + size/2, size/2, arr);
            partition(row + size/2, col + size/2, size/2, arr);
        }
        return;
    }
    
    
    public static boolean arrCheck(int row, int col, int size, int [][] arr) {
        int value = arr[row][col];
        for(int i=row; i<row+size; i++) {
            for(int j=col; j<col+size; j++) {
                if(arr[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}
