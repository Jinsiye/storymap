package cn.edu.nju.software.storymapping.system.support;

import cn.edu.nju.software.storymapping.system.entity.MyUserDetails;
import cn.edu.nju.software.storymapping.system.entity.User;
import cn.edu.nju.software.storymapping.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userService.queryByUsername(username);
         if (user==null)
             return null;

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        MyUserDetails userDetails = new MyUserDetails(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
        return userDetails;
    }
}
