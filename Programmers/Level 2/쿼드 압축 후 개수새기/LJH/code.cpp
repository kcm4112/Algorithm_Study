#include <iostream>
#include <string>
#include <vector>

using namespace std;
vector<int> answer;
void divide(vector<vector<int>>arr,int s);
int check_square(vector<vector<int>>arr);
vector<int> solution(vector<vector<int>> arr) {
    answer.resize(2,0);
    int size= arr[0].size(); //size
    divide(arr,size);
    return answer;
}
int check_square(vector<vector<int>>arr){
    int num=arr[0][0];
    for(int i=0; i<arr.size();i++){
        for(int j=0; j<arr.size();j++){
            if(num!=arr[i][j])
                return -1;            
        }
    }
    return num;
}
void divide(vector<vector<int>>arr,int s){
    if(check_square(arr)!=-1){
        if(arr[0][0]==1)
            answer[1]++;
        else
            answer[0]++;
    }
    else{
        int size= s/2;
        vector<vector<int>>q1;
        vector<vector<int>>q2;
        vector<vector<int>>q3;
        vector<vector<int>>q4;
        q1.resize(size,vector<int>(size,0));
        q2.resize(size,vector<int>(size,0));
        q3.resize(size,vector<int>(size,0));
        q4.resize(size,vector<int>(size,0));
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                q1[i][j]=arr[i][j];
                q2[i][j]=arr[i][j+size];
                q3[i][j]=arr[i+size][j];
                q4[i][j]=arr[i+size][j+size];
            }
        }
        divide(q1,size);
        divide(q2,size);
        divide(q3,size);
        divide(q4,size);
    }

}
