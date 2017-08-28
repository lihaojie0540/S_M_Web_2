import dao.SpitterDao;
import dao.SpittleDao;
import dao.UserDao;
import model.Comment;
import model.Spitter;
import model.Spittle;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.*;


/**
 * Created by amarsoft on 17-7-25.
 */
public class test {

    public static void main(String[] args){
        Logger logger = Logger.getLogger(test.class);
        commentTest();
    }

    public static void spiiterTest(){

        ApplicationContext ctx = null;
        ctx = new ClassPathXmlApplicationContext("spitter-mybatis.xml");
        SpitterDao spitterDao = (SpitterDao) ctx.getBean("spitterDao");
        Spitter spitter = new Spitter();

    }

    public static void spiitleTest(){

        ApplicationContext ctx = null;
        ctx = new ClassPathXmlApplicationContext("spitter-mybatis.xml");
        SpittleDao spittleDao = (SpittleDao) ctx.getBean("spittleDao");
        Spittle spittle = new Spittle();

    }

    public static void commentTest(){
        ApplicationContext ctx = null;
        ctx = new ClassPathXmlApplicationContext("spitter-mybatis.xml");
        SpittleDao spittleDao = (SpittleDao) ctx.getBean("spittleDao");
        Comment comment = new Comment();
        comment.setSpittleid(5);
        comment.setComment("ceshi");
        comment.setName("Aalto");
        List<Comment> x1 = spittleDao.selectCommnetBySpittleId(7);
        Gson gson = new GsonBuilder().create();
        gson.toJson(x1,System.out);
    }
}
