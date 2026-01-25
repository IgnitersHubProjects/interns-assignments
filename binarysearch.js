const readline =require('readline');
const rl =readline.createInterface({
  input:process.stdin,
  output:process.stdout
});
function binarySearch(arr,target){
  let left=0;
  let right=arr.length-1;
 while(left<=right) {
    const mid=Math.floor((left+right)/2);
    if(arr[mid]===target) return mid;
    if(arr[mid]<target) left = mid + 1;
    else right = mid - 1;
  }
  return -1;
}
rl.question('Enter sorted array elements: ',(arrInput)=>{
  const arr=arrInput.split(/[\s,]+/).map(Number);
  rl.question('Enter the target element: ',(targetInput)=>{
    const target=Number(targetInput);
    const index=binarySearch(arr,target);
    if(index!==-1) {
      console.log(`Element ${target} found at index ${index}.`);
    }else{
      console.log(`Element ${target} not found in the array.`);
    }
    rl.close();
  });
});
