package com.example.smartguide.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.smartguide.dto.LoginReq;
import com.example.smartguide.dto.SignUpReq;
import com.example.smartguide.model.Role;
import com.example.smartguide.model.Member;

@Mapper
public interface MemberMapper {

	@Select("SELECT *" + " FROM user" + " WHERE id=#{userId}")
	Member selectUser(@Param("userId") Long userId);

	@Select("SELECT id FROM user WHERE username=#{username}")
	Long selectUserIdByUsername(@Param("username") String username);

	@Select("SELECT id FROM user WHERE username=#{loginReq.username} AND password=#{loginReq.password}")
	Long selectUserId(@Param("loginReq") LoginReq loginReq);

	@Select("SELECT authority FROM user WHERE id=#{id}")
	Role getAuthorizationById(@Param("id") Long id);

	@Select("SELECT group_id FROM user WHERE username=#{username}")
	Long selectGroupIdByUsername(@Param("username") String username);

	@Select("SELECT * FROM user WHERE username=#{username}")
	Optional<Member> selectUserByUsername(@Param("username") String username);

	@Insert("INSERT INTO user(username, password, role, group_id)" + " VALUES(#{signUpReq.username},"
			+ " #{signUpReq.password}," + " #{signUpReq.role}," + " #{signUpReq.groupId})")
	int insertUser(@Param("signUpReq") SignUpReq signUpReq);

	@Insert("INSERT INTO user(username, password, group_id) VALUES(#{username}, #{password}, #{groupId})")
	int insertNormalUser(@Param("username") String username, @Param("password") String password,
			@Param("groupId") Long groupId);
}
