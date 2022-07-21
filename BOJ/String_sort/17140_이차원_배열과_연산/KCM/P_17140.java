import java.util.*;
import java.io.*;

public class P_17140 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        //배열의 인덱스는 1부터 시작하므로 101크기로 선언
        int [][] A = new int [101][101];
        int R=3;
        int C=3;
        int time = 0;

        //입력값 대입!
        for(int i=1;i<=3;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++){
                A[i][j] = Integer.parseInt(s.nextToken());
            }
        }
        while(A[r][c]!=k && time <=100){
            //R연산하는 부분
            if(R>=C){
                time++;
                //Hashmap에 각 숫자들이 몇 번 나왔는지 저장한다.
                for(int i=1;i<=R;i++){
                    Map<Integer, Integer> map = new LinkedHashMap<>();
                    for(int j=1;j<=C;j++){
                        if(!map.containsKey(A[i][j])){
                            map.put(A[i][j], 1);
                        }
                        else{
                            map.put(A[i][j], map.get(A[i][j]) + 1);
                        }
                    }
                    //map의 Value의 오름차순으로 정렬하는 list를 만든다.
                    List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
                    list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            if(o1.getValue() == o2.getValue()){
                                return o1.getKey() - o2.getKey();
                            }
                            else{
                                return o1.getValue() - o2.getValue();
                            }
                        }
                    });

                    int idx = 1;
                    for(int a=0;a<list.size();a++){
                        int key = list.get(a).getKey();
                        int value = list.get(a).getValue();
                        if(key==0){
                            continue;
                        }
//                        System.out.printf("%d %d ", key, value);
                        A[i][idx++] = key;
                        A[i][idx++] = value;
//                        System.out.println();
                    }
                    C = Math.max(C, idx-1);
                    while(idx<=100){
                        A[i][idx++]=0;
                    }
                }
            }
            //C연산 하는 부분.
            else{
                time++;
                //Hashmap에 각 숫자들이 몇 번 나왔는지 저장한다.
                for(int i=1;i<=C;i++){
                    Map<Integer, Integer> map = new LinkedHashMap<>();
                    for(int j=1;j<=R;j++){
                        if(!map.containsKey(A[j][i])){
                            map.put(A[j][i], 1);
                        }
                        else{
                            map.put(A[j][i], map.get(A[j][i]) + 1);
                        }
                    }
                    //map의 Value의 오름차순으로 정렬하는 list를 만든다.
                    List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
                    list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                            if(o1.getValue() == o2.getValue()){
                                return o1.getKey() - o2.getKey();
                            }
                            else{
                                return o1.getValue() - o2.getValue();
                            }

                        }
                    });

                    int idx = 1;
                    for(int a=0;a<list.size();a++){
                        int key = list.get(a).getKey();
                        int value = list.get(a).getValue();
                        if(key==0){
                            continue;
                        }
//                        System.out.printf("%d %d ", key, value);
                        A[idx++][i] = key;
                        A[idx++][i] = value;
//                        System.out.println();
                    }
                    R = Math.max(R, idx-1);
                    while(idx<=100){
                        A[idx++][i]=0;
                    }
                }
            }
        }
        for(int [] a : A){
            for(int b : a){
                System.out.printf("%d ", b);
            }
            System.out.println();
        }
        System.out.println(time>100 ? -1 : time);
    }

}
