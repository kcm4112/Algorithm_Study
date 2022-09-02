import java.util.*;
import java.io.*;

public class P_1802 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = false;
        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            String s = br.readLine();
            flag = true;
            if(s.length()==1){
                sb.append("YES\n"); //한 번 접었으면 무조건 YES
                continue;
            }
            for(int j=0;j<s.length()/2;j++){
                if(s.charAt(j) == s.charAt(s.length()-1-j)){
                    flag = false;
                    sb.append("NO\n");
                    break;
                }
            }
            if(flag){
                sb.append("YES\n");
            }
        }
        System.out.println(sb);
    }
}
