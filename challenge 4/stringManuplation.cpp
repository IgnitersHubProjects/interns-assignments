#include<string>
#include<iostream>
#include<algorithm>
    using namespace std;
    class StringManuplation{
        public:
        string str,hyphenated;
        int i,count,len;
        StringManuplation(){
            count=1;
        }
        void getStr();
        void strCount();
        void strReverse();
        void getHyphens();
    };
    void StringManuplation :: getStr(){
        cout << "Enter the sentence" <<endl;
        getline(cin , str);
        hyphenated = str;
    }
    void StringManuplation :: strCount(){
        len = str.length();
        for(i=0;i<len;i++){
            if(str[i] == ' '){
                count++;
            }
        }
        cout << "Number of words:" << count << endl;
    }
    void StringManuplation :: strReverse(){
         len = str.length();
         string ans = " ",word;
         reverse(str.begin(),str.end());
        for (i=0;i<len;i++){
            word=" ";
            while(i<len && str[i] != ' '){
                word = word + str[i];
                i++;
            }
            reverse(word.begin(),word.end());
            if(word.length() > 0){
                ans = ans + word + " ";
            }
        }
        cout << "Reversed sentence:" << ans << endl;
    }
    void StringManuplation :: getHyphens(){
        len = hyphenated.length();
        for(i=0;i<len;i++){
            if(hyphenated[i] == ' '){
                hyphenated[i] = '-';
            }
        }
        cout << "Modified sentence:" << hyphenated << endl;
    }
    int main(){
        StringManuplation S;
        S.getStr();
        S.strCount();
        S.strReverse();
        S.getHyphens();
        return 0;
    }