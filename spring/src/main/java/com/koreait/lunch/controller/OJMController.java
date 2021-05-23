package com.koreait.lunch.controller;

import com.koreait.lunch.controller.MyUtils;
import com.koreait.lunch.model.dao.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class OJMController {

    @Autowired
    BoardDAO boardDAO;

    @RequestMapping(value = "/ojm", method = RequestMethod.GET)
    public String ojm(Model model, HttpSession session) {
        model.addAttribute("list", boardDAO.getAllBoard());
        MyUtils.setTemplate(model, "오늘 점심은 뭐먹지?", "/ojm", session);
        return MyUtils.TEMPLATE;
    }
}
