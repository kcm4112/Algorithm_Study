package BOJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BOJ2529 {
    static Scanner scanner = new Scanner(System.in);
    static boolean [] used = new boolean[10];
    static List<String> num = new ArrayList<>();
    static String condition = "";
    static int N;
    public static void main(String[] args) {
        N = scanner.nextInt();

        for(int i =0; i < N ;i++)
        {
            condition += scanner.next();
        }

        solution("",0);
        System.out.println(num.get(num.size()-1));
        System.out.println(num.get(0));
    }

    private static void solution(String numbers, int idx) {

        if(idx ==N+1)
        {
            num.add(numbers);
            return;
        }
        for(int i = 0 ; i< 10 ; i++)
        {
            if(used[i]==true)continue;
            if(idx==0 || check(idx,i, Character.getNumericValue(numbers.charAt(idx - 1))))
            {
                used[i]=true;
                solution(numbers+i,idx+1);
                used[i]=false;
            }
        }

    }

    private static boolean check(int idx,int cur_op,int pre_op) {

        switch (condition.charAt(idx-1))
        {
            case '<' :
                if(pre_op<cur_op) return true;
                break;
            case '>' :
                if(pre_op>cur_op)return true;
        }
        return false;
    }

}
