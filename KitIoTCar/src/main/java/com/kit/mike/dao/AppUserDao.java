package com.kit.mike.dao;


import com.kit.mike.entity.AppUser;
import com.kit.mike.formbean.AppUserForm;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AppUserDao {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser findUserAccount(String userName) {
        try{
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                + " Where e.userName = :userName ";
            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Long getMaxUserId() {
        String sql = "Select e.userId from " + AppUser.class.getName() +  " e ";
        Query query = entityManager.createQuery(sql, Long.class);
        List<Long> idList = query.getResultList();
        long max = 0;
        for(long id: idList) {
            if(max < id)
                max = id;
        }
        return max;
    }

    public List<AppUser> getAppUser() {
        String sql = "Select e from " + AppUser.class.getName() +  " e ";
        Query query = entityManager.createQuery(sql, AppUser.class);

        return query.getResultList();
    }

    public AppUser createAppUser(AppUserForm form) {
        Long userId = this.getMaxUserId() + 1;
        System.out.println("userId = " + userId);
        String encrytedPassword = this.passwordEncoder.encode(form.getPassword());
        AppUser user = new AppUser(userId, form.getUserName(), form.getFullName(), encrytedPassword, false);

        System.out.println("transaction " + entityManager.contains(user));
        entityManager.merge(user);
        return user;
    }


}
