import java.util.*;

public class Problem03 {
    public static void main(String[] args) {
        String[] storage1 = new String[]{"AZWQY", "CAABX", "BBDDA", "ACACA"};
        String[] requests1 = new String[]{"A", "BB", "A"};

        System.out.println(solution(storage1, requests1));

        String[] storage2 = new String[]{"HAH", "HBH", "HHH", "HAH", "HBH"};
        String[] requests2 = new String[]{"C", "B", "B", "B", "B", "H"};

        System.out.println(solution(storage2, requests2));
    }

    private static char[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};


    private static int n, m;

    public static int solution(String[] storage, String[] requests) {
        int answer = 0;

        n = storage.length;
        m = storage[0].length();

        arr = new char[n + 2][m + 2];
        visited = new boolean[n + 2][m + 2];

        for(int i=1; i<n+1; i++) {
            for(int j=1; j<m+1; j++) {
                arr[i][j] = storage[i-1].charAt(j-1);
            }
        }

        for(int i=0; i<n+2; i++) {
            visited[i][0] = true;
            visited[i][m+1] = true;
        }

        for(int i=0; i<m+2; i++) {
            visited[0][i] = true;
            visited[n+1][i] = true;
        }

        for(int i=0; i<requests.length; i++) {
            if(requests[i].length() == 1) {
                checkVisited();

                List<Integer[]> list = new ArrayList<>();

                for(int k=1; k<arr.length-1; k++) {
                    for (int l=1; l<arr[0].length-1; l++) {
                        if(arr[k][l] == requests[i].charAt(0)) {
                            for(int m=0; m<4; m++) {
                                int nx = k + dx[m];
                                int ny = l + dy[m];

                                if(visited[nx][ny]) {
                                    list.add(new Integer[]{k, l});
                                }
                            }

                        }
                    }
                }

                for(int k=0; k<list.size(); k++) {
                    arr[list.get(k)[0]][list.get(k)[1]] = '\0';
                    visited[list.get(k)[0]][list.get(k)[1]] = true;
                }
            } else {
                for(int k=0; k<arr.length; k++) {
                    for (int l=0; l<arr[0].length; l++) {
                        if(arr[k][l] == requests[i].charAt(0)) {
                            arr[k][l] = '\0';
                        }
                    }
                }
            }
        }

        for(int i=1; i<arr.length-1; i++) {
            for(int j=1; j<arr[0].length-1; j++) {
                if(arr[i][j] != '\0') answer++;
            }
        }

        return answer;
    }

    private static void checkVisited() {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[]{0, 0});
        visited = new boolean[n+2][m+2];
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Integer[] cur = queue.poll();

            for(int k=0; k<4; k++) {
                int nx = cur[0] + dx[k];
                int ny = cur[1] + dy[k];

                if(nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length) {
                    if(!visited[nx][ny] && arr[nx][ny] == '\0') {
                        queue.offer(new Integer[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

}

