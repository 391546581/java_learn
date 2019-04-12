package com.feign.member.rsp;

import lombok.Data;

import java.util.Date;

@Data
public class MemberEntity  {

    // 会员id
    private Integer memberId;

    // 手机号
    private String mobile;

    // 注册手机号
    private String registerMobile;

    // 手机归属国家代码
    private String countryCode;

    // 用户头像id
    private String imageId;

    // 用户姓名
    private String realname;

    // 用户昵称
    private String nickName;

    // 性别(1:男2:女3:未知)
    private String sex;

    // 邮箱
    private String email;

    // 微信openid
    private String wechatOpid;

    // 实名认证标识(0：未实名 1:已实名)  默认：0
    private String realnameAuth;

    // 实名认证渠道
    private String authChannel;

    private Date authTime;

    // 登录密码
    private String password;

    // 交易密码
    private String tradePwd;

    // 是否开通微众账号(0:未开通 1:开通 2:等待人工审核)  默认：0
    private String webankFlag;

    // 是否开通云油账号(0:未开通 1:开通)  默认：0
    private String yunAcctFlag;

    // 身份证
    private String idenCode;

    // 会员编号
    private String shopBn;

    // 推荐人
    private String pcode;

    private Integer pMemberId;

    private String pMobile;

    private String pRealname;

    // 备注
    private String remark;

    // 平台来源,如: web,app
    private String source;

    // app应用市场来源
    private String marketSource;

    // 广告来源
    private String adSource;

	private String activitySource;

    // 注册时间  默认：CURRENT_TIMESTAMP
    private Date registerTime;

    // 最后更新时间  默认：CURRENT_TIMESTAMP
    private Date lastUpdateTime;

    // 用户状态(-1 删除 0:正常 1:冻结 2：注销)  默认：0
    private String status;
}