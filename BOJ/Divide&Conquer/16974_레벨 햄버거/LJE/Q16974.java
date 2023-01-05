package DivideConquer;

import java.util.Scanner;

public class Q16974 {

    public static long[] burger = new long[51];
    public static long[] pattie = new long[51];
    public static long result=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Long X = sc.nextLong();

        burger[0] = 1;
        pattie[0] = 1;
        for (int i = 1; i <= 50; i++) {
            burger[i] = 2*burger[i-1]+3;
            pattie[i] = 2*pattie[i-1]+1;
        }

        eat(N, X);
    }

    public static void eat(int n, long x){
        if (x == 0) {
            System.out.println(result);
            return;
        }
        else if (n == 0) {
            result++;
            System.out.println(result);
            return;
        }

        x--;
        if(x==burger[n-1]){
            result += pattie[n-1];
            x -= burger[n-1];
            eat(n-1, x);
        }
        else if(x>burger[n-1]){
            result+=pattie[n-1]+1;
            x -= (burger[n-1]+1);
            eat(n-1 , x);
        }
        else{
            eat(n-1, x);
        }
    }



}
