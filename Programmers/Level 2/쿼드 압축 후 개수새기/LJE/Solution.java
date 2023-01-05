
class Solution {
    public static int one=0, zero=0;
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        int length = arr[0].length;

        Divide(arr, 0, 0, length);

        answer[0] = zero;
        answer[1] = one;
        return answer;
    }

    public static void Divide(int[][] arr, int x, int y, int n){
        if(n==1 || Check(arr, x, y, n)){
            if(arr[x][y]==0)
                zero++;
            else
                one++;
            return;
        }

        else{
            Divide(arr, x, y, n/2);
            Divide(arr, x+n/2, y, n/2);
            Divide(arr, x, y+n/2, n/2);
            Divide(arr, x+n/2, y+n/2, n/2);
        }

    }

    public static boolean Check(int[][] arr, int x, int y, int n){
        int color = arr[x][y];
        for (int i = x; i < x+n; i++) {
            for (int j = y; j < y+n; j++) {
                if(color!=arr[i][j])
                    return false;
            }
        }
        return true;
    }
}