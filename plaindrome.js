const readline =require('readline');

const rl =readline.createInterface({
  input:process.stdin,
  output:process.stdout
});
function isPalindrome(str) {
  const cleanStr =str.toLowerCase().replace(/[^a-z0-9]/g, '');
  let left =0;
  let right =cleanStr.length-1;
  while(left<right){
    if(cleanStr[left]!==cleanStr[right]){
      return false;
    }
    left++;
    right--;
  }
  return true;
}
rl.question('Enter a string : ',(inputString)=>{
  if(isPalindrome(inputString)){
    console.log(`The string '${inputString}' is a palindrome.`);
  }else{
    console.log(`The string '${inputString}' is not a palindrome.`);
  }
  rl.close();
});