package dev.e23.cantabile.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Music {
    private Integer musicId;
    private String title;
    private String src;
    private String cover;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
    private String artist;

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
