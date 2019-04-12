package com.feign.member.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.bwoil.c2b.utils.json.JacksonUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: wangcs
 * @Date: 2019/4/11 10:53
 * @Description:
 */

@Service
public class MemberDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public List<Members> queryAllMembers() {
//        String sql = "select a.member_id ,a.login_account mobile ,a.disabled status " +
//                "from sdb_pam_members a " +
//                "left join sdb_b2c_members b on a.member_id = b.member_id " +
//                "left join sdb_b2c_transaction_pass d on a.member_id = d.member_id " +
//                "left join (select * from sdb_b2c_member_identity_info where audit_status='1') c on a.member_id = c.member_id " +
//                " where a.login_type='mobile' limit 10 ";
//        List<Members> list = jdbcTemplate.query(sql, new RowMapper<Members>() {
//            @Override
//            public Members mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Members members = new Members();
//                members.setMemberId(rs.getInt("member_id"));
//                members.setMobile(rs.getString("mobile"));
//                members.setStatus(rs.getString("status"));
//                return members;
//            }
//        });
//        return list;
//    }
    public List<Members> queryMembersByPage(int page) {
        String sql = "select a.member_id ,a.login_account mobile ,a.disabled status " +
                "from sdb_pam_members a " +
                "left join sdb_b2c_members b on a.member_id = b.member_id " +
                "left join sdb_b2c_transaction_pass d on a.member_id = d.member_id " +
                "left join (select * from sdb_b2c_member_identity_info where audit_status='1') c on a.member_id = c.member_id " +
                " where a.login_type='mobile' limit "+ (page-1)*10 +",10 ";
        List<Members> list = jdbcTemplate.query(sql, new RowMapper<Members>() {
            @Override
            public Members mapRow(ResultSet rs, int rowNum) throws SQLException {
                Members members = new Members();
                members.setMemberId(rs.getInt("member_id"));
                members.setMobile(rs.getString("mobile"));
                members.setStatus(rs.getString("status"));
                return members;
            }
        });
        return list;
    }

}
