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
