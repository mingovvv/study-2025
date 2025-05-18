function solution(plans) {
    const planList = plans.map(([name, start, playtime]) => {
        const [h, m] = start.split(':').map(Number);
        return {
            name,
            start: h * 60 + m,
            remaining: Number(playtime)
        };
    }).sort((a, b) => a.start - b.start);

    const result = [];
    const stack = [];

    for (let i = 0; i < planList.length; i++) {
        const current = planList[i];
        const next = i + 1 < planList.length ? planList[i + 1] : null;

        let timeLeft = next ? next.start - current.start : Infinity;

        if (current.remaining <= timeLeft) {
            // 과제 완료 가능
            result.push(current.name);
            timeLeft -= current.remaining;

            // 멈춘 과제 이어서 하기
            while (timeLeft > 0 && stack.length > 0) {
                const paused = stack.pop();
                if (paused.remaining <= timeLeft) {
                    result.push(paused.name);
                    timeLeft -= paused.remaining;
                } else {
                    paused.remaining -= timeLeft;
                    stack.push(paused);
                    break;
                }
            }
        } else {
            // 다음 과제가 시작되기 전에 끝낼 수 없음
            current.remaining -= timeLeft;
            stack.push(current);
        }
    }

    // 남아 있는 스택 마저 처리
    while (stack.length > 0) {
        result.push(stack.pop().name);
    }

    return result;
}