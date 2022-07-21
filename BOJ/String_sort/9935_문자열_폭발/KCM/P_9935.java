import java.util.*;
import java.io.*;

//비교는 많이 해도됨, 많아봤자 36번임
public class P_9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String target = br.readLine();
        StringBuilder sb = new StringBuilder();
        int cnt=0;
        for(int i=0; i<input.length();i++){
            char temp = input.charAt(i);
            sb.append(temp);
            if(sb.length()>=target.length()){
                cnt=0;
                for(int j=0;j<target.length();j++){
                    char c1 = sb.charAt(sb.length()-target.length()+j);
                    char c2 = target.charAt(j);
                    if(c1 == c2){
                        cnt++;
                    }
                    else{
                        break;
                    }
                }
                if(cnt == target.length()){
                    sb.delete(sb.length()-target.length(), sb.length());
                }
            }
        }
        if(sb.length()==0){
            System.out.println("FRULA");
        }
        else{
            System.out.println(sb.toString());
        }
    }
}
