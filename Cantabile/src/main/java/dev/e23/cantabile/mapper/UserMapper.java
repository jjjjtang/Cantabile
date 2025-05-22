package dev.e23.cantabile.mapper;

import dev.e23.cantabile.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    public List<User> getAllUsers();


}
