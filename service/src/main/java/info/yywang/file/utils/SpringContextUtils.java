package info.yywang.file.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author yanyan.wang
 * @date 2016-09-08 19:39
 */
public final class SpringContextUtils implements ApplicationContextAware {
    /**
     * 上下文
     */
    private static ApplicationContext ctu;

    /**
     * 私有构造函数
     */
    private SpringContextUtils() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctu = applicationContext;
    }

    /**
     * 获得上下文
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return ctu;
    }

    public static Object getBean(String name) {
        return ctu.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return ctu.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return ctu.getBean(name, clazz);
    }
}


