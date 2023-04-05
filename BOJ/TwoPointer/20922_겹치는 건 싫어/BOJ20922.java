package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ20922 {

    static int N;
    static int K;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static int [] array;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bufferedReader.readLine());
        array = new int [N];
        for(int i =0;i<N;i++)
        {
            array[i] = Integer.parseInt(st.nextToken());
        }

        solution();

    }

    private static void solution() {
        Map<Integer,Integer> map = new HashMap<>();
        int max_len=0;
        int cur_len=0;
        int p1 = 0;
        int p2 = 1;
        map.put(array[p1],K-1);
        while (p2 != N)
        {
            if(map.containsKey(array[p2])) {
                if (map.get(array[p2]) != 0)
                {
                    map.put(array[p2], map.get(array[p2]) - 1);
                }
                else
                {
                    int dup_ele = array[p2];
                    int p_dup_ele = find(dup_ele,p1);
                    if(p1<=p_dup_ele) {
                        for(int i = p1 ; i<p_dup_ele;i++)
                        {
                            map.put(array[i],map.get(array[i])+1);
                        }
                        p1 = p_dup_ele+1;
                    }
                }
            }
            else
            {
                map.put(array[p2],K-1);
            }
            cur_len = p2 - p1 +1;
            if(cur_len>max_len) max_len = cur_len;
            p2++;
        }

        System.out.println(max_len);
        return ;
    }

    private static int find(int dupEle,int p1) {
        int i=p1;
        while (i<N)
        {
            if(array[i]==dupEle)break;
            i++;
        }
        return i;
    }

}
