🧩 풀이
1. 괄호 쌍별로, index를 짝지어서 기억할 필요가 있다. (무조건!) 이를 위해 '(' 가 나온 index를 저장하는 List와 ')'가 나온 index를 저장하는 List를 생성한다 - 각각 left와 right라고 선언했음.

2. '(' 가 나오면 left에 그 괄호의 index를 add한다.

3. 2번 과정을 계속하다가 만약 ')'가 나오면 right에 add한 후 가장 최근에 넣은 left값과 방금 넣은 right의 값을 짝으로하여 Pair객체를 생성해준다 - '(' 는 가장 가까운 ')' 와 짝을 이루므로 left의 가장 최근 값과 현재 right에 넣은 값을 짝지어주면 된다.

4. 입력예시 1을 예로들어서 설명하면 우선, 괄호의 짝이 2개가 나오기 때문에 각각의 괄호쌍을 사용하느냐 안하느냐에 따라 식이 달라질 수 있다.

5. 따라서, dfs로 Combination을 구현하여 괄호쌍을 넣는 경우와 그렇지 않은 경우로 문자열을 구해주었다.

6. 괄호쌍을 저장한 List는 paren이고 이 List에서 하나씩 꺼내어 그 괄호쌍의 '(' = start와 ')' = end를 visit배열에서 true로 만들어준다(수식에 넣겠다는 의미!)

7. 수식을 만들 때는 StringBuilder를 사용하여 input으로 주어진 수식의 길이만큼 for문을 선언하고 앞에서부터 괄호가 아닌 문자가 나오면 그냥 쌓고, 괄호가 나오면 위에서 true로 바꿔놓은 부분만 쌓는다.

8. 만들어진 문자열들을 사전순으로 정렬한다. (그냥 오름차순으로 sorting하면됨!)

---

🧩 전체 코드
```java
import java.util.*;
import java.io.*;
//1. 괄호 쌍별로, index를 구하여 객체를 생성해서 그 index를 기억한다.
//2. 괄호 쌍을 저장해놓은 list를 생성후 그 list의 size가 전체 원소가 되는 집합의 부분집합(조힙을 구한다)
//3. 그 조합에 따라 괄호를 출력할지 안할지를 판별하여 string을 list에 저장한다.
//4. 출력하기 전에 list에 저장한 string을 한번 sort해준다.(사전순이란 말은 그냥 sorting하라는 말임)
//5. 주의할 점. (((1)))이렇게 있을 때, 괄호쌍이 빠지는 위치가 달라도 ((1))인 경우가 여러경우 나올 수 있다.
//6. set을 이용하여 중복을 제거한 string배열을 만들어야함!

//괄호의 짝을 저장하는 pair클래스 생성
class Pair{
    int start;
    int end;
    Pair(int v1, int v2){
        start = v1;
        end = v2;
    }
}
public class P_2800 {
    static ArrayList<Pair> paren = new ArrayList<>();
    static List <Integer> left = new ArrayList<>();
    static List <Integer> right = new ArrayList<>();
    static ArrayList<String> total = new ArrayList<>();
    static boolean [] visit = null;
    static BufferedReader br = null;
    static String exp = "";
    static TreeSet<String> set = new TreeSet<>();

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in)) ;
        exp = br.readLine();
        //exp를 탐색하며 ( 와 )의 index를 list에 저장
        for(int i=0;i<exp.length();i++){
            char c = exp.charAt(i);
            if(c == '('){
                left.add(i);
            }
            else if (c == ')'){
                right.add(i);
                paren.add(new Pair(left.get(left.size()-1), right.get(right.size()-1)));
                left.remove(left.size()-1);
                right.remove(right.size()-1);
            }
            else{
                continue;
            }
        }

        visit = new boolean [exp.length()];
        dfs(0);

        for(String s : set){
            if(s.equals(exp)){
                continue;
            }
            else{
                System.out.println(s);
            }
        }

    }

    //괄호를 사용하냐 안하냐 조합 경우의수 구하는 메소드
    public static void dfs(int depth){
        if(depth == paren.size()){
            StringBuilder sb = new StringBuilder();
            for(int k=0;k<exp.length();k++){
                if(exp.charAt(k) == '(' || exp.charAt(k) == ')'){
                    if(visit[k]){
                        sb.append(exp.charAt(k));
                    }
                }
                else{
                    sb.append(exp.charAt(k));
                }
            }
            set.add(sb.toString());
            return;
        }

        Pair p = paren.get(depth);
        //현재 depth의 (start,end)위치의 괄호 쌍을 사용할경우 안할경우를 구현한 코드
        visit[p.start] = true;
        visit[p.end] = true;
        dfs(depth+1);
        visit[p.start] = false;
        visit[p.end] = false;
        dfs(depth+1);
    }
}
```
---
🧩 주의할 점
1. 주어진 input수식과 같은 수식이 생길 수 있으므로 같다면 출력하지 않는다.
2. 예를 들어 (((1)))이라는 수식에서 첫번째 괄호쌍과 두번째 괄호쌍을 지우면 (1)이지만, 두번째 괄호쌍과 세번째 괄호쌍을 지워도 (1)이기 때문에 중복된 수식이 생길 수 있다. 따라서 StringBuilder로    쌓은 문자열을 저장할 때, HashSet 또는 TreeSet에 저장을 하면 중복을 자동으로 없앨 수 있다.
3. 나는 자동으로 정렬을해주는 이진트리Set인 TreeSet에 만들어진 수식을 저장하였기 때문에 따로 정렬을 하지 않았고 원래의 수식과 같은 경우만 출력을 하지 않도록 처리를 해 주어서 출력하였다.
  
