function solution(book_time) {
    const toMinutes = (time) => {
        const [hh, mm] = time.split(":").map(Number);
        return hh * 60 + mm;
    };

    // 예약을 시작 시간 기준으로 정렬
    book_time.sort((a, b) => toMinutes(a[0]) - toMinutes(b[0]));

    const rooms = []; // 각 방의 종료 시각을 저장

    for (let [start, end] of book_time) {
        const startMin = toMinutes(start);
        const endMin = toMinutes(end) + 10; // 퇴실 후 청소시간 10분 추가

        // 종료된 방 중 청소까지 끝난 방이 있다면 재사용
        let reused = false;
        for (let i = 0; i < rooms.length; i++) {
            if (rooms[i] <= startMin) {
                rooms[i] = endMin;
                reused = true;
                break;
            }
        }

        // 재사용 가능한 방이 없다면 새 방 추가
        if (!reused) {
            rooms.push(endMin);
        }
    }

    return rooms.length;
}