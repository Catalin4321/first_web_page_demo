package com.website.web.models;

import jakarta.persistence.*;

//crearea tabelului pentru baza de date
@Entity
public class Post {

    //crearea campurilor tabelului
    //crearea coloanei pentru id cu generarea automata a ID
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq")
    @SequenceGenerator(name="my_entity_seq", sequenceName = "my_entity_seq", allocationSize = 10, initialValue = 1)
    private Integer id;

    private String title, anons, full_text, comment;
    private int views;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setComment(String comment){
        this.comment=comment;
    }
    public String getComment(){
        return comment;
    }

    //crearea constructorului pentru a salva datele din pagina web in BD
    public Post(String title, String anons, String full_text, String comment) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.comment=comment;
    }

    //fara un constructor default nu merge codul
    public Post(){

    }


}
