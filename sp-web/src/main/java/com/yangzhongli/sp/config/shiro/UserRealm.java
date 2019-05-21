package com.yangzhongli.sp.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0){
        System.out.println("授权");
        Subject subject= SecurityUtils.getSubject();
        //WechatUser user=(WechatUser) subject.getPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("buzhidao");
        return simpleAuthorizationInfo;
    }
   @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
      /*  // TODO Auto-generated method stub
        System.out.println("认证");



        //shiro判断逻辑
        UsernamePasswordToken user = (UsernamePasswordToken) arg0;
        WechatUser realUser = new WechatUser();
        realUser.setOpen_id(user.getUsername());
//        realUser.setPassword(String.copyValueOf(user.getPassword()));
        WechatUser newUser = userService.findUser(realUser);
        //System.out.println(user.getUsername());

        if(newUser == null){
            //用户名错误
            //shiro会抛出UnknownAccountException异常
            return null;
        }
*/
       // return new SimpleAuthenticationInfo(newUser,newUser.getOpen_id(),"");
       return null;
    }

}
