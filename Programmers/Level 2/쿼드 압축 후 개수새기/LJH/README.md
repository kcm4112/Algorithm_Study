## **👀문제 해석**

-   **한 사각형에 대해서 그 사각형이 모두 같은 수로 채워지지 않았다면 쪼개줘야 합니다.**
-   **분할정복 문제라고 할 수 있습니다. \[뭐 다른 방법도 있겠지만요\]**

## **🔎문제 풀이**

1.  **처음에 해당 사각형이 모두 같은 수로 채워져 있는지 검사해줘야 합니다.**
    -   **여기서 검사가 통과한다면 해당 사각형의 숫자에 해당하는 값을 +1 해줍니다.**
    -   **아니라면 아래와 같은 과정을 반복해야 합니다.**
2.  **사각형을 4등분 해줘야 합니다.**
3.  **4등분 한 뒤 위의 1번 단계를 반복해주면 됩니다.**
4.  **여기서 분할을 멈추는 조건은 1번 단계에서 시행하는 검사에 통과하면 됩니다.**

## **✔느낀 점**

-   **프로그래머스는 다른 사람의 풀이를 볼 수 있다.**
-   **나는 배열 4개를 계속 생성해서 1,2,3,4 사분면으로 쪼개줬는데 하나의 배열을 쓰고 푼 사람들이 의외로 많아서 많이 배웠다.**
-   **탐색을 써서 풀었던데 그 코드를 읽고 많이 도움이 된 것 같다.**
-   **배열 4개를 만들어서 푼 건 시간적으로나 메모리적으로 많이 부족한 코딩방법이다. \[반성,,\]**
