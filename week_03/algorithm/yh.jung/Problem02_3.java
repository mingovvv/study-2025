package algorithm.yh.jung;

import java.util.Arrays;

public class Problem02_3 {
    public static void main(String[] args) {
        /*
         * 전체 탐색 -> dfs, bfs
         * 맨해튼 거리가 2 이하 X (|x1-x2| + |y1-y2|)
         * 사람 찾아서
         * 현재가 사람일 때, 상하좌우가 테이블이라면, 그 상하좌우에는 사람이 있으면 안됨
         */


        Solution solution = new Solution();

        String[][] arr = new String[][]
                {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {
        "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {
        "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        System.out.println(Arrays.toString(solution.solution(arr)));
    }

    static class Solution {
        private static final int ARRAY_SIZE = 5;
        private static int[] dx = new int[]{1, -1, 0, 0};
        private static int[] dy = new int[]{0, 0, 1, -1};

        public int[] solution(String[][] places) {
            int[] answer = new int[ARRAY_SIZE];

            for(int i=0; i<places.length; i++) {
                char[][] arr = new char[ARRAY_SIZE][ARRAY_SIZE];
                boolean[][] visited = new boolean[ARRAY_SIZE][ARRAY_SIZE];

                for(int k=0; k<ARRAY_SIZE; k++) {
                    for(int l=0; l<ARRAY_SIZE; l++) {
                        char c = places[i][k].charAt(l);

                        arr[k][l] = c;
                    }
                }


                answer[i] = isGood(arr, visited);
            }


            return answer;
        }

        private int isGood(char[][] arr, boolean[][] visited) {
            for(int i=0; i<ARRAY_SIZE; i++) {
                for(int j=0; j<ARRAY_SIZE; j++) {
                    if(arr[i][j] == 'P') {
                        visited[i][j] = true;
                        for(int k=0; k<4; k++) {
                            int nx = j + dx[k];
                            int ny = i + dy[k];

                            if(nx >= 0 && ny >= 0 && nx < ARRAY_SIZE && ny < ARRAY_SIZE) {
                                visited[ny][nx] = true;
                                if(arr[ny][nx] == 'P')
                                    return 0;
                                if(arr[ny][nx] == 'O') {
                                    for(int l=0; l<4; l++) {
                                        int nxx = nx + dx[l];
                                        int nyy = ny + dy[l];

                                        if(nxx == nx && nyy == ny) continue;

                                        if(nxx >= 0 && nyy >= 0 && nxx < ARRAY_SIZE && nyy < ARRAY_SIZE) {
                                            if(arr[nyy][nxx] == 'P' && !visited[nyy][nxx])
                                                return 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return 1;
        }
    }
}
