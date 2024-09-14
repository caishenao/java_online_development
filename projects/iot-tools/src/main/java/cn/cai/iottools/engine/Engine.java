package cn.cai.iottools.engine;

/**
 * 引擎接口
 * @author cai
 */
public interface Engine {

    /**
     * 加载库文件脚本
     */
    void load(String script);

    /**
     * 执行脚本
     * @param script 要执行的脚本
     */
    void run(String script);

}
