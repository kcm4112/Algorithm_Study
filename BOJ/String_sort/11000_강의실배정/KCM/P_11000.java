import java.util.*;
import java.io.*;


class Time implements Comparable<Time>{
    int start = 0;
    int end = 0;
    Time(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public int compareTo(Time t) {
        if(start == t.start) { //오름차순으로 정렬
            return end - t.end;
        }
        else {
            return start - t.start;
        }
    }
}
public class P_11000 {
    static int N;
    static ArrayList<Time> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            list.add(new Time(v1, v2));
        }

        Collections.sort(list);

//        for(int i=0;i<list.size();i++) {
//            Time t = list.get(i);
//            System.out.printf("%d %d\n", t.start, t.end);
//        }
        Queue<Integer> q = new PriorityQueue<>();
        for(int i=0;i<list.size();i++) {
            Time t = list.get(i);
            if(!q.isEmpty() && q.peek() <= t.start) {
                q.poll();
            }
            q.add(t.end);
        }

        System.out.println(q.size());

    }
}
