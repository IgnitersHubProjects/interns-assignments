const express = require('express');
const multer = require('multer');
const fs = require('fs');
const path = require('path');

const app = express();
const upload = multer({ dest: 'uploads/' });
const PORT = 3000;

app.use(express.static(path.join(__dirname, 'public')));

function evaluateExpression(expression) {
    try {
        const safeExpression = expression.replace(/\^/g, '**');
        return eval(safeExpression);
    } catch (error) {
        return `Error: Invalid expression`;
    }
}

app.post('/process-file', upload.single('file'), (req, res) => {
    const inputFile = req.file.path;
    const outputFile = path.join(__dirname, 'uploads', `output-${Date.now()}.txt`);

    const inputData = fs.readFileSync(inputFile, 'utf-8');
    const lines = inputData.split('\n');


    const results = lines.map((line) => {
        const match = line.match(/^(.*)=\s*(.*)$/);
        if (match) {
            const expression = match[1].trim(); 
            const providedResult = match[2].trim(); 
    
            try {
                const calculatedResult = evaluateExpression(expression); 
                if (providedResult) {
                    return calculatedResult == providedResult
                        ? `${line.trim()}`
                        : `${line.trim()} Error: Incorrect result (expected ${calculatedResult})`;
                } else {
                    return `${expression} = ${calculatedResult}`;
                }
            } catch (error) {
                return `${line.trim()} Error: ${error.message}`;
            }
        } else {
            return `${line.trim()} Error: Invalid format`;
        }
    });
    
    

    fs.writeFileSync(outputFile, results.join('\n'));

    res.download(outputFile, 'output.txt', () => {
        fs.unlinkSync(inputFile); 
        fs.unlinkSync(outputFile); 
    });
});

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
