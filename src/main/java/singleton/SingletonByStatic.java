package singleton;

public class SingletonByStatic {
    private static final SingletonByStatic instance = new SingletonByStatic();

    private SingletonByStatic() {}
}
