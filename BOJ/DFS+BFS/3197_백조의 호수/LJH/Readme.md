## 🔎문제 해석

> 문제 자체적으로는 다른 골드 문제에 비해서 특별하게 어렵거나, 꼬았다는 부분은 없습니다  
> 하지만 메모리초과, 시간초과가 굉장히 많이 발생하기 때문에, 그 점을 유의해서 풀어야 합니다.

아래 사진은 저의 처참한 광경입니다. 2%대에서 한번 걸리고 ,47%에서 한번 걸렸습니다
![](https://blog.kakaocdn.net/dn/PcarJ/btr8w6SmyMO/SkAhtn1JjCGaDTTH8Xmh51/img.png)
이 부분은 나중에 말해드리겠습니다. 굉장히 스포이기 때문에)


우선 백조의 호수와 비슷한 문제의 유형은 백준에 치즈 문제와 빙산 문제가 있습니다.

다른 문제와 동일하게 얼음의 탐색방향과 백조의 탐색방향은 "상, 하 , 좌 우"로 4방향입니다.

### 📕초기 풀이 방식

백조를 탐색시키고, 얼음을 녹이고, 백조를 탐색시키고 , 얼음을 녹이는 과정을 반복하다가 또 다른 백조를 만난다면 종료.

이러한 방식이었습니다. 이러한 방식을 사용하니 `시간초과`가 났습니다.

(이때는 단순 무식하게, 탐색하면서 얼음을 녹이지도 않고, 백조의 위치도 계속 고정시킨 탐색이었음)

### 📗수정

얼음을 녹이는 과정에서 얼음을 녹인다면, 해당 지역이 물로 바뀌어서 다른 얼음이 (얼음에서 바뀐) 물에 영향을 받을 수도 있기에,

우선 얼음이 녹는 경우 후보를 정해서, 모든 후보를 정한 뒤 "후보들만" 물로 바꿔주고, 진행했습니다.

이렇게 해도,, `시간초과`..

### 📗수정

그러면 얼음을 탐색하는 과정에서, 얼음을 바로 녹여줘야 하나?라고 생각했습니다.

그래서 얼음은 물에 영향을 받기 때문에 , 이제 기준을 물로 바꿔줬습니다. 물을 기준으로 상하좌우가 얼음이라면, 얼음은 물로 바꿔줬습니다

이렇게 얼음을 물로 바꿔도, 물은 전혀 영향을 받지 않기 때문에, 탐색하는 동시에 얼음을 녹일 수 있었습니다.

이렇게 해도 `메모리 초과`..

아마 queue에 너무 많은 원소가 들어갔거나, queue에 많은 값이 할당되어서 그렇게 된 거 같습니다.

### 📗수정

그렇다면 어떻게 queue에 적은 원소를 넣을 수 있을까요?

정답은 바로 `탐색범위`를 줄이는 것입니다.

저는 기존에 항상 백조의 위치에서 탐색을 시작했습니다.

그렇기에 날이 지날 때마다 탐색했던 위치를 또 탐색하고, 또 탐색하고 , 또 탐색했습니다.

당연히.. queue에 많은 좌표가 들어갈 수밖에 없습니다.

따라서 만약 백조가 이동하다가 막혔을 경우 (얼음을 만났다면) 그때 기준점을 새로운 백조 큐에 넣어줍니다.

기준점을 잡아서 이동하기 때문에 기준점이 이제 백조가 최대한 갈 수 있는 지점들일 것입니다.

그리고 이러한 기준점들을 기록하기 위해서 새로운 백조 큐를 생성해 줬습니다.

더 좋은 방법이 있는지는 모르겠지만, 제 최선은 새로운 백조 큐를 생성해 줘서 거기다가 백조의 위치를 넣고 다시 기존 백조 큐에 복사해 줬습니다.

### 📗수정

자 이렇게 되면 `메모리 초과`도 해결될 줄 알았습니다..

하지만.. 47%에서 메모리 초과가 떴습니다.. 음 아무리 고민해 봐도 , 메모리 초과가 뜬 이유를 모르겠어서, 이제 질문 게시판을 찾아보다가,

한 가지를 발견하고, 통과했습니다. 그 한 가지는 바로 백조의 위치도 물이다. 즉 초기 백조의 위치도 물이다!입니다.

백조는 물에만 돌아다닐 수 있기 때문에, 당연히 백조의 위치는 물입니다. 저는 초기 큐를 세팅할 때 물 따로, 백조 따로를 넣었었습니다.

하지만 수정된 코드는 백조 따로, 얼음이 아닌 것 따로(물)을 큐로 세팅을 하고 코드를 제출하니 정답이었습니다.

💡정리를 해보자면

-   시간초과를 해결하기 위해서는 얼음을 탐색하는 동시에 바로 녹여야 한다!
-   메모리 초과를 해결하기 위해서는 백조가 갈 수 있는 최대 지점을 큐에 기록해야 한다!
-   백조의 위치도 물이다...
-   모두 제 개인적인 생각입니다.

필요한 큐의 개수는 백조큐, 새로운 백조큐, 물 큐, 새로운 물큐 이렇게 4개가 필요합니다.

당연히 bfs 이기에, 방문체크 배열도 필요할 것입니다.

함수

funct() -> 총괄적인 bfs (백조 탐색, 얼음 탐색)이 들어감.

melt\_ice(); -> 얼음을 녹이는 bfs

## 😀느낀 점

-   시간초과, 메모리 초과 때문에 굉장히 많은 고생을 한 문제인 만큼 풀어서 뿌듯했다.
-   원래 보통 골드 문제들은 큐만 따로 쓰고, bfs를 진행해도 문제가 없었지만, 플레인만큼 탐색하면서 값을 바꾸는 작업 같은 최적화 작업이 필요해서 굉장히 어려웠다.
