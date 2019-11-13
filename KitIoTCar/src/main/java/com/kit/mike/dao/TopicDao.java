package com.kit.mike.dao;

import com.kit.mike.entity.AppUser;
import com.kit.mike.entity.Device;
import com.kit.mike.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TopicDao {

    @Autowired
    private EntityManager entityManager;

    public List<Topic> findTopic(String userName) {
        String sql = "Select tp from " + Topic.class.getName() + " tp, " //
                + AppUser.class.getName() + " a "//
                + " where a.userName =:userName" ;
        Query query = this.entityManager.createQuery(sql, Topic.class);
        query.setParameter("userName", userName);
        return  query.getResultList();
    }
}
