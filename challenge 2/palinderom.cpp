#include<iostream>
#include<string.h>
using namespace std;
class Palindrom{
    public:
    string a,b;
    int i,j,flag,len;
    void getStr();
    void calculatePalindrom();
};
void Palindrom :: getStr(){
    cout << "Enter a string"<<endl;
    getline(cin , a);
}
void Palindrom :: calculatePalindrom(){
    j=0;
    flag=0;
    len = a.length();
    for(i=len-1;i>=0;i--){
        b[j]=a[i];
        j++;
    }
    b[i]='\0';
    for(i=0;i<len;i++){
        if(a[i]!=b[i]){
            flag=1;
            break;
        }
    }
    if(flag==0){
        cout << "The string '" << a <<"' is a palindrom" << endl;
    }
    else{
        cout << "The string is not palindrom" << endl;
    }
}
    int main(){
        Palindrom P;
        P.getStr();
        P.calculatePalindrom();
        return 0;
    }