#include<iostream>
using namespace std;
const int SIZE = 100;
int arr[SIZE];
int rec[SIZE];//记录每个子串的起始坐标
//排序数组arr[fir:end]
//合并操作的子函数
void merge(int fir,int end,int mid);
//扫描得到子串的子函数
int pass(int n);
//自然合并函数
void mergeSort3(int n);
/********************************************************************/

void mergeSort3(int n){
    int num=pass(n);
    while(num!=2){
        //num=2说明已经排好序了
        //每循环一次，进行一次pass()操作
        for(int i=0;i<num;i+=2)
            //坐标解释可参加P23页的图示
            merge(rec[i],rec[i+2]-1,rec[i+1]-1);
        num=pass(n);
    }
}
void merge(int fir,int end,int mid){
    //合并
    int tempArr[SIZE];
    int fir1=fir,fir2=mid+1;
    for(int i=fir;i<=end;i++){
        if(fir1>mid)
            tempArr[i]=arr[fir2++];
        else if(fir2>end)
            tempArr[i]=arr[fir1++];
        else if(arr[fir1]>arr[fir2])
            tempArr[i]=arr[fir2++];
        else
            tempArr[i]=arr[fir1++];
    }
    for(int i=fir;i<=end;i++)
        arr[i]=tempArr[i];
}
int  pass(int n){
    int num=0;
    int biger=arr[0];
    rec[num]=0;
    num++;
    for(int i=1;i<n;i++){
        if(arr[i]>=biger)biger=arr[i];
        else {
            rec[num]=i;
            num++;
            biger=arr[i];
        }
    }
    //给rec[]加一个尾巴，方便排序
    rec[num]=n;
    num++;
    return num;
}
int main(){
    int n;
    while(cin>>n){
        for(int i=0;i<n;i++)cin>>arr[i];
        //测试mergeSort函数
        /**/mergeSort3(n);
        for(int i=0;i<n;i++)cout<<arr[i]<<" ";
        cout<<endl;
        
        //测试pass函数
        /*int num = pass(n);
         for(int i=0;i<num;i++)cout<<rec[i]<<" ";
         cout<<endl;*/
    }
    return 0;
}