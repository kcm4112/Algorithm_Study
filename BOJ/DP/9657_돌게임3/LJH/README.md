​
## **🔎문제 해석**
​
**이 문제를 푸는 방법은 많을 것 같다. 하지만 몇 번 그림을 그리다 보면 특별한 규칙을 찾을 수 있고, 그 규칙을 통해서 식을 세워보면 쉽게 문제를 풀 수 있다.**
​
**우선 문제를 보면 완벽하게 게임을 한다는 말이 있다.** 
​
**완벽한 게임이란? 무조건 최선의 선택을 한다는 뜻. 즉 상대방이 이길 수 있는 수를 절대 두지 않는다는 뜻이다.** 
​
**그래서 나는 선공을 하는 사람이 이기는 경우가 아예 없을 경우 후공이 완벽하게 이기고, 선공이 한번이라도 이기는 경우가 있다면 선공이 이기는 것이라고 해석했다.**
​
**규칙을 보면 돌을 1개,3개,4개를 가지고 올 수 있다.**
​

**DP \[i\]는 i개의 돌이 있을 때 선공이 이길지 후공이 이길지를 0과 1로 선언해서 설계했다. \[1은 선공 승, 0은 후공 승\]**
​


**🔎예를 들어서 설명하겠다.**
​
> **만약 돌이 한개 있을 경우 : 선공인 사람이 1개를 가져가면 되므로 DP \[1\]=1이다.**  
> **만약 돌이 두 개 있을 경우 : 선공인 사람이 1개, 후공인 사람이 1개를 가져가게 되므로, DP \[2\]=0이다.**  
> **만약 돌이 세 개 있을 경우 : 선공인 사람이 3개를 가져가면 되므로 DP \[3\]=1이다.**  
> **만약 돌이 네 개 있을 경우 : 선공인 사람이 4개를 가져가면 되므로 DP \[4\]=1이다.**
​
---
​
**위의 예시는 최대로 가져올 수 있는 돌의 개수인 4개로 쉽게 만들 수 있는 예시이다.**
​

**만약 돌이 5개 있을 때는 누가 이길까?**
​


**돌 5개에서 뽑고 남게 되는 경우의 수는 아래와 같다.**
​
![img](https://user-images.githubusercontent.com/99114456/188263128-41727c27-73f9-4b73-b3a8-3542011fcec3.png)
​

**즉 DP \[5\]는 (DP \[4\], DP \[2\], DP \[1\]) 3개의 값에 영향을 받는다. 
DP\[4\], DP\[2\], DP\[1\] 3개 중에서 하나라도 0 \[후공승\]이 있다면 DP \[5\]의 값은 1이 될 것이다. 왜냐하면 DP\[5\]에서  ->DP\[4\], DP\[2\],DP\[1\]을 간다는 것 자체가 턴을 하나 소비한 것이고, 아래 단계에서의 후공이 DP\[5\]에서는 선공이 될것이다. 그래서 하위 단계에서 하나라도 후공의 승이 있다면 DP\[I\]값은 1이 될것이고, 하나도 없으면 DP\[I\]값은 0이 될것이다.**
​
**이렇게 계속해서 DP \[i\] 값을 추가해주면 어떠한 큰 보기가 와도 빠르게 아래 가지에서 값을 찾아내서 해결할 수 있을 것이다.**

---

## **✔느낀 점**

-   **DP는 한 번에 그 관계(?), 아니면 식 같은걸 찾아내야 하는 능력이 필요하다.**
-   **DP 배열을 1차원만 사용할지 2차원을 사용할지 등등**
-   **많이 풀어보는 것이 정답인둣!**
​
