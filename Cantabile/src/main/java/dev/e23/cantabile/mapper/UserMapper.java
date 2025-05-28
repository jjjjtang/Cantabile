package dev.e23.cantabile.mapper;

import dev.e23.cantabile.model.Article;
import dev.e23.cantabile.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> getAllUsers();

    @Insert("INSERT INTO user (username, password, valid) VALUES (#{username}, #{password}, #{valid})")
    void addUser(User user);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User login(String username, String password);

    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    User getUserById(Integer userId);


}
