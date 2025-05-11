import java.util.*;

public class Problem01_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[][] arr4 = new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "23:59", "30"}, {"computer", "12:30", "100"}};
        System.out.println(Arrays.toString(solution.solution(arr4)));

        String[][] arr1 = new String[][]{{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        System.out.println(Arrays.toString(solution.solution(arr1)));

        String[][] arr2 = new String[][]{{"science", "12:40", "50"},  {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        System.out.println(Arrays.toString(solution.solution(arr2)));

        String[][] arr3 = new String[][]{{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}};
        System.out.println(Arrays.toString(solution.solution(arr3)));


    }
}

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int answerIndex = 0;

        Arrays.sort(plans, (o1, o2) -> compareTime(o1[1], o2[1]));

        int index = 1;

        Stack<String[]> stack = new Stack<>();
        stack.push(plans[0]);

        while(index < plans.length) {
            String[] latestPlan = stack.pop();

            String latestPlanEndTime = plusTime(latestPlan[1], latestPlan[2]);

            if(compareTime(plans[index][1], latestPlanEndTime) >= 0) {
                answer[answerIndex++] = latestPlan[0];

                int remainTime = minusTime(plans[index][1], latestPlanEndTime);

                //남은 시간동안 과제 진행
                if(remainTime > 0) {
                    if(compareTime(plans[index][1], latestPlanEndTime) > 0) {
                        while(!stack.isEmpty()) {
                            String[] latestRemainPlan = stack.pop();
                            int latestPlayTime = Integer.parseInt(latestRemainPlan[2]);

                            if(remainTime >= latestPlayTime) {
                                remainTime -= latestPlayTime;
                                answer[answerIndex++] = latestRemainPlan[0];
                            } else {
                                stack.push(new String[]{latestRemainPlan[0], latestRemainPlan[1], String.valueOf(latestPlayTime - remainTime)});
                                break;
                            }
                        }
                    }
                }

            } else {
                int remainTime = Integer.parseInt(latestPlan[2]) - minusTime(plans[index][1], latestPlan[1]);

                if(remainTime > 0) {
                    stack.push(new String[]{latestPlan[0], latestPlan[1], String.valueOf(remainTime)});
                } else {
                    answer[answerIndex++] = latestPlan[0];
                }
            }

            stack.push(plans[index++]);

        }

        while(!stack.isEmpty()) {
            answer[answerIndex++] = stack.pop()[0];
        }


        return answer;
    }

    private String plusTime(String time, String playTime) {
        String[] split = time.split(":");

        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        minute += Integer.parseInt(playTime);

        hour += minute/60;
        minute %= 60;

        return hour + ":" + minute;
    }

    private int minusTime(String time1, String time2) {
        //time1 > time2
        String[] split1 = time1.split(":");
        String[] split2 = time2.split(":");

        int hour1 = Integer.parseInt(split1[0]);
        int minute1 = Integer.parseInt(split1[1]);

        int hour2 = Integer.parseInt(split2[0]);
        int minute2 = Integer.parseInt(split2[1]);

        int hour, minute;

        if(minute2 > minute1) {
            hour1--;
            minute = 60 + (minute1 - minute2);
        } else {
            minute = minute1 - minute2;
        }

        hour = hour1 - hour2;

        return hour * 60 + minute;
    }

    private int compareTime(String time1, String time2) {
        String[] split1 = time1.split(":");
        String[] split2 = time2.split(":");

        int hour1 = Integer.parseInt(split1[0]);
        int hour2 = Integer.parseInt(split2[0]);
        int minute1 = Integer.parseInt(split1[1]);
        int minute2 = Integer.parseInt(split2[1]);

        if(hour1 > hour2) {
            return 1;
        } else if(hour1 < hour2) {
            return -1;
        } else {
            if(minute1 > minute2) {
                return 1;
            } else if(minute1 == minute2) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}