package com.yegol.museum.portal.controller;

import com.yegol.museum.portal.service.IUserService;
import com.yegol.museum.portal.service.ServiceException;
import com.yegol.museum.portal.vo.R;
import com.yegol.museum.portal.vo.RegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class SystemController {
    @Autowired
    private IUserService userService;
    @GetMapping("/login.html")
    public ModelAndView loginForm(){
        return new ModelAndView("login");
    }

    @GetMapping("/register.html")
    public ModelAndView register(){
        return new ModelAndView("register");
    }
    //注册学生的控制器方法
//因为页面使用了post的方法提交所以使用PostMapping
    @PostMapping("/register")
    public R registerStudent(
            //当一个实体类前加了@Validated注解
            //表示这个实体类的内容要被SpringValidation验证
            //验证完毕之后,会生成一个BindingResult类型的对象
            //这个对象中保存着验证的结果信息
            @Validated RegisterVo registerVo,
            BindingResult result){
        log.debug("获得的注册信息为:{}",registerVo);
        //判断result验证结果中有没有错误
        if(result.hasErrors()){
            //如果验证结果有错误
            //获得这个错误
            String error=result.getFieldError()
                    .getDefaultMessage();
            //利用R类返回错误信息给页面
            return R.unproecsableEntity(error);
        }
        if(!registerVo.getPassword().equals(registerVo.getConfirm())){
            return R.unproecsableEntity("两次密码输入不一致");
        }
        try{
            userService.registerStudent(registerVo);
            return R.created("注册完成");
        }catch(ServiceException e){
            log.error("注册失败",e);
            return R.failed(e);
        }
    }
}
