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

    public void saveSpittle(Spittle spittle) {
        spittle.setWhens(new Date());
        spitterDao.saveSpittle(spittle);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Spittle> getRecentSpittles(int count) {
        List<Spittle> spittles = spitterDao.getABunchOfSpittles(count);
        reverse(spittles);
        return spittles.subList(0, min(DEFAULT_SPITTLES_PER_RESULT, spittles.size()));
    }

    public void saveSpitter(Spitter spitter) {
        spitterDao.addSpitter(spitter);
    }

    public void updateSpitter(Spitter spitter) {
        spitterDao.saveSpitter(spitter);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Spitter getSpitter(int id) {
        return spitterDao.getSpitterById(id);
    }

    public void startFollowing(Spitter follower, Spitter followee) {

    }

    public List<Spittle> getSpittlesForSpitter(Spitter spitter) {
        List<Spittle> spittles = spitterDao.getSpittlesForSpitter(spitter);
        return spittles;
    }

    public List<Spittle> getSpittlesForSpitter(String username) {
        Spitter spitter = spitterDao.getSpitterByUsername(username);
        return getSpittlesForSpitter(spitter);
    }

    public Spitter getSpitter(String username) {
        Spitter spitter = spitterDao.getSpitterByUsername(username);
        return spitter;
    }

    public void deleteSpittle(int id) {
        spitterDao.deleteSpittle(id);
    }

    public List<Spitter> getAllSpitters() {
        List<Spitter> spitters = spitterDao.findAllSpitters();
        return spitters;
    }

    public Spittle getSpittleById(int id) {
        return spitterDao.getSpittleById(id);
    }

    public List<Comment> getAllComments(List<Integer> a) {
        return spitterDao.findAllConments(a);
    }

    public String findSpitterBySpittleid(int i) {
        Spittle spittle = spittleDao.findspitter(i);
        return spittle.getSpitter().getUsername();
    }

}
