package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.MemberDAO;
import com.koreait.lunchproject1.model.vo.MemberVO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    MemberDAO memberDAO;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session){
        if(MyUtils.getLoginUser(session) != null){
            return MyUtils.REDIRECTPAGE("/ojm");
        }
        MyUtils.setTemplate(model,"로그인 | 오점뭐?","login",session);
        return MyUtils.TEMPLATE;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginF(Model model, HttpSession session, MemberVO vo){

        String msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
        String hashedPw = memberDAO.getHashedPw(vo);
        if(hashedPw.equals("") || !BCrypt.checkpw(vo.getPw(), hashedPw)) { //비번 틀릴경우
            model.addAttribute("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
            return MyUtils.REDIRECTPAGE("login");
        }
        if(BCrypt.checkpw(vo.getPw(), hashedPw)) { // 로그인 성공
            //로그인 성공 시 포인트를 획득함
            //로그인 포인트는 하루에 한번만 받을 수 있음
            vo.setLog("로그인");
            vo.setLoginPoint(100);
            memberDAO.log(vo); //로그인시 로그들을 db에 저장
            memberDAO.logCheck(vo); //하루 최초 로그인 시 출석체크가 되는 메소드
            session.setAttribute("userInfo", memberDAO.getUserInfo(vo));
        }
        return MyUtils.REDIRECTPAGE("/ojm");
    }

}
