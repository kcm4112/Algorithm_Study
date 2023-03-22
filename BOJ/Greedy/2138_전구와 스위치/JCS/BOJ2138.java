package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ2138 {

    static int [] want ;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int arr1_cnt =0;
    static int arr2_cnt =0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());

        int [][] origin = new int[2][N];
        want = new int [N];
        String temp = bufferedReader.readLine();
        for(int i=0;i<temp.length();i++)
        {
            origin[0][i] = Character.getNumericValue(temp.charAt(i));
            origin[1][i] = Character.getNumericValue(temp.charAt(i));
        }
        temp = bufferedReader.readLine();
        for(int i=0;i<temp.length();i++)
        {
            want[i] = Character.getNumericValue(temp.charAt(i));
        }

        arr2_cnt+=1;
        solution(origin[0],switch_change(origin[1],0),0);

    }

    private static void solution(int [] arr1, int [] arr2,int idx)
    {

        if(idx == N-1)
        {
            int cnt1=0;
            int cnt2=0;
            for(int i=0;i<N;i++)
            {
               if(arr1[i]==want[i])cnt1++;
               if(arr2[i]==want[i])cnt2++;
            }
            if(cnt1 != N && cnt2 !=N)System.out.println(-1);
            else if(cnt1 ==N && cnt2 ==N)
            {
                System.out.println((arr1_cnt<=arr2_cnt) ? arr1_cnt :arr2_cnt);
            }
            else {
                System.out.println((cnt1==N) ? arr1_cnt:arr2_cnt);
            }
            System.exit(0);
        }
        //arr1에서 안켰을 때 검사
        if(!check(arr1,idx+1))
        {
            arr1 = switch_change(arr1,idx+1);
            arr1_cnt+=1;
        }
        //arr2에서 안켰을 때 검사
        if(!check(arr2,idx+1))
        {
            arr2 = switch_change(arr2,idx+1);
            arr2_cnt+=1;
        }

        solution(arr1,arr2,idx+1);

    }

    private static boolean check(int [] arr, int idx)
    {
        if(arr[idx -1] == want[idx - 1]) return true;
        return false;
    }
    private static int [] switch_change(int [] arr,int idx)
    {
        int mask = 1;
        if(idx ==0)
        {
            arr[idx] = arr[idx]^mask;
            arr[idx+1]= arr[idx+1]^mask;
            return arr;
        }
        else if(idx == N-1)
        {
            arr[idx] = arr[idx]^mask;
            arr[idx-1] = arr[idx-1]^mask;
            return arr;
        }
        arr[idx-1]= arr[idx-1]^mask;
        arr[idx]= arr[idx]^mask;
        arr[idx+1] = arr[idx+1]^mask;
        return arr;
    }


}
