package cn.cai.workflow.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.camunda.bpm.engine.rest.mapper.JacksonConfigurator;

/**
 * 自定义返参日期监听器
 */
public class JacksonDateFormatListener implements ServletContextListener {

    /**
     * 设置日期格式
     *
     * @param sce Information about the ServletContext that was initialized
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JacksonConfigurator.setDateFormatString("YYYY-MM-dd HH:mm:ss");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JacksonConfigurator.setDateFormatString("YYYY-MM-dd HH:mm:ss");
    }
}
