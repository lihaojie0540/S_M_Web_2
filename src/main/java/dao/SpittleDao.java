package dao;

import model.Comment;
import model.Spittle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpittleDao {

    @PreAuthorize("(hasRole('ROLE_SPITTER') "
            + "and #spittle.text.length() <= 140) or "
            + "hasRole('ROLE_ADMIN')")
    void addSpittle(Spittle spittle);

    void updateSpittle(Spittle spittle);

    Spittle selectalldetail(int id);

    Spittle findspitter(int id);

    void addComment(Comment comment);

    void deleteComment(int id);

    List<Comment>selectCommnetBySpittleId(int id);
}
