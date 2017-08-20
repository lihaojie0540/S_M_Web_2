package model;

import java.util.Date;

public class Comment {
    private int id;
    private int spittleid;
    private String name;
    private String comment;
    private Date whens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpittleid() {
        return spittleid;
    }

    public void setSpittleid(int spittleid) {
        this.spittleid = spittleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getWhens() {
        return (Date) whens.clone();
    }

    public void setWhens(Date whens) {
        this.whens = (Date) whens.clone();
    }
}
