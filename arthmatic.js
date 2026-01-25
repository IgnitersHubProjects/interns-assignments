const fs =require('fs');
const inputFile='input.txt';
const outputFile='output.txt';
function solveExpression(expr){
    try{
        let cleaned =expr.replace('=','').trim();
        cleaned =cleaned.replace(/\^/g,'**');
        cleaned =cleaned.replace(/[\[\{]/g, '(').replace(/[\]\}]/g, ')');
        cleaned =cleaned.replace(/\)\s*\(/g, ')*(');
        const result =new Function('return ' + cleaned)();
        return result;
    }catch(e){
        return 'Error';
    }
}
fs.readFile(inputFile, 'utf8',(err,data)=>{
    if(err)return console.log(err);
    const lines =data.split(/\r?\n/);
    let outputData ='';
    lines.forEach(line =>{
        if(!line.trim()) return;
        const result =solveExpression(line);
        outputData += `${line.replace('=', '').trim()} = ${result}\n`;
    });
    fs.writeFile(outputFile,outputData,(err)=>{
        if(err) console.log(err);
        else console.log(`Processed. Results in ${outputFile}`);
    });
});