package dev.e23.cantabile.mapper;

import dev.e23.cantabile.model.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MusicMapper {

    @Select("select * from music")
    public List<Music> getAllMusics();

    @Select("SELECT * FROM music WHERE title LIKE CONCAT('%', #{keyword}, '%') " +
            "OR artist LIKE CONCAT('%', #{keyword}, '%')")
    List<Music> searchMusics(@Param("keyword") String keyword);

    @Select("select * from music order by likes desc limit 10")
    public List<Music> hotMusics();

}
