package singleton;

public class SingletonWithImprovedDCL {
    private volatile static SingletonWithImprovedDCL instance;

    private SingletonWithImprovedDCL() {}
    private static SingletonWithImprovedDCL getInstance() {
        SingletonWithImprovedDCL instanceRef = instance;
        if (instanceRef == null) {
            synchronized (SingletonWithDCL.class) {
                instanceRef = instance;
                if (instanceRef == null) {
                    instanceRef = new SingletonWithImprovedDCL();
                }
            }
        }
        return instanceRef;
    }
}
