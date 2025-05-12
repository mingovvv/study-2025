import java.util.*;

public class Problem02_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] arr3 = new String[]{"SOXOL", "OXXOX", "OOOOX", "XOXXO", "EOOOO"};
        System.out.println(new Solution().solution(arr3));

        String[] arr1 = new String[]{"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        System.out.println(solution.solution(arr1));

        String[] arr2 = new String[]{"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"};
        System.out.println(solution.solution(arr2));
    }

    static class Solution {
        private static int height, width;
        private static char[][] arr;
        private static boolean[][] visited;
        private static int[] dx = {1, -1, 0, 0};
        private static int[] dy = {0, 0, 1, -1};

        private static int[] start = new int[2];
        private static int[] lever = new int[2];
        private static int[] end = new int[2];

        public int solution(String[] maps) {
            height = maps.length;
            width = maps[0].length();

            arr = new char[height][width];

            for(int i=0; i<height; i++) {
                for(int j=0; j<width; j++) {
                    char c = maps[i].charAt(j);

                    if(c == 'S') start = new int[]{i, j};
                    if(c == 'L') lever = new int[]{i, j};
                    if(c == 'E') end = new int[]{i, j};

                    arr[i][j] = c;
                }
            }


            int leverCount = bfs(start[0], start[1], lever[0], lever[1]);
            if(leverCount == -1) return -1;

            int endCount = bfs(lever[0], lever[1], end[0], end[1]);
            if(endCount == -1) return -1;

            return leverCount + endCount;
        }

        private int bfs(int y, int x, int targetY, int targetX) {
            Queue<Integer[]> queue = new LinkedList<>();
            queue.offer(new Integer[]{y, x, 0});

            visited = new boolean[height][width];
            visited[y][x] = true;

            while(!queue.isEmpty()) {
                Integer[] target = queue.poll();

                if(target[0] == targetY && target[1] == targetX) return target[2];

                for(int i=0; i<4; i++) {
                    int nx = target[1] + dx[i];
                    int ny = target[0] + dy[i];

                    if(nx > -1 && ny > -1 && nx < width && ny < height) {
                        if(arr[ny][nx] != 'X' && !visited[ny][nx]) {
                            queue.offer(new Integer[]{ny, nx, target[2]+1});
                            visited[ny][nx] = true;
                        }
                    }
                }
            }

            return -1;
        }
    }
}


