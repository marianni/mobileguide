package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author mariannarachelova
 */

public class VersionedEntityListener {



    @PreUpdate
    public void preUpdate(final VersionedEntity entity) {
        EntityManager em = createEntityManager();
        setDataVersion(entity, em);
        updateParent(entity, em);
        entity.setChangedOn(new Date());
    }

    @PrePersist
    public void prePersist(final VersionedEntity entity) {
        EntityManager em = createEntityManager();
        setDataVersion(entity, em);
    }

    @PreRemove
    public void preRemove(final VersionedEntity entity) {
        EntityManager em = createEntityManager();
        updateParent(entity, em);
    }



    private void updateParent(VersionedEntity entity, EntityManager em) {
        /*
        if (entity instanceof ChildEntity){
            ((ChildEntity) entity).updateParent(em);
        }
        */
    }

    private void setDataVersion(VersionedEntity entity, EntityManager em) {
        Query q = em.createNativeQuery( "SELECT nextval('public.data_version_sequence')");
        BigInteger version = (BigInteger) q.getSingleResult();
        entity.setDataVersion(Long.valueOf(version.toString()));
    }

    private EntityManager createEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sprievodca_db");
        return emf.createEntityManager();
    }

}
