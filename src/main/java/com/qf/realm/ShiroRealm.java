package com.qf.realm;

import com.qf.entity.User;
import com.qf.mapper.UserMapper;
import com.qf.mapper.UserPermissionMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        User user = userMapper.findByTelPwd(primaryPrincipal, "");
        //获取用户所有权限
        Set<String> permissions = userPermissionMapper.findAllPerms(user.getId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        /*Set<String> stringPermissions = simpleAuthorizationInfo.getStringPermissions();
        for (String stringPermission : stringPermissions) {
            System.out.println(stringPermission);
        }*/
        return simpleAuthorizationInfo;
    }
    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        Object credentials = authenticationToken.getCredentials();
        String password = new String((char[]) credentials);
        User user = userMapper.findByTelPwd(principal, password);
        //System.out.println("user认证:"+user);
        if (user!=null){
            SimpleAuthenticationInfo simpleAuthenticationInfo =
                    new SimpleAuthenticationInfo(principal, password, "");
            return simpleAuthenticationInfo;
        }
        throw new RuntimeException("错误请求");
    }
}
