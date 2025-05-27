function solution(board) {
    const row = board.length;
    const col = board[0].length;
    const visit = Array.from({length: row}, () => Array(col).fill(false));
    const directions = [
        [-1,0], //상
        [1,0], // 하
        [0, -1], // 좌
        [0, 1] // 우
    ]

    let start, end;

    // 시작 위치(R)와 목표 위치(G) 찾기
    for (let i=0; i < row; i++) {
        for (let j=0; j < col; j++) {
            if (board[i][j] === 'R') start = [i,j];
            if (board[i][j] === 'G') end = [i,j];
        }
    }

    const now = [[...start, 0]];
    visit[start[0]][start[1]] = true;

    while (now.length > 0) {
        const [x, y, count] = now.shift();
        if (x === end[0] && y === end[1]) return count;

        for (const [dx, dy] of directions) {
            let nowX = x;
            let nowY = y;

            // 벽에 막힐때까지 진행
            while (true) {
                const nextX = nowX + dx;
                const nextY = nowY + dy;

                if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && board[nextX][nextY] !== 'D') {
                    nowX = nextX;
                    nowY = nextY;
                } else {
                    break;
                }
            }

            if (!visit[nowX][nowY]) {
                visit[nowX][nowY] = true;
                now.push([nowX, nowY, count + 1]);
            }
        }
    }

    return -1; // 도달할 수 없는 경우
}