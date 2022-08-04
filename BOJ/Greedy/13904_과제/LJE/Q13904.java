package Greedy;

import java.util.*;

class Node{
    int day;
    int weight;

    public Node(int day, int weight) {
        this.day = day;
        this.weight = weight;
    }
}

public class Q13904 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        ArrayList<Node> list = new ArrayList<>();
        int deadline = 0;
        for(int i=0;i<N;++i) {
            int d = sc.nextInt();
            int w = sc.nextInt();
            deadline = Math.max(deadline, d);
            list.add(new Node(d,w));
        }

        int result=0;
        while(deadline>0){
            int max = 0, index=-1;
            for(int i=0;i<list.size();++i) {
                if (deadline <= list.get(i).day){
                    if(max < list.get(i).weight){
                        max = list.get(i).weight;
                        index = i;
                    }
                }
            }
            result += max;
            if(index!=-1)
                list.remove(index);
            deadline--;
        }

        System.out.println(result);
    }
}
