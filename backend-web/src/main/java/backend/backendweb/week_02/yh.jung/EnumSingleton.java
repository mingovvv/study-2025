package backend.backendweb.week_02.yh.jung;

public enum EnumSingleton {
    INSTANCE; // 싱글톤 인스턴스

    private int currentId = 0;

    public int getCurrentId() {
        return currentId;
    }

    public long getNextId() {
        return ++currentId;
    }
}
