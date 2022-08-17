package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int index;
    int distance;

    public Node(int i, int d){
        this.distance = d;
        this.index = i;
    }

    @Override
    public int compareTo(Node o) {
        if(this.distance < o.distance)
            return -1;
        return 1;
    }
}

public class Q1446 {
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    public static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        distance = new int[D + 1];
        for(int i=0;i<=D;++i)
            graph.add(new ArrayList<>());

        for(int i=0;i<N;++i){
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if((end-start)<=cost || end>D)
                continue;
            graph.get(start).add(new Node(end, cost));
        }

        findShortestPath(0,D);
        System.out.println(distance[D]);
    }

    public static void findShortestPath(int start, int D) {
        distance[start]=0;

        for(int i=1;i<=D;++i){
            distance[i] = distance[i-1]+1;
            for(int j=0;j<graph.size();++j) {
                for(int k=0;k<graph.get(j).size();++k){
                    if(i==graph.get(j).get(k).index){
                        distance[i] = Math.min(distance[i], graph.get(j).get(k).distance + distance[j] );
                    }
                }
            }
        }
    }
}
