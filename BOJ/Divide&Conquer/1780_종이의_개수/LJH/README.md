## **🔎문제 해석**

> **이러한 문제가 전형적인 분할 정복의 문제인 것  같다.**  
> **조건에 부합하지 않으면 9등분으로 분할해서 다시 조건을 검사하고 조건에 맞으면 분할을 멈추고 하는 그러한 문제...**  
> **우선 종이를 검사해서 하나라도 다른 요소가 있다면 그 즉시 9 등분하고 각각의 등분에서 또 검사하고, 요소가 계속 다르더라도 종이의 개수가 한 개면 \[임계점\] 분할을 그만하고 그 요소에 따라 종이의 개수를 더해주면 된다.**

## **⚡구현**

**우선 나는 재귀 함수로 계속 종이를 분할했다. \[자세한 재귀 함수는 아래에 설명할 것이다\]**

#### **💻종이의 구성요소를 검사하는 함수**

![img](https://user-images.githubusercontent.com/99114456/186872569-27d89bce-fb8c-4e58-93e4-be65883bad27.png)

**종이의 첫 번째 원소를 기준으로 하나라도 다르다면 그 즉시 cp를 false로 초기화하고 만약 원소가 같다면 그대로 cp의 초기값인 true가 반환될 것이다.**

**\--> checkpaper값이 true라면 종이의 모든 구성요소가 같다는 뜻.  --> sumpaper 실행**

                               **false라면 종이의 구성요소가 하나라도 다르다는 뜻--> 분할해야 함. \[재귀 함수 실행\]**

#### **💻종이의 개수를 더해주는 함수**

![img](https://user-images.githubusercontent.com/99114456/186872578-6fb82d66-b5ed-478a-9781-480415f03ba1.png)

**우선 이 함수가 실행되는 조건이 모든 종이가 같은 숫자기 때문에 편의상 제일 첫 번째 원소를 기준으로** 

**\-1,0,1에 따라 각각 sum 배열에 더해줬다.**

#### **💻재귀 함수 내부**

![img](https://user-images.githubusercontent.com/99114456/186872726-44d52355-4e47-4c69-87b0-cb2e0b34ac2f.png)

**재귀함수 내부에서 9등분을 어떻게 구현했냐면**

![img](https://user-images.githubusercontent.com/99114456/186872607-c37ff756-fcb0-47a4-8337-5ce752eb3524.png)

**각각의 첫 번째 시작점을 인자로 넘겨주고 배열의 크기를 넘겨주면서 배열을 구분했다.**

**예를 들어 makepaper(0,0, Size) 면 (0,0)에서 시작하는 배열이고 배열의 크기는 Size\*Size라는 뜻이다.**

**그렇다면 makepaper(0+2\*Size,2+Size, Size)는 어떤 배열을 넘겨줄까?**

![img](https://user-images.githubusercontent.com/99114456/186872614-bd0b7766-39fc-467c-90f0-7a654830a501.png)

**위의 배열을 넘겨줄 것이다. 이렇게 나는 각 행, 열 의 시작 인자와 배열의 크기를 넘겨주면서 큰 배열을 분할했다.**

## **✔느낀 점**

-   **초기에는 배열 9개를 매번 만들어서 함수의 인자로 넘겨줬는데 시간 초과가 났다..ㅜㅜ**
    -   **이유는 함수 인자에 배열을 넘겨주면 매번 함수가 실행될 때마다 배열이 복사되는 시간이 발생해서 당연히 시간 초과가 나는 것이다.**
-   **이러한 분할 정복은 하나의 배열을 가지고 계속해서 쪼개는 문제인데 너무 날먹으로 풀려고 했던 것 같다 ㅎㅎ..**
-   **재귀 함수 내부를 더욱 깔끔하게 짤 수 있었을 거 같음(if문을 남발했는데 안 하고 전체를 반복문으로 해도 좋았을 듯?)**
-   **분할 정복의 개념이 헷갈렸는데 이번 종이의 개수 문제가 딱 전형적인 분할 정복을 맛보기 좋았던 문제인 것 같다.**
