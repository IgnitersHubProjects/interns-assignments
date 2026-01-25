const fs=require('fs');
const readline=require('readline');

const words=fs.readFileSync('words.txt','utf8')
    .split('\n')
    .map(w => w.trim())
    .filter(Boolean);
function Subsequence(input,target) {
    let inputIdx=0;
    for(let i=0;i<target.length;i++){
        if(inputIdx<input.length&&target[i]===input[inputIdx]){
            inputIdx++;
        }
    }
    return inputIdx===input.length;
}
const rl=readline.createInterface({input:process.stdin,output:process.stdout});
function ask() {
    rl.question('Input >> ',(input)=>{
        if(!input){
            ask();
            return;
        }
        const matches =words.filter(word=>Subsequence(input,word));
        matches.sort((a,b)=>a.length-b.length);
        const result =matches.slice(0,3);
        console.log(`Output>> ${result.join(', ')}`);
        ask();
    });
}
ask();