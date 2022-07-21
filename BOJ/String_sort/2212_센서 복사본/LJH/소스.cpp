#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, k;
int main()
{
    cin >> n >> k;
    vector<int> sensor(n, 0);
    vector<int> dif(n - 1, 0);
    if (n > k) //만약 센서의 개수보다 집중국의 개수가 많다면 모든 구간을 커버치므로 0임.
    {
        int sum = 0;
        for (int i = 0; i < n; i++) //sensor의 좌표를 입력받음.
        {
            cin >> sensor[i];
        }
        sort(sensor.begin(), sensor.end()); //sensor의 좌표를 오름차순으로 정렬함.
        for (int i = 0; i < n - 1; i++) //각 좌표들의 거리차를 구함.
        {
            dif[i] = sensor[i + 1] - sensor[i];
        }
        sort(dif.begin(), dif.end()); //거리차를 오름차순으로 정렬함.
        for (int i = 0; i < k - 1; i++) //k개의 집중국을 사용해서 k-1개의 구간을 커버할 수 있음.
        {
            dif.pop_back(); //거리의 최소값을 구해야 하므로, 거리가 큰 구간을 커버함.
        }
        for (auto a : dif)
        {
            sum += a; //남아있는 거리 차이를 더함.
        }
        cout << sum;
    }
    else //만약 집중국이 센서보다 많다면 모든 센서를 커버할 수 잇음.
    {
        cout << 0;
    }
}