package String_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];
        for(int i=0;i<N;++i){
            list[i] = Check(br.readLine());
        }

        for (int i : list)
            System.out.println(i);

    }

    public static boolean Palindrome(String str){
        for(int i=0;i<str.length();++i){
            if(str.charAt(i)!=str.charAt(str.length()-i-1)){
                return false;
            }
        }
        return true;
    }

    public static int Check(String str){
        int flag = 0;

        if(Palindrome(str))
            return 0;

        for(int i=0;i<str.length();++i){
            if(str.charAt(i)!=str.charAt(str.length()-i-1)){
                boolean A = Palindrome(makeString(str, i));
                boolean B = Palindrome(makeString(str, str.length()-i-1));
                if(A||B)
                    return 1;
                else
                    return 2;
            }
        }

        return 2;
    }

    public static String makeString(String str, int index){
        String result="";
        if(index==0)
            result = str.substring(1,str.length());
        else if(index==str.length()-1)
            result = str.substring(0, str.length()-1);
        else
            result = str.substring(0,index) + str.substring(index+1, str.length());
        return result;
    }
}
