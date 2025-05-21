function solution(n, left, right) {
    const answer = [];

    for (let i = left; i <= right; i++) {
        const row = Math.floor(i / n)
        const col = i % n

        const num = Math.max(row,col) + 1;
        answer.push(num);
    }

    return answer;
}