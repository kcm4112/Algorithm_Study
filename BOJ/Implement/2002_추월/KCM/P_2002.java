import java.util.*;
import java.io.*;
//들어간 순서와 나온 순서를 비교해야 한다.
//나온차를 하나씩 탐색하며, 해당 차량 뒤의 차 중, 들어온 순서가 자신보다 빠른 놈이 있다면 해당차량은 무조건 추월한것!
public class P_2002 {
    static int N, total = 0;
    public static void main(String[] args) throws IOException{
        Map<String, Integer> inmap = new LinkedHashMap<>();
        Map<String, Integer> outmap = new LinkedHashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int [] outlist = new int [N]; //나온 차들의 순서대로 들어간 순서를 저장할 리스트
        //들어간 차의 (번호판,순서) 맵에 저장.
        for(int i=1;i<=N;i++){
            inmap.put(br.readLine(), i);
        }
        for(int i=0;i<N;i++){
            outlist[i] = inmap.get(br.readLine());
        }
        //나온차를 하나씩 탐색하며, 해당 차량 뒤의 차 중, 들어온 순서가 자신보다 빠른 놈이 있다면 해당차량은 무조건 추월한것!
        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                if(outlist[i] > outlist[j]){
                    total++;
                    break;
                }
            }
        }
        System.out.println(total);
    }
}
