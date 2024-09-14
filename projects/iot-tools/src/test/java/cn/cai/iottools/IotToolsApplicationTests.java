package cn.cai.iottools;

import cn.cai.iottools.engine.groovy.GroovyEngine;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class IotToolsApplicationTests {


//    @Autowired
//    private GroovyScriptEngine groovyScriptEngine;

//    @Test
//    void contextLoads() {
//        try {
//            GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine("src/test/resources/groovy/");
//            groovyScriptEngine.run("init.groovy", "");
//        } catch (ResourceException e) {
//            throw new RuntimeException(e);
//        } catch (ScriptException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Test
//    void runFile() {
//        GroovyShell shell = new GroovyShell();
//        // 加载数据
//        Map<String, Script> scriptMap = new HashMap<>();
//        loadScripts("E:\\onlineDevelopment\\java_online_development\\projects\\iot-tools\\src\\main\\resources\\groovy", scriptMap);
//
//        Script script = scriptMap.get("init.groovy");
////        Object evaluate = script.evaluate("Init init = new Init(); init.main()");
//        Object run = script.invokeMethod("a", null);
//        System.out.println(run);
//
//    }

//    @Test
//    void load() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        GroovyClassLoader loader = new GroovyClassLoader();
//        loadScripts("E:\\onlineDevelopment\\java_online_development\\projects\\iot-tools\\src\\main\\resources\\groovy", loader);
//        Class<?> init = loader.parseClass("groovy.Init");
//        Object o = init.newInstance();
//        Object a = init.getMethod("run").invoke(o);
//        System.out.println(a);
//    }


    public static void loadScripts(String directoryPath, GroovyClassLoader loader) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".groovy"));
        if (files != null) {
            for (File file : files) {
                try {
                    // 读取文件内容
                    FileInputStream inputStream = new FileInputStream(file);
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes, 0, bytes.length);
                    loader.loadClass(new String(bytes));
                } catch (Exception e) {
                    // Handle exceptions, e.g., log the error
                }
            }
        }
    }

    public static void loadScripts(String directoryPath, Map<String, Script> scriptMap) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".groovy"));
        if (files != null) {
            for (File file : files) {
                try {
                    GroovyShell shell = new GroovyShell();
                    Script script = shell.parse(file);
                    scriptMap.put(file.getName(), script);
                } catch (Exception e) {
                    // Handle exceptions, e.g., log the error
                }
            }
        }
    }

}
