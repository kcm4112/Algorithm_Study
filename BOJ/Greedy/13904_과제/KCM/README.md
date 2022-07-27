## 🧩풀이
1. 주어진 정보를 'w' 를 기준으로 내림차순으로 정렬한다.

2. list에 저장된 내림차순 정렬된 정보를 for문 내에서 처음부터 탐색한다.

3. arr 배열은 arr[i] 에서 i가 날짜를 나타내고 arr[i]의 값이 그 날짜에 진행하는 과제의 weight를 나타낸다.

4. 만약 i 날에 아직 정해진 과제가 없어서 arr[i]값이 0이라면 그대로 arr[i]에 해당하는 weight를 넣어준다.

5. 만약 i날에 정해진 과제가 있다면 i-1번째 날을 확인하고, 그래도 과제가 있다면 i를 1씩 감소시키며 arr[i]가 0이 될 때까지 반복한다.

 

즉, 어차피 과제의 weight를 기준으로 오름차순 되어있기 때문에 date에 해당하는 arr배열에 그 weigth를 넣어주되, 만약 arr[i]의 값이 있다면(마감기한이 같은 과제가 이미 존재한다면) 분명히 그 arr[i]값보다는 현재 arr[i]값이 작을 것이므로, 마감기한과 가장 가까운 날에 그 과제를 배당해준다!!

---

## 🧩나의 코드
```java
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
```

---

## 🧩Review
그렇게 어렵지는 않았던 것 같다.

다만 생각해야 될 필수적인 것은
>1. 과제의 가치가 높은 것이 우선시 되어야 함. (그러나 마감기한에 딱 맞춰서 할 수록 좋다. 왜냐하면 그 전에 다른 과제를 하는게 더 이득이기 때문에!)
>2. 가치가 높지만 이미 그 마감기한에 가치가 더 높은 과제가 배정된 경우는 최대한 그날과 가까운 날에 과제가 없는 날을 골라서 배당해주어야함!

주의하면서 코딩하면 될 것 같다!!

조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,조금씩 성장하는 것 같기도?,,,,,
