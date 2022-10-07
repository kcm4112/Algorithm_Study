package String_Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture implements Comparable<Lecture>{
    int start;
    int end;
    public Lecture(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture o) {
        if(this.start == o.start )
            return this.end - o.end;
        return this.start - o.start;
    }
}

public class Q11000 {
    public static ArrayList<Lecture> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;++i){
            String str = br.readLine();
            st = new StringTokenizer(str, " ");
            list.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(list.get(0).end);

        for(int i=1;i<N;++i){
            if(list.get(i).start>=pq.peek()){
                pq.poll();
            }
            pq.offer(list.get(i).end);
        }

        System.out.println(pq.size());

    }
}
