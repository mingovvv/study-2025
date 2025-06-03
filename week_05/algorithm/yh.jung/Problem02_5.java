package algorithm.yh.jung;

import java.util.*;
import java.util.stream.*;

public class Problem02_5 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 7;
        int k = 3;
        int[] arr = new int[]{4, 2, 4, 5, 3, 3, 1};


        System.out.println(solution.solution(n, k, arr));
    }

    static class Solution {
        public int solution(int n, int k, int[] enemy) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

            for(int i=0; i<enemy.length; i++) {
                n -= enemy[i];
                queue.offer(enemy[i]);

                if(n < 0) {
                    if(k == 0) return i;
                    n += queue.poll();
                    k--;
                }
            }

            return enemy.length;
        }
    }

}
