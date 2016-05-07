package rx.plugins;

public class RxJavaResettablePlugins extends RxJavaPlugins {

    RxJavaResettablePlugins() {
        super();
    }

    public static void resetPlugins() {
        RxJavaPlugins.getInstance().reset();
    }
}
