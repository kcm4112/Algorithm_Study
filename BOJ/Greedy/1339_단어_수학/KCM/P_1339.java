import java.util.*;
import java.io.*;

//존나쉬웠다 의외로.
public class P_1339 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String [] s = new String[N];
        Map <Character, Integer> map = new HashMap<>();

        for(int i=0;i<N;i++){
            s[i] = br.readLine();
            for(int j=0; j<s[i].length() ; j++){
                if(!map.containsKey(s[i].charAt(j))){
                    map.put(s[i].charAt(j), (int)Math.pow(10,(s[i].length() - j - 1)));
                }
                else{
                    map.put(s[i].charAt(j), map.get(s[i].charAt(j)) + (int)Math.pow(10,(s[i].length() - j - 1)));
                }
            }
        }

        ArrayList <Integer> list = new ArrayList<>();
        for(Map.Entry <Character, Integer> m : map.entrySet()){
            list.add(m.getValue());
        }
        Collections.sort(list, Collections.reverseOrder());

        int num = 9;
        int total = 0;
        for(int i = 0; i<list.size();i++){
            total = total + num*list.get(i);
            num--;
        }
        System.out.println(total);
    }
}
