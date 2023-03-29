package BOJ;

import java.util.Scanner;

public class BOJ2011 {
    static String password;
    static Scanner scanner = new Scanner(System.in);
    static double [] dp;

    public static void main(String[] args) {

        password = scanner.nextLine();

        dp = new double[password.length()];

        int idx= 0;
        solution(idx);
    }
    private static void solution(int idx) {

        if(idx == password.length())
        {
            System.out.println((int)(dp[idx-1]));
            System.exit(0);
        }
        if(idx ==0)
        {
            if(Character.getNumericValue(password.charAt(idx))==0)
            {
                System.out.println(0);
                System.exit(0);
            }
            dp[0]= 1d;
            solution(idx+1);
        }
        if(idx==1)
        {
            if(Integer.parseInt(password.substring(idx-1,idx+1))>26)
            {
                if(Character.getNumericValue(password.charAt(idx))==0)
                {
                    System.out.println(0);
                    System.exit(0);
                }else dp[idx]=dp[idx-1];
            }
            else
            {
                if(Character.getNumericValue(password.charAt(idx))==0)
                {
                    dp[idx]=dp[idx-1];
                }
                else dp[idx]=dp[idx-1]*2;
            }
        }
        else
        {
            if(Character.getNumericValue(password.charAt(idx))==0)
            {
                if(Character.getNumericValue(password.charAt(idx-1))==1 || Character.getNumericValue(password.charAt(idx-1))==2)
                {
                    dp[idx]=dp[idx-2]%1000000;
                }
                else {
                    System.out.println(0);
                    System.exit(0);
                }
            }
            else
            {
                int sum = Integer.parseInt(password.substring(idx-1,idx+1));
                if(sum <27 && sum >9)
                {
                    dp[idx]=(dp[idx-1]+dp[idx-2])%1000000;
                }
                else
                {
                    dp[idx] = dp[idx-1]%1000000;
                }
            }
        }
        solution(idx+1);
    }
}
