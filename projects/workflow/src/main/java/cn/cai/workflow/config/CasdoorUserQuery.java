package cn.cai.workflow.config;

import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.UserQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import java.util.Collections;
import java.util.List;

public class CasdoorUserQuery extends UserQueryImpl {
    /**
     *TODO
     * @param commandContext
     * @return
     */
    @Override
    public long executeCount(CommandContext commandContext) {
        checkQueryOk();
        final CasdoorIdentityProvider identityProvider = getIdentityProvider(commandContext);
        return identityProvider.findUserCountByQueryCriteria(this);
    }

    /**
     * TODO
     * Executes the actual query to retrieve the list of results.
     *
     * @param commandContext
     * @param page           used if the results must be paged. If null, no paging will be applied.
     */
    @Override
    public List<User> executeList(CommandContext commandContext, Page page) {
        return Collections.emptyList();
    }

    /**
     * 获取provider
     * @param commandContext
     * @return
     */
    private CasdoorIdentityProvider getIdentityProvider(CommandContext commandContext){
        return (CasdoorIdentityProvider) commandContext.getProcessEngineConfiguration().getIdentityService();
    }
}
