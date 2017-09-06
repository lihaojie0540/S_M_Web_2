package service;

import model.Comment;
import model.Spitter;
import model.Spittle;
import org.springframework.stereotype.Service;
import java.util.List;

public interface SpitterService {
    void saveSpitter(Spitter spitter);
    void updateSpitter(Spitter spitter);
    String findSpitterBySpittleid(int i);
    Spitter getSpitter(int id);
    Spitter getSpitter(String username);
    List<Spitter> getAllSpitters();

    void saveSpittle(Spittle spittle);
    void deleteSpittle(int id);
    Spittle getSpittleById(int id);
    Spittle findSpittleByCommentid(int id);
    List<Spittle> getRecentSpittles(int count);
    List<Spittle> getSpittlesForSpitter(Spitter spitter);
    List<Spittle> getSpittlesForSpitter(String username);

    void addComment(Comment comment);
    void deleteComment(int id);
    List<Comment> getAllComments(List<Integer> a);
    List<Comment> findCommentBySpittleId(int id);
}
