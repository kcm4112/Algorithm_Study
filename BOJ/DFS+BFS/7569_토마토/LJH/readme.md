﻿
## **💡문제해석**

-   **토마토가 모두 다 익을 수 있는 최소의 일수를 구한다는 것이 문제의 목표이다.**
    -   **우리는 최솟값, 최소 경로를 구하기 위해서는 DFS보단 BFS가 효율적이라는 것을 이전 알고리즘 소개에서 배웠을 것이다.**
-   **문제 특성상 3차원 배열로 푸는 게 쉬울 거 같아서 모든 배열을 3차원 배열로 해결했다.**
-   **익은 토마토의 위치를 queue에 넣어줌.**
-   **queue에서 front에서 뽑고 pop 하고 경로 체크와 토마토 체크를 한 뒤 date와 visit, tomato를 각각 업데이트해주고, queue에 넣는다.**
-   **queue가 빌 때까지 과정을 했는데 만약 빈 토마토가 있다면 -1을 출력 아니라면 해당 날짜를 출력함.**

## **👀구현**

**1\. 변수 선언**

![img](https://user-images.githubusercontent.com/99114456/182024143-b7952a0b-3cc3-4d9c-9ea9-4bf9fc2bc17d.png)

-   **tomato는 익은 토마토, 안 익은 토마토, 빈 장소를 각각 넣음.**
-   **visit은 방문을 했는지, 안 했는지의 여부를 검사.**
-   **date는 며칠 차인지를 검사함.**
-   **방향은 총 6가지 방향이 있다. \[왼쪽, 오른쪽, 아래, 위, 앞, 뒤\]**
    -   **방향 설정에 유의하자. 방향설정 때문에 계속 틀렸었다..!**

#### **2\. 행렬 만들기**

![img](https://user-images.githubusercontent.com/99114456/182024147-d66dc0cc-af59-4c63-8ec4-a29ca0758cd8.png)

-   **토마토가 익은 토마토 라면 해당 위치를 queue에 넣어줌.**
-   **pair(i\*m+j, k) 해준 이유는 나중에 쉽게 자기 좌표를 역 추적할 수 있기 때문이다.**
-   **아래 그림처럼 나는 좌표를 숫자로 변환해서 넣어줬다.**
-   **만약 (2,2) -> 8  왜냐하면 2(row)\*(세로의 개수)+2(col)이기 때문.**

![img](https://user-images.githubusercontent.com/99114456/182024148-38fb1e0d-7e13-4643-aa24-3239c3d547ab.png)

#### **3.BFS 함수 설명**

![img](https://user-images.githubusercontent.com/99114456/182024149-baf3ced9-1085-4fe1-96ea-32b908180171.png)

-   **queue에 front에 있는 좌표를 뽑아서 변수에 저장 후 pop 해준다.**
-   **방향은 총 6가지 방향이 있고, 옮겨준 좌표가 이동할 수 없는 index라면 continue 해준다.**
-   **이동할 수 있는 경우 그 이동한 좌표의 토마토가 익지 않았고, 방문을 하지 않았다면 토마토가 익었다는 표시를 하고, 방문했다는 표시를 하고 날짜를 최신화해준다.**

#### **4\. 답 출력**

![img](https://user-images.githubusercontent.com/99114456/182024150-fe2866a6-8c32-462d-bf95-7315e1cd9c6a.png)

-   **만약 행렬을 탐색해서 안 익은 토마토가 있다면 day에 -1을 저장하고, 만약 안익은 토마토가 없다면 원래 저장된 day를 출력함.**

## **✔느낀 점**
​
-   **확실히 이론 정리를 하고 문제를 접근하니 무슨 방법을 써야 할지 바로 느낌이 와서 시간을 많이 단축했다.**
-   **문제를 이해하는데 조금 고생을 했다.(방향이랑 일차)**
-   **이해하고 보면 문제를 구성하는 알고리즘 자체는 쉬운 편이다.**