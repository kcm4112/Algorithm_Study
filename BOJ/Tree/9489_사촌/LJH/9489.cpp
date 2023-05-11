#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, k;
int main()
{
    while (1)
    {
        cin >> n >> k;
        if (n == 0 && k == 0) // 종료해야함.
            break;
        int parent_idx = -1, before = 0, ans = 0, data;
        int parent[1000001];
        vector<int> node;
        for (int i = 0; i < n; i++){
            cin >> data;
            node.push_back(data);
            if (i == 0){ // 첫입력이면 루트노드임.
                before = data;
                parent[data] = -1; // 자기가 자신의 부모임.
            }
            else{
                if (before + 1 == data){ // 같은집합.
                    parent[data] = node[parent_idx];
                    before = data;
                }
                else{
                    before = data;
                    parent_idx++;
                    parent[data] = node[parent_idx]; // data의 부모를 넣어줌.
                }
            }
        }
        // parent[data]에는 data의 부모가 있습니다.우리는 k의 사촌 수를 찾아야 합니다.
        if (k == node[0]){
            ans = 0;
        }
        else{
            for (int i = 0; i < node.size(); i++){
                //  k와 부모가 다르고, k의 부모들과 부모가 같아야합니다.
                if (parent[parent[node[i]]] == parent[parent[k]] && parent[node[i]] != parent[k]){
                    ans++;
                }
            }
        }
        cout << ans << endl;
    }
}