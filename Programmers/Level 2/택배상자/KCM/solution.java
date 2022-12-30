import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] order) {
        Stack <Integer> sub = new Stack<>();
        int answer = 0;

        int orderIndex = 0; //order배열에 있는 것들을 탐색하기 위한 변수
        int number = 1; //현재 컨베이어벨트에 있는 상자들을 탐색하기 위한 변수

        while(true) {
            if(!sub.isEmpty() && sub.peek() == order[orderIndex]) {
                answer++;
                orderIndex++;
                sub.pop();
            }
            else if(number > order.length) { //스택의 맨 앞도 정답이 아닌데, 더이상 컨베이어에 박스가 없을 떄 즉, 스택의 안쪽에 찾는 박스가 있을 것이다.
                break;
            }
            else if(number == order[orderIndex]) {
                answer++;
                orderIndex++;
                number++;
            }
            else if(number != order[orderIndex]) {
                sub.push(number);
                number++;
            }
        }
        return answer;
    }
}
