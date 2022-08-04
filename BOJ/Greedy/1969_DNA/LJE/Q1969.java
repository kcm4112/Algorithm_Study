package Greedy;

import java.util.*;

public class Q1969 {
    public static String findValue(int index, String[] list){

        List<String> temp_list=new ArrayList<>();
        String check_str = "ACGT";
        for(int i=0;i< list.length;++i)
        {
            temp_list.add(Character.toString(list[i].charAt(index)));
        }

        int max=0;
        String result="";
        for(int i=0;i<check_str.length();++i){
            int count = Collections.frequency(temp_list, Character.toString(check_str.charAt(i)));
            if(count>max)
            {
                result = Character.toString(check_str.charAt(i));
                max = count;
            }
        }
        
        return result;
    }

    public static int getCount(String origin, String answer){
        int result=0;
        for(int i=0;i<origin.length();++i){
            if(origin.charAt(i)!=answer.charAt(i))
                result++;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        String[] list = new String[N];
        for(int i=0;i< list.length;++i){
            list[i]=sc.next();
        }

        String answer = "";
        for(int i=0;i<M;i++) {
            answer += findValue(i,list);
        }

        int count=0;
        for(int i=0;i<N;++i){
            count += getCount(list[i],answer);
        }

        System.out.println(answer);
        System.out.println(count);
    }
    
}
