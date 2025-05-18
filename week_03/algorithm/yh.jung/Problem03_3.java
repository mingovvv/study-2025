package algorithm.yh.jung;

import java.util.*;

public class Problem03_3 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] info = new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
//        String[] info = new String[]{"java backend junior pizza 150"};
        String[] query = new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        System.out.println(Arrays.toString(solution.solution(info, query)));
    }

    static public class Solution {
        static Map<String, List<Integer>> map = new HashMap<>();

        public int[] solution(String[] info, String[] query) {
            for (String inf : info) {
                String[] split = inf.split(" ");
                String[] conditions = Arrays.copyOf(split, 4);
                int score = Integer.parseInt(split[4]);

                generateKeys(conditions, "", 0, score); // 16개 조합 생성
            }

            // 각 리스트 정렬 (이분 탐색용)
            for (List<Integer> scores : map.values()) {
                Collections.sort(scores);
            }

            int[] answer = new int[query.length];
            for (int i = 0; i < query.length; i++) {
                String q = query[i].replaceAll(" and ", ""); // "and" 제거
                String[] split = q.split(" ");
                String key = split[0];
                int targetScore = Integer.parseInt(split[1]);

                List<Integer> scoreList = map.getOrDefault(key, new ArrayList<>());
                int idx = lowerBound(scoreList, targetScore);
                answer[i] = scoreList.size() - idx;
            }

            return answer;
        }

        // 가능한 모든 조합 key를 만들어서 map에 추가
        private void generateKeys(String[] conditions, String current, int depth, int score) {
            if (depth == 4) {
                map.computeIfAbsent(current, k -> new ArrayList<>()).add(score);
                return;
            }

            // 해당 조건 포함
            generateKeys(conditions, current + conditions[depth], depth + 1, score);
            // '-'로 대체-
            generateKeys(conditions, current + "-", depth + 1, score);
        }

        // 이분 탐색 (target 이상인 첫 번째 인덱스)
        private int lowerBound(List<Integer> list, int target) {
            int left = 0, right = list.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (list.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }

    /*static class Solution {
        public int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            List<ApplicantInfo>[] arr = new ArrayList[100000];

            for(int i=0; i<info.length; i++) {
                String[] split = info[i].split(" ");
                if(arr[Integer.parseInt(split[4])] == null) {
                    arr[Integer.parseInt(split[4])] = new ArrayList();
                }
                arr[Integer.parseInt(split[4])].add(new ApplicantInfo(info[i]));
            }

            for(int i=0; i<query.length; i++) {
                query[i] = query[i].replaceAll(" and ", " ");
                String[] split = query[i].split(" ");

                ApplicantInfo value = new ApplicantInfo(query[i]);
                int score = Integer.parseInt(split[4]);
                int count = 0;

                for(int k=score; k<100000; k++) {
                    List<ApplicantInfo> list = arr[k];
                    if(list == null) continue;

                    for(int j=0; j<list.size(); j++) {
                        if(list.get(j).equals(value)) count++;
                    }
                }

                answer[i] = count;
            }


            return answer;
        }

        public static class ApplicantInfo {
            public String languageType; // cpp, java, python
            public String job;
            public String career;
            public String food;

            public ApplicantInfo(String infoLine) {
                String[] split = infoLine.split(" ");
                this.languageType = split[0];
                this.job = split[1];
                this.career = split[2];
                this.food = split[3];
            }

            @Override
            public boolean equals(Object obj) {
                ApplicantInfo other = (ApplicantInfo) obj;

                if(!other.languageType.equals("-") && !this.languageType.equals(other.languageType)) return false;
                if(!other.job.equals("-") && !this.job.equals(other.job)) return false;
                if(!other.career.equals("-") && !this.career.equals(other.career)) return false;
                if(!other.food.equals("-") && !this.food.equals(other.food)) return false;

                return true;
            }

            @Override
            public int hashCode() {
                return Objects.hash(languageType, job, career, food);
            }
        }
    }*/

    /*static class Solution {
        public int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            List<ApplicantInfo> applicants = new ArrayList<>();
            for(int i=0; i<info.length; i++) {
                applicants.add(new ApplicantInfo(info[i]));
            }

            for(int i=0; i<query.length; i++) {
                int count = 0;

                for(int j=0; j<applicants.size(); j++) {
                    // System.out.println(applicants.get(j).toString() + ", query: " + query[i] + ", " + applicants.get(j).isGood(query[i]));

                    if(applicants.get(j).isGood(query[i])) count++;
                }

                answer[i] = count;
            }


            return answer;
        }

        public static class ApplicantInfo {
            public String languageType; // cpp, java, python
            public String job;
            public String career;
            public String food;
            public Integer score;

            public ApplicantInfo(String infoLine) {
                String[] split = infoLine.split(" ");
                this.languageType = split[0];
                this.job = split[1];
                this.career = split[2];
                this.food = split[3];
                this.score = Integer.parseInt(split[4]);
            }

            public boolean isGood(String query) {
                query = query.replaceAll(" and ", " ");

                String[] split = query.split(" ");  //정보 5개 + and 3개

                if(!split[0].equals("-") && !this.languageType.equals(split[0])) return false;
                if(!split[1].equals("-") && !this.job.equals(split[1])) return false;
                if(!split[2].equals("-") && !this.career.equals(split[2])) return false;
                if(!split[3].equals("-") && !this.food.equals(split[3])) return false;
                if(!split[4].equals("-") && this.score < Integer.parseInt(split[4])) return false;

                return true;
            }




            //         public String toString() {
//             return this.languageType + ", " + this.job + ", " + this.career + ", " + this.food + ", " + this.score;
//         }
        }
    }*/
}
