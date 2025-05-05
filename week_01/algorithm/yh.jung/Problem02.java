import java.util.*;
public class Problem02 {
    public static void main(String[] args) {
        int[] arr = new int[]{100,180,360,100,270};
        System.out.println(solution(arr));
    }

    public static long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);
        Map<Double, Integer> map = new HashMap<>();

        for(int i=0; i<weights.length; i++) {
            double rate11 = weights[i] * 1.0;
            double rate23 = (weights[i] * 2.0) / 3.0;
            double rate24 = (weights[i] * 2.0) / 4.0;
            double rate34 = (weights[i] * 3.0) / 4.0;

            if(map.containsKey(rate11)) answer += map.get(rate11);
            if(map.containsKey(rate23)) answer += map.get(rate23);
            if(map.containsKey(rate24)) answer += map.get(rate24);
            if(map.containsKey(rate34)) answer += map.get(rate34);

            map.put(rate11, map.getOrDefault(rate11, 0) + 1);
        }

        return answer;
    }
}
