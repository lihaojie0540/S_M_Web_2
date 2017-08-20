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

/**
 * Created by amarsoft on 17-7-25.
 */
public class test {

    public static void main(String[] args){
        Logger logger = Logger.getLogger(test.class);
        ApplicationContext ctx = null;
        ctx = new ClassPathXmlApplicationContext("spitter-mybatis.xml");

        SpitterDao spitterDao = (SpitterDao) ctx.getBean("spitterDao");
        SpittleDao spittleDao = (SpittleDao) ctx.getBean("spittleDao");

        Spitter spitter = new Spitter();
        Spittle spittle = new Spittle();

        List<Integer> a = new ArrayList<Integer>();
        a.add(7);
        a.add(8);
        a.add(9);
        List<Comment> comments = spitterDao.findAllConments(a);
    }
}
