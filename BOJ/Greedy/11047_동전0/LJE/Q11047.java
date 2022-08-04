package Greedy;
import java.io.*;
import java.util.Scanner;

public class Q11047 {
    public static int getResult(int value, int[] coin)
    {
        int total_coin = 0;
        for(int i=0;i<coin.length;i++)
        {
            if(value==0)
                break;
            int coin_num = value/coin[i];
            total_coin+=coin_num;
            value-=coin_num*coin[i];
        }
        return total_coin;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] coin = new int[N];
        for(int i=coin.length-1;i>=0;i--)
            coin[i]=sc.nextInt();

        System.out.println(getResult(K, coin));

    }
}
