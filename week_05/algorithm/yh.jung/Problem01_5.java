package algorithm.yh.jung;

import java.util.*;

public class Problem01_5 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[][] arr = new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};

        System.out.println(solution.solution(arr));
    }

    static
    class Solution {
        public int solution(String[][] book_time) {
            int answer = 0;

            int[][] int_book_time = new int[book_time.length][book_time[0].length];

            for(int i=0; i<book_time.length; i++) {
                String[] time1 = book_time[i][0].split(":");
                String[] time2 = book_time[i][1].split(":");

                int_book_time[i][0] = Integer.parseInt(time1[0]) * 60 + Integer.parseInt(time1[1]);
                int_book_time[i][1] = Integer.parseInt(time2[0]) * 60 + Integer.parseInt(time2[1]) + 10;
            }

            Arrays.sort(int_book_time, (o1, o2) -> {
                if(o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            });

            int[] room = new int[1];
            room[0] = int_book_time[0][1];

            for(int i=1; i<book_time.length; i++) {

                int availableRoomIndex = -1;

                for(int j=0; j<room.length; j++) {
                    if(int_book_time[i][0] >= room[j]) {
                        availableRoomIndex = j;
                        break;
                    }
                }

                if(availableRoomIndex == -1) {
                    room = Arrays.copyOf(room, room.length + 1);
                    room[room.length - 1] = int_book_time[i][1];
                } else {
                    room[availableRoomIndex] = int_book_time[i][1];
                }

            }


            return room.length;
        }
    }

}
