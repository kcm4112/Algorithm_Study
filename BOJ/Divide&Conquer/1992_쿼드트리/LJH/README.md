## **🔎문제해석**

**앞에서 푼 종이의 개수를 풀고 푸니까 훨씬 더 쉬운 느낌?**

**분할 정복을 푸는 방법이 딱 정형화되어있는 거 같아서 풀면 풀수록 쉬웠다. 이 문제는 분할하고 정복하는 것은 쉽지만 조금 까다로웠던 부분이 출력 문제였다. 저렇게 특이한 형식으로 출력해야 했기에 조금 귀찮았다.**

**나는 처음에 괄호를 어떻게 넣어줘야 할지 몰라서 앞, 뒤로 접근이 용이한 deque를 사용했다.**

**하지만 지금 다시 와서 보니 deque 말고 queue도 괜찮고 vector도 괜찮았을 것 같다.!!** 

**어떤 자료구조를 사용해도 상관없을 것 같다.** 

## **💻기존코드**
```
if (checkTree(row, col, tsize) == false)
            {                
                makeTree(row, col, tsize);
            }
            else
            {           
                decideTree(row, col);
            }
            if (checkTree(row, col + tsize, tsize) == false)
            {               
                makeTree(row, col + tsize, tsize);
            }
            else
            {               
                decideTree(row, col + tsize);
            }
            if (checkTree(row + tsize, col, tsize) == false)
            {               
                makeTree(row + tsize, col, tsize);
            }
            else
            {               
                decideTree(row + tsize, col);
            }
            if (checkTree(row + tsize, col + tsize, tsize) == false)
            {                
                makeTree(row + tsize, col + tsize, tsize);
            }
            else
            {               
                decideTree(row + tsize, col + tsize);
            }
```

## **💻수정코드**
```
 int x[2] = {row, row + tsize};
            int y[2] = {col, col + tsize};
            // 4분면을 탐색함.
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    if (checkTree(x[i], y[j], tsize) == false)
                    {
                        makeTree(x[i], y[j], tsize);
                    }
                    else
                    {
                        decideTree(x[i], y[j]);
                    }
                }
            }
```

## **✔느낀 점**
​
-   **앞선 종이의 개수를 구하는 코드에서는 if문을 남발했었지만, 지금은 더 깔끔하게(?) 짠 것 같다.**
-   **이제 분할하는 구간의 개수가 많아져도 저렇게 for 문을 이중으로 쓰면 쉽게 풀어질 듯**
-   **분할 정복 정벅 ㅋ**
