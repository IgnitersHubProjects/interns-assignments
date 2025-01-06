#include<iostream>
using  namespace std;
class BinarySearch{
    public:
    int left,right,mid;
    int binarySearch(int a[],int size,int data){
        left = 0;
        right = size-1;
        while(left <= right){
            mid = (left+right)/2;
            if(data == a[mid]){
                return mid;
            }
            else if(data < a[mid]){
                right = mid -1;
            }
            else{
                left =mid +1;
            }
        }
        return -1;
    }
};
int main(){
    BinarySearch B;
    int a[]={1, 3, 5, 7, 9, 11, 13, 15, 17, 19},size,data,index;
    size = sizeof(a)/sizeof(a[0]);
    cout << "Enter the target element"<<endl;
    cin >> data;
    cout << "The array:";
    for(int i=0;i<size;i++){
        cout << a[i] << " ";
    }
    cout << endl;
    index=B.binarySearch(a,size,data);

    if(index == -1){
        cout << "Element not found" << endl;
    }
    else{
        cout << "Element "<< data << " is found at index "<< index << endl;
    }
}