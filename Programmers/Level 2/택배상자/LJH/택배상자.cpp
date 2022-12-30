
#include <string>
#include <vector>
#include <stack>
using namespace std;

const int MAX = 1e6 + 1;

bool visited[MAX];

int solution(vector<int> order) {
    int answer = 0;
    stack<int> conveyorBelt;
    int idx = 1;

    for (int o : order)
    {
        if (o < idx)
        {
            if (conveyorBelt.empty() || conveyorBelt.top() != o)
            {
                break;
            }

            conveyorBelt.pop();
            idx--;
        }

        while (o > idx)
        {
            if (!visited[idx])
            {
                visited[idx] = true;
                conveyorBelt.push(idx);
            }

            idx++;
        }

        visited[o] = true;
        answer++;
    }

    return answer;
}