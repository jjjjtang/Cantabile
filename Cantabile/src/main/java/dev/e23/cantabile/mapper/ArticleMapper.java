package dev.e23.cantabile.mapper;

import dev.e23.cantabile.model.Article;
import dev.e23.cantabile.model.ArticleArtist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select * from article")
    public List<Article> getAllArticles();

    @Select("SELECT a.user_id, u.username, a.content, a.like, a.create_time, a.last_update_time, u.src " +
            "FROM article a " +
            "JOIN user u ON a.user_id = u.id")
    public List<ArticleArtist> getAllArticlesWithUserSrc();

}

