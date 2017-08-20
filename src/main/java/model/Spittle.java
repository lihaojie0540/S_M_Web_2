package model;

import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component("spittle")
public class Spittle {
    private int id;
    private Spitter spitter;
    private String text;
    private Date whens;
    private List<Comment> comments;

    public Spittle() {
        this.spitter = new Spitter();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getWhens() {
        return (Date) whens.clone();
    }

    public void setWhens(Date whens) {
        this.whens = (Date) whens.clone();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
