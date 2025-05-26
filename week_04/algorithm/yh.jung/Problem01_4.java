package algorithm.yh.jung;

import java.util.*;

public class Problem01_4 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] arr = new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};

        System.out.println(solution.solution(arr));
    }

    static class Solution {
        private char[][] arr;
        private int[] start = new int[2];
        private int[] end = new int[2];
        private boolean[][] visited;

        public int solution(String[] board) {
            int answer = 0;

            int height = board.length;
            int width = board[0].length();

            arr = new char[height][width];
            visited = new boolean[height][width];

            for(int i=0; i<board.length; i++) {
                for(int j=0; j<board[i].length(); j++) {
                    char c = board[i].charAt(j);

                    if(c == 'R') {
                        start[0] = j;
                        start[1] = i;
                    } else if(c == 'G') {
                        end[0] = j;
                        end[1] = i;
                    }

                    arr[i][j] = c;
                }
            }


            return bfs();
        }

        private int bfs() {
            Queue<Integer[]> queue = new LinkedList<>();
            queue.offer(new Integer[]{start[0], start[1], 0});
            visited[start[1]][start[0]] = true;

            while(!queue.isEmpty()) {
                Integer[] target = queue.poll();

                int targetX = target[0];
                int targetY = target[1];
                int targetCount = target[2];

                if(targetY == end[1] && targetX == end[0]) return targetCount;

                visited[targetY][targetX] = true;

                int tempX, tempY;

                //상
                tempX = targetX;
                tempY = targetY;

                while (tempY > 0) {
                    if(arr[tempY-1][tempX] == 'D') break;
                    else tempY--;
                }

                if(!visited[tempY][tempX]) queue.offer(new Integer[]{tempX, tempY, targetCount + 1});

                //하
                tempX = targetX;
                tempY = targetY;

                while(arr.length - 1 > tempY) {
                    if(arr[tempY+1][tempX] == 'D') break;
                    else tempY++;
                }

                if(!visited[tempY][tempX]) queue.offer(new Integer[]{tempX, tempY, targetCount + 1});

                //좌
                tempX = targetX;
                tempY = targetY;

                while(tempX > 0) {
                    if(arr[tempY][tempX-1] == 'D') break;
                    else tempX--;
                }

                if(!visited[tempY][tempX]) queue.offer(new Integer[]{tempX, tempY, targetCount + 1});

                //우
                tempX = targetX;
                tempY = targetY;

                while(arr[0].length - 1 > tempX) {
                    if(arr[tempY][tempX+1] == 'D') break;
                    else tempX++;
                }

                if(!visited[tempY][tempX]) queue.offer(new Integer[]{tempX, tempY, targetCount + 1});
            }

            return -1;
        }
    }

}
