import sys

input = sys.stdin.readline
S, E, Q = map(str, input().split())
chat = []
student = {}
answer = 0

S = int(S.replace(':', ''))
E = int(E.replace(':', ''))
Q = int(Q.replace(':', ''))

for i in sys.stdin:
    live = list(i.split())

    enter = int(live[0].replace(':', ''))
    name = live[1]

    if enter <= S:
        student[name] = enter
    else:
        if enter >= E and enter <= Q:
            if name in student and name not in chat:
                answer += 1
                chat.append(name)

print(answer)