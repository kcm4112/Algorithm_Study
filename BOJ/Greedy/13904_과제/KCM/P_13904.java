import java.util.*;
import java.io.*;
class Task implements Comparable<Task>{
    int d = 0;
    int w = 0;
    Task(int date, int weight){
        d = date;
        w = weight;
    }
    @Override
    public int compareTo(Task task){
        if(task.w < w){
            return 1;
        }
        else if(task.w > w){
            return -1;
        }
        else if(task.w == w){
            if(task.d > d){
                return 1;
            }
            else if(task.d < d){
                return -1;
            }
        }
        return 0;
    }

}
public class P_13904 {
    static List<Task> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] arr = new int [1001];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            list.add(new Task(v1, v2));
        }

        Collections.sort(list, Collections.reverseOrder());

        for(int i=0;i<list.size();i++){
            Task t = list.get(i);
            int n1 = t.d;
            int n2 = t.w;
//            System.out.printf("%d %d\n", n1, n2);
            if(arr[n1]==0){
                arr[n1] = n2;
            }
            else{
                while(true){
                    n1--;
                    if(n1<=0){
                        break;
                    }
                    if(arr[n1] == 0){
                        arr[n1] = n2;
                        break;
                    }
                }
            }
        }
        int total = 0;
        for(int i =0;i<arr.length;i++){
//            System.out.println(arr[i]);
            total = total + arr[i];
        }
        System.out.println(total);




    }
}
