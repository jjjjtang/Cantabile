package dev.e23.cantabile.controller;


import dev.e23.cantabile.mapper.MusicMapper;
import dev.e23.cantabile.model.Music;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MusicController {

    @Resource
    private MusicMapper musicMapper;


    @GetMapping("/getAllMusics")
    public List<Music> getAllMusics(){
        return musicMapper.getAllMusics();
    }

    @GetMapping("/searchMusics")
    public List<Music> searchMusics(@RequestParam("keyword") String keyword){
        return musicMapper.searchMusics(keyword);
    }

    @GetMapping("/hotMusics")
    public List<Music> hotMusics(){
        return musicMapper.hotMusics();
    }
}
