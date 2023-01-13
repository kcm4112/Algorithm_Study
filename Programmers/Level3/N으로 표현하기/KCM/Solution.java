import java.io.*;
import java.util.*;

class Solution {
    //list(i)는 의미는 N을 i개 사용하여 만들 수 있는 수들의 집합이다.
    List<Set<Integer>> list = new ArrayList<>();
    
    public int solution(int N, int number) {
        int answer = -1;
        //리스트의 각 원소를 HashSet으로 초기화해줌. 중복되는 수는 저장할 필요가 없기 때문에!
        for(int i=0; i<9; i++) {
            list.add(new HashSet<>());
        }
        
        //N 1개로 만들수 있는 수는 N밖에 없기때문에 무조건 N으로 저장
        list.get(1).add(N);
        
        for(int i=2;i<9;i++) {
            Set<Integer> set = list.get(i);
            
            for(int j=1;j<=i;j++) {
                //왼쪽 수와 오른쪽 수를 지정하기 위한 set 선언
                Set<Integer> left = list.get(j);
                Set<Integer> right = list.get(i-j);
                
                for(int left_num : left) {
                    for(int right_num : right) {
                        set.add(left_num + right_num);
                        set.add(left_num - right_num);
                        set.add(left_num * right_num);
                        if(left_num!=0 && right_num!=0) {
                            set.add(left_num / right_num);
                        }
                    }
                }
                set.add(Integer.parseInt(Integer.toString(N).repeat(i)));
            }
            // list.get(i).add(set);
        }
        
        for(Set<Integer> check : list) {
            if(check.contains(number)) {
                answer = list.indexOf(check);
                break;
            }
        }
        return answer;
    }
}
