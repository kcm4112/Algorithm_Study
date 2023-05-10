#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int r,c,t;
vector<vector<int> >ary;
int urow,ucol,drow,dcol;
int dir[4][2]={ {0,1},{0,-1},{1,0},{-1,0} };
void dust_move();
vector<pair<int,int> >mc;
queue<pair<int,int> >dust;
void air();
void print_ary();
int main(){
    
    cin>>r>>c>>t;
    ary.resize(r,vector<int>(c,0));
    for(int i=0; i<r; i++){
        for(int j=0; j<c;j++){
            cin>>ary[i][j];
            if(ary[i][j]==-1){
                mc.push_back(make_pair(i,j));
            }
        }
    }
    urow = mc[0].first,ucol=mc[0].second,drow=mc[1].first,dcol=mc[1].second;
    while(t--){
        dust_move();
        air();        
    }
    int answer=0;
    for(int i=0; i<r; i++){
        for(int j=0; j<c; j++){
            if(ary[i][j]==-1){
                continue;
            }
            answer+=ary[i][j];
        }
    }
    cout<<answer<<endl;
}
void air(){ //공기청정기 작동. 시계방향, 반시계방향으로 공기청정기로 들어오는 미세먼지는 없어진다.
    //좌표 기준으로 반시계, 시계 방향
    //시계방향
    int temp =  ary[drow][c-1]; // ->
    int temp2=ary[r-1][c-1]; 
    int temp3=ary[r-1][0]; 
    for(int i=c-1; i>1; i--){     //위쪽
        ary[drow][i]=ary[drow][i-1];
    }
    ary[drow][1]=0;
   // 오른쪽
    for(int i=r-1; i>drow; i--){
        if(i==drow+1){
            ary[i][c-1]=temp;
            continue;
        }
        ary[i][c-1]=ary[i-1][c-1];
    }
 
    for(int i=0;i<c-1;i++){ // 아래쪽
        if(i==c-2){
            ary[r-1][i]=temp2;
            continue;
        }
        ary[r-1][i]=ary[r-1][i+1];
    }
    for(int i=drow+1; i<r-1; i++){ //왼쪽
        if(i==r-2){
            ary[i][0]=temp3;
            continue;
        }
        ary[i][0]=ary[i+1][0];
    }
    //반시계 방향
    temp=ary[urow][c-1];
    temp2=ary[0][c-1];
    temp3=ary[0][0];
    for(int i=c-1; i>0; i--){ //아래
        if(ary[urow][i-1]==-1){
            ary[urow][i]=0;
            continue;
        }
        ary[urow][i]=ary[urow][i-1];
    }
    for(int i=0; i<urow; i++){ //오른쪽
        if(i==urow-1){
            ary[i][c-1]=temp;
            continue;
        }
        ary[i][c-1]=ary[i+1][c-1];
    }
    for(int i=0; i<c-1; i++){ //위쪽
        if(ary[0][i]==-1){
            continue;
        }
        if(i==c-2){
            ary[0][i]=temp2;
            continue;
        }
        ary[0][i]= ary[0][i+1];
    }
    for(int i=urow-1; i>0; i--){ //왼쪽
        if(i==1){
            ary[i][0]=temp3;
            continue;
        }
        ary[i][0]=ary[i-1][0];
    }
}
void dust_move(){ //여기까지는 잘 되는듯.
    
    for(int i=0; i<r; i++){
        for(int j=0; j<c; j++){
            if(ary[i][j]>0){
                dust.push(make_pair(i,j));
            }
        }
    }
    vector<vector<int> >v;
    v.assign(r,vector<int>(c,0));
    v=ary;
    int size= dust.size();
    while(size--){
        int row =dust.front().first, col = dust.front().second;
        int val = ary[row][col]/5;
        dust.pop();
        for(int i=0; i<4; i++){
            int nrow = row+dir[i][0], ncol=col+dir[i][1];
            if(nrow<0 || nrow >=r || ncol<0 || ncol>=c || ary[nrow][ncol]==-1){
                continue;
            }
            v[nrow][ncol]+=val;
            v[row][col]-=val;
        }
    }
    ary=v;
}