package com.example.emmalady.androidrikkeilesson9usejson.model;

/**
 * Created by Liz Nguyen on 05/11/2017.
 */

public class Item {
    private String id;
    private String title;
    private String content;

    public Item(){

    }

    public Item(String id, String title){
        this.id = id;
        this.title = title;
    }
    public Item(String id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
