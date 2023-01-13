import java.io.*;
import java.util.*;

public class P_1495 {
    static int N, S, M;
    static int [] volume;
    static int [] example;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        volume = new int[M+1];
        String [] s = br.readLine().split(" ");
        example = new int[s.length];

        for(int i=0;i<s.length;i++) {
            example[i] = Integer.parseInt(s[i]);
        }
        for(int i=0;i<=M;i++) {
            volume[i] = -1; //모든 가능한 볼륨은 0으로 초기화해준다.
        }
        volume[S] = 0; //0번째 곡의 볼륨은 S이다.
        for(int i=1;i<=N;i++) {
            //가능한 볼륨의 개수가 엄청 많으므로 (정확한 개수는 모름)
            //리스트를 선언해준다.
            List<Integer> list = new ArrayList<>();
            for(int k=0;k<volume.length;k++) {
                //volume[k] = i-1 일 때 k 값이, i-1번째 곡의 가능한 볼륨 값이다.
                //이 때 k값은 여러개일 수 있으므로 리스트를 하나 선언하고, 매번 초기화하여 그 안에 값을 넣어준다.
                if(volume[k] == i-1) {
                    if(k + example[i-1] <= M && k + example[i-1] >= 0) {
                        list.add(k+example[i-1]);
                    }
                    if(k - example[i-1] <= M && k-example[i-1] >= 0) {
                        list.add(k-example[i-1]);
                    }
                }
//                for(int j=0;j<list.size();j++) {
//                    volume[list.get(j)] = i;
//                }
//                System.out.println(list);
            }
            //예제 1에서 마지막 곡의 볼륨을 구할 때, 아직 최종 후보를 다 고르기 전에 volume[10] = 1이었는데 volume[10]=3으로바뀜
            //만약 volume[10] = 2이었다면, 2에 대해 탐색을 다 하기도 전에 바뀌게 되어서 10에서 example[2]를 고려한 값을 구할 수 없다.
            for(int j=0;j<list.size();j++) {
                volume[list.get(j)] = i;
            }

        }
        int max = -1;
        for(int i=0;i<volume.length;i++) {
            if(volume[i] == N) {
                max = Math.max(max, i);
            }
        }
        System.out.println(max);
    }
}
