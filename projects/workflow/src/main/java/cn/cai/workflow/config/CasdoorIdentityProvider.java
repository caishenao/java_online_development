package cn.cai.workflow.config;

import cn.cai.web.comment.utils.BeanUtils;
import jakarta.annotation.Resource;
import org.camunda.bpm.engine.identity.*;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.casbin.casdoor.service.UserService;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 使用casdoor对用户和用户组进行重写
 */
public class CasdoorIdentityProvider implements ReadOnlyIdentityProvider {

    private UserService userService;

    public CasdoorIdentityProvider() {
        super();
        this.userService = BeanUtils.getBean(UserService.class);
    }

    /**
     * @param userId
     * @return a {@link User} object for the given user id or null if no such user exists.
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public User findUserById(String userId) {
        org.casbin.casdoor.entity.User user = null;
        try {
             user = userService.getUser(userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User findUser = new UserEntity();
        if (user != null) {
            findUser.setEmail(user.email);
            findUser.setId(user.id);
            findUser.setPassword(user.password);
            findUser.setFirstName(user.firstName);
            findUser.setLastName(user.lastName);
        }
        return findUser;
    }

    /**
     * @return a {@link UserQuery} object which can be used for querying for users.
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public UserQuery createUserQuery() {

        return null;
    }

    /**
     * @param commandContext
     * @return a {@link UserQuery} object which can be used in the current command context
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public UserQuery createUserQuery(CommandContext commandContext) {
        return null;
    }

    /**
     * Creates a {@link NativeUserQuery} that allows to select users with native queries.
     *
     * @return NativeUserQuery
     */
    @Override
    public NativeUserQuery createNativeUserQuery() {
        return null;
    }

    /**
     * @param userId
     * @param password
     * @return 'true' if the password matches the
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public boolean checkPassword(String userId, String password) {
        return false;
    }

    /**
     * @param groupId
     * @return a {@link Group} object for the given group id or null if no such group exists.
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public Group findGroupById(String groupId) {
        return null;
    }

    /**
     * @return a {@link GroupQuery} object which can be used for querying for groups.
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public GroupQuery createGroupQuery() {
        return null;
    }

    /**
     * @param commandContext
     * @return a {@link GroupQuery} object which can be used for querying for groups and can be reused in the current command context.
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public GroupQuery createGroupQuery(CommandContext commandContext) {
        return null;
    }

    /**
     * @param tenantId
     * @return a {@link Tenant} object for the given id or null if no such tenant
     * exists.
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public Tenant findTenantById(String tenantId) {
        return null;
    }

    /**
     * @return a {@link TenantQuery} object which can be used for querying for
     * tenants.
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public TenantQuery createTenantQuery() {
        return null;
    }

    /**
     * @param commandContext
     * @return a {@link TenantQuery} object which can be used for querying for
     * tenants and can be reused in the current command context.
     * @throws IdentityProviderException in case an error occurs
     */
    @Override
    public TenantQuery createTenantQuery(CommandContext commandContext) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() {

    }

    public long findUserCountByQueryCriteria(CasdoorUserQuery casdoorUserQuery) {

        return 0;
    }
}
