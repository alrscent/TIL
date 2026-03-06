package singleton;

public class SingletonForThreadSafe {
    private static SingletonForThreadSafe instance;
    private SingletonForThreadSafe() {}

    public synchronized static SingletonForThreadSafe getInstance() {
        if (instance == null) {
            instance = new SingletonForThreadSafe();
        }
        return instance;
    }
}
