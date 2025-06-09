package dev.e23.cantabile.controller;

import dev.e23.cantabile.mapper.ArticleMapper;
import dev.e23.cantabile.model.Article;
import dev.e23.cantabile.model.ArticleArtist;
import dev.e23.cantabile.model.User;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Resource
    private ArticleMapper articleMapper;

    @GetMapping("/getArticleById")
    public List<ArticleArtist> getArticleArtistById(@RequestParam("userId") Integer userId) {
        return articleMapper.getAllArticlesWithUserSrc();
    }

    @PostMapping("/addArticle")
    public void addArticle(@RequestBody Article content) {
        articleMapper.addArticle(content);
    }



}