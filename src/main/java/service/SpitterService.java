package service;

import model.Comment;
import model.Spitter;
import model.Spittle;
import org.springframework.stereotype.Service;
import java.util.List;

public interface SpitterService {
    List<Spittle> getRecentSpittles(int count);
    void saveSpittle(Spittle spittle);

    void saveSpitter(Spitter spitter);
    Spitter getSpitter(int id);
    void startFollowing(Spitter follower, Spitter followee);

    List<Spittle> getSpittlesForSpitter(Spitter spitter);
    List<Spittle> getSpittlesForSpitter(String username);
    Spitter getSpitter(String username);

    Spittle getSpittleById(int id);
    void deleteSpittle(int id);

    List<Spitter> getAllSpitters();
    List<Comment> getAllComments(List<Integer> a);

    String findSpitterBySpittleid(int i);
    void updateSpitter(Spitter spitter);
}
