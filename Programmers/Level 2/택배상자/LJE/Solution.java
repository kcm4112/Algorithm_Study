import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> sub = new Stack<>();
        int answer = 0;
        int box=1;
        int index=0;
        while(true){
            if(index>=order.length || box>order.length+1)
                break;

            if(order[index]!=box){
                //스택에서 찾기
                if(sub.size()!=0 && sub.peek()==order[index]){
                    answer++;
                    index++;
                    sub.pop();
                }
                //스택에도 없다면 스택에 넣기
                else{
                    sub.push(box);
                    box++;
                }
            }
            else{
                answer++;
                index++;
                box++;
            }
        }

        return answer;
    }
}