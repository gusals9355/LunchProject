package com.koreait.lunch.controller;

import com.koreait.lunch.model.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    @Autowired
    MemberDAO memberDAO;

    @RequestMapping(value = "/user/login", )
    public String loginForm(Model model){

    }
}
