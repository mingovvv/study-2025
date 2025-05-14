import java.util.*;

public class Problem03_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] arr1 = new int[]{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m1 = 3;
        int k1 = 5;
        System.out.println(solution.solution(arr1, m1, k1));
    }

    static class Solution {
        public int solution(int[] players, int m, int k) {
            int answer = 0;

            int[] servers = new int[players.length];

            for(int i=0; i<players.length; i++) {
                int currentServer = 0;

                for(int j=i-k+1; j<i; j++) {
                    if(j<0) continue;
                    currentServer += servers[j];
                }

                int currentMaxUsers = (currentServer + 1) * m - 1;

                if(players[i] > currentMaxUsers) {
                    int remainUsers = players[i] - currentMaxUsers;

                    servers[i] = remainUsers % m != 0 ? remainUsers / m + 1 : remainUsers / m;

                    answer += servers[i];
                }

            }

            return answer;
        }
    }
}
