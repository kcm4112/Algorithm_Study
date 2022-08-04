package Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1339_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String[] list = new String[N];
        for (int i = 0; i < list.length; ++i)
            list[i] = sc.next();

        Map<Character, Integer> alpha = new HashMap<>();
        for(int i=0;i< list.length;++i){
            int digit = list[i].length()-1;
            for(int j=0;j<list[i].length();++j){
                int count = (int)Math.pow(10, digit--);
                alpha.put(list[i].charAt(j), alpha.getOrDefault(list[i].charAt(j), 0)+count);
            }
        }

        ArrayList<Integer> keymap = new ArrayList<Integer>(alpha.values());
        keymap.sort((s1,s2)->s2.compareTo(s1));
        
        int answer = 0, num = 9;
        for(int temp:keymap){
            answer += num-- * temp;
        }

        System.out.println(answer);
    }
}
