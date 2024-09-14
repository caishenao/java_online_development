package cn.cai.iottools.engine.groovy;

import cn.cai.iottools.engine.Engine;
import groovy.lang.GroovyClassLoader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * groovy 引擎
 */
@Component
@AllArgsConstructor
public class GroovyEngine implements Engine {


    private GroovyClassLoader groovyClassLoader;

    /**
     * 加载库文件脚本
     *
     * @param script 要加载脚本的内容
     */
    @Override
    public void load(String script) {

    }

    /**
     * 执行脚本
     *
     * @param script 要执行的脚本
     */
    @Override
    public void run(String script) {
        Class aClass = groovyClassLoader.parseClass(script);
        try {
            Constructor declaredConstructor = aClass.getDeclaredConstructor();
            Object o = declaredConstructor.newInstance();
            Object a = aClass.getMethod("a").invoke(o);
            System.out.println(a);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
