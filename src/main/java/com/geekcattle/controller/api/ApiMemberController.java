package com.geekcattle.controller.api;

import com.geekcattle.mapper.member.MemberMapper;
import com.geekcattle.model.member.Member;
import com.geekcattle.util.ReturnUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/member")
public class ApiMemberController {

    protected  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MemberMapper memberMapper;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelMap index(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            PrincipalCollection principals = subject.getPrincipals();
            Member member = (Member) principals.getPrimaryPrincipal();
            return ReturnUtil.Success("获取用户信息成功", member);
        }else{
            return ReturnUtil.Error("用户不存在");
        }

    }

}
