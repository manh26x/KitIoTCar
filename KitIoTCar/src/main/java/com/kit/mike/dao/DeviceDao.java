package com.kit.mike.dao;

import com.kit.mike.entity.AppUser;
import com.kit.mike.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DeviceDao {
    @Autowired
    private EntityManager entityManager;

    public List<Device> findDevice(Long userId) {
        String sql = "Select d from " + Device.class.getName() + " d " //
                + " Where d.userId = :userId";
        Query query = entityManager.createQuery(sql, Device.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }


    public List<Device> findDevice(String userName) {
        String sql = "Select d from " + Device.class.getName() + " d,  "  + AppUser.class.getName() + " a "//
                + " Where a.userName = :userName";
        Query query = entityManager.createQuery(sql, Device.class);
        query.setParameter("userName", userName);
        return query.getResultList();
    }
}
