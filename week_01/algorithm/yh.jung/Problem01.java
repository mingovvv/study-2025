import java.util.*;

public class Problem01 {
    public static void main(String[] args) {
        int k1 = 6;
        int[] arr1 = new int[]{1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution(k1, arr1)); // 3

        int k2 = 4;
        int[] arr2 = new int[]{1, 3, 2, 5, 4, 5, 2, 3};
        System.out.println(solution(k2, arr2)); // 2

        int k3 = 2;
        int[] arr3 = new int[]{1, 1, 1, 1, 2, 2, 2, 3};
        System.out.println(solution(k3, arr3)); // 1
    }

    public static int solution(int k, int[] tangerine) {
        int answer = 0;
        Map<Integer, Integer> countMap = new HashMap<>();

        for(int i=0; i<tangerine.length; i++) {
            countMap.put(tangerine[i], countMap.getOrDefault(tangerine[i], 0) + 1);;
        }

        List<Integer> countKeyList = new ArrayList<>(countMap.keySet());
        Collections.sort(countKeyList, (o1, o2) -> countMap.get(o2).compareTo(countMap.get(o1)));

        int beforeTangerine = 0;
        for(int i=0; i<countKeyList.size() && 0<k; i++) {
            int count = countMap.get(countKeyList.get(i));

            if(beforeTangerine != countKeyList.get(i)) answer++;

            k -= count;
            beforeTangerine = countKeyList.get(i);
        }

        return answer;
    }
}

