package dao;

import model.Comment;
import model.Spitter;
import model.Spittle;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface SpitterDao {

        void addSpitter(Spitter spitter);

        void saveSpitter(Spitter spitter);

        Spitter getSpitterById(int id);

        List<Spittle> getRecentSpittle(int number);

        void saveSpittle(Spittle spittle);

        List<Spittle> getSpittlesForSpitter(Spitter spitter);

        Spitter getSpitterByUsername(String username);

        void deleteSpittle(int id);

        @PostAuthorize("returnObject.spitter.username == principal.username")
        Spittle getSpittleById(int id);

        List<Spitter> findAllSpitters();

        List<Comment> findAllConments(List<Integer> a);

        //只允许拥有 ROLE_SPITTER 权限的用户进行访问
        @PreAuthorize("hasRole(SPITTER)")
        //过滤 Spittle 列表，使用户只看到自己的 Spittle。
        @PostFilter("filterObject.spitter.username == princle.name")
        List<Spittle> getABunchOfSpittles(int number);

        @PreAuthorize("hasRole(SPITTER)")
        @PostFilter("hasPermission(filterObject,'delete')")
        List<Spittle> getSpittelsToDelete(Spitter spitter);

}
