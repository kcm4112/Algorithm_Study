import java.util.*;
import java.io.*;

//1. 각 자리별로 가장 많이 나온 알파벳을 채택.
//2. Haming Distance는 DNA의 개수 - 가장 많이 나온 알파벳의 빈도수를 구하면 되고, 이것을 M회 반복한다.

public class P_1969 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String [] arr = new String[N];
        for(int i=0;i<N;i++){
            arr[i] = br.readLine();
        }
        String s = "";
        char ch = ' ';
        int total = 0;
        int num_max = 0 ;
        //각 자리별로 가장 많이 나온 알파벳 구하기.
        for(int i=0;i<M;i++){
            Map <Character, Integer> map = new TreeMap<>();
            for(int j=0;j<N;j++){
                if(!map.containsKey(arr[j].charAt(i))){
                    map.put(arr[j].charAt(i), 1);
                }
                else{
                    map.put(arr[j].charAt(i), map.get(arr[j].charAt(i)) + 1);
                }
            }
            for(Map.Entry <Character, Integer> m : map.entrySet()){
                char key = m.getKey();
                int value = m.getValue();
//                System.out.printf("%c %d", key, value);
//                System.out.printf("%c %d\n", key, value);
                if(value > num_max){
                    num_max = value;
                    ch = key;
                }
                else if(value == num_max){
                    if(key < ch){
                        ch = key;
                    }
                }
            }
            s = s + ch + "";
            total = total + (N - num_max);
            num_max=0;
        }
        System.out.println(s);
        System.out.println(total);
    }
}
