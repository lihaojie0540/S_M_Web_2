package service;

import dao.SpitterDao;
import dao.SpittleDao;
import model.Comment;
import model.Spitter;
import model.Spittle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import static java.util.Collections.reverse;
import static java.lang.Math.min;

@Service("spitterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SpitterServiceImpl implements SpitterService {
    public static final int DEFAULT_SPITTLES_PER_RESULT = 49;

    private ApplicationContext ctx = new ClassPathXmlApplicationContext("spitter-mybatis.xml");
    private SpitterDao spitterDao = (SpitterDao) ctx.getBean("spitterDao");
    private SpittleDao spittleDao = (SpittleDao) ctx.getBean("spittleDao");

    public void saveSpitter(Spitter spitter) {
        spitterDao.addSpitter(spitter);
    }

    public void updateSpitter(Spitter spitter) {
        spitterDao.saveSpitter(spitter);
    }

    public String findSpitterBySpittleid(int i) {
        return spittleDao.findspitter(i).getSpitter().getUsername();
    }

    public Spitter getSpitter(int id) {
        return spitterDao.getSpitterById(id);
    }

    public Spitter getSpitter(String username) {
        return spitterDao.getSpitterByUsername(username);
    }

    public List<Spitter> getAllSpitters() {
        return spitterDao.findAllSpitters();
    }

    public void saveSpittle(Spittle spittle) {
        spittle.setWhens(new Date());
        spitterDao.saveSpittle(spittle);
    }

    public void deleteSpittle(int id) {
        spitterDao.deleteSpittle(id);
    }

    public Spittle getSpittleById(int id) {
        return spitterDao.getSpittleById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Spittle> getRecentSpittles(int count) {
        List<Spittle> spittles = spitterDao.getABunchOfSpittles(count);
        reverse(spittles);
        return spittles.subList(0, min(DEFAULT_SPITTLES_PER_RESULT, spittles.size()));
    }

    public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
        return spitterDao.getSpittlesForSpitter(spitter);
    }

    public List<Spittle> getSpittlesForSpitter(String username) {
        return getSpittlesForSpitter(spitterDao.getSpitterByUsername(username));
    }

    public List<Comment> getAllComments(List<Integer> a) {
        return spitterDao.findAllConments(a);
    }

    public void addComment(Comment comment) {
        spittleDao.addComment(comment);
    }

    public void deleteComment(int id) {
        spittleDao.deleteComment(id);
    }

    public List<Comment> finCommentBySpittleId(int id) {
        return  spittleDao.selectCommnetBySpittleId(id);
    }
}
