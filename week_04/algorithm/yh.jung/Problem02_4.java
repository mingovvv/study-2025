package algorithm.yh.jung;

import java.util.Arrays;

public class Problem02_4 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] plans1 = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        System.out.println(Arrays.toString(solution.solution(6, 6, plans1)));

        int[][] plans2 = {{1,1,100,97}};
        System.out.println(Arrays.toString(solution.solution(100, 97, plans2)));

        int[][] plans3 = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
        System.out.println(Arrays.toString(solution.solution(3, 3, plans3)));
    }
    static class Solution {
        public int[] solution(int rows, int columns, int[][] queries) {
            int[] answer = new int[queries.length];

            int[][] arr = new int[rows][columns];

            for(int i=0; i<rows; i++) {
                for(int j=0; j<columns; j++) {
                    arr[i][j] = (i*columns) + (j + 1);
                }
            }

            for(int l=0; l<queries.length; l++) {
                int min = Integer.MAX_VALUE;

                int x1 = queries[l][1] - 1;
                int y1 = queries[l][0] - 1;

                int x2 = queries[l][3] - 1;
                int y2 = queries[l][2] - 1;

                int prev = arr[y1+1][x1];

                //우
                for(int i=x1; i<=x2; i++) {
                    int temp = arr[y1][i];
                    arr[y1][i] = prev;
                    prev = temp;

                    min = Math.min(arr[y1][i], min);
                }

                //하
                for(int i=y1+1; i<=y2; i++) {
                    int temp = arr[i][x2];
                    arr[i][x2] = prev;
                    prev = temp;

                    min = Math.min(arr[i][x2], min);
                }

                //좌
                for(int i=x2-1; i>=x1; i--) {
                    int temp = arr[y2][i];
                    arr[y2][i] = prev;
                    prev = temp;

                    min = Math.min(arr[y2][i], min);
                }

                //상
                for(int i=y2-1; i>=y1+1; i--) {
                    int temp = arr[i][x1];
                    arr[i][x1] = prev;
                    prev = temp;

                    min = Math.min(arr[i][x1], min);
                }

                answer[l] = min;
            }

            return answer;
        }
    }
}
