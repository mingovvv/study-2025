package algorithm.yh.jung;

import java.util.*;
import java.util.stream.Collectors;

public class Problem01 {
    public static void main(String[] args) {
        /*
        * - i/n == 행
        * - i%n == 열
        *
        * 1 2 3  2 2 3  3 3 3
        * 0 1 2  3 4 5  6 7 8    => max(row, col) + 1
        */

        Solution solution = new Solution();

        System.out.println(solution.solution(3, 2, 5).stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }

    static class Solution {
        public List<Long> solution(int n, long left, long right) {
            List<Long> list = new ArrayList<>();

            for(long i=left; i<right+1; i++) {
                list.add(Math.max(i/n, i%n) + 1);
            }

            return list;
        }
    }

}
