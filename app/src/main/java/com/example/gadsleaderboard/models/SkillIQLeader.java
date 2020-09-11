package com.example.gadsleaderboard.models;

public class SkillIQLeader {
    private String name, score, country, image;

    public SkillIQLeader(String name, String score, String country, String image) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
