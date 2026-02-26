const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Levenshtein Distance
function levenshteinDistance(a, b) {
    const matrix = [];

    for (let i = 0; i <= b.length; i++) {
        matrix[i] = [i];
    }

    for (let j = 0; j <= a.length; j++) {
        matrix[0][j] = j;
    }

    for (let i = 1; i <= b.length; i++) {
        for (let j = 1; j <= a.length; j++) {
            if (b[i - 1] === a[j - 1]) {
                matrix[i][j] = matrix[i - 1][j - 1];
            } else {
                matrix[i][j] = Math.min(
                    matrix[i - 1][j - 1] + 1,
                    matrix[i][j - 1] + 1,
                    matrix[i - 1][j] + 1
                );
            }
        }
    }

    return matrix[b.length][a.length];
}

// Automatic approximate search
function approximateSearch(strings, query) {
    const scored = strings.map(str => ({
        word: str,
        score: levenshteinDistance(query, str)
    }));

    scored.sort((a, b) => a.score - b.score);

    const minDistance = scored[0].score;

    // Return all words having minimum distance
    return scored
        .filter(item => item.score === minDistance)
        .map(item => item.word);
}

// Interactive input
rl.question("Enter number of strings (N): ", (n) => {
    let strings = [];
    let count = 0;

    console.log("Enter the strings:");

    function askString() {
        if (count < n) {
            rl.question("", (input) => {
                strings.push(input.trim());
                count++;
                askString();
            });
        } else {
            rl.question("Enter search word: ", (query) => {

                const result = approximateSearch(strings, query.trim());

                console.log("\nMost similar matches:");
                console.log(result.join(", "));

                rl.close();
            });
        }
    }

    askString();
});