import java.util.List;

public class DataPreprocessor {
    public void preprocess(List<String> data) {
        System.out.println("[전처리] 데이터 정렬 중...");
        data.sort(String::compareTo);

        System.out.println("[전처리] 이상치 제거 중...");
        data.removeIf(d -> d.contains("N/A"));
    }
}