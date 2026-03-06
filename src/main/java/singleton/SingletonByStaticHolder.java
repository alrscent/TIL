package singleton;

public class SingletonByStaticHolder {
    private SingletonByStaticHolder() {}
    private static class Holder {
        private static final SingletonByStaticHolder instance = new SingletonByStaticHolder();
    }

    public static SingletonByStaticHolder getInstance() {
        return Holder.instance;
    }
}
