package com.koreait.lunchproject1.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
    private String name;
    private String nickName;
    private String email;
    private String gender;
    private String id;
    private String pw;
    private String point;
    private String rank;
    private String reg_dt;

    //-------------------------
    private String select;
    private String sign;
    private String log;
    private int loginPoint;
    private int attendance;

}
