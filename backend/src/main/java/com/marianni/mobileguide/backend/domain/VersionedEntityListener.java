package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.math.BigInteger;


public class VersionedEntityListener {


    @PreUpdate
    public void preUpdate(final VersionedEntity entity) {
        setDataVersion(entity);
    }

    @PrePersist
    public void prePersist(final VersionedEntity entity) {
        setDataVersion(entity);
    }

    private void setDataVersion(VersionedEntity entity) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sprievodca_db");
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery( "SELECT nextval('public.data_version_sequence')");
        BigInteger version = (BigInteger) q.getSingleResult();
        entity.setDataVersion(Long.valueOf(version.toString()));
    }
}
