package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mariannarachelova
 */

public class VersionedEntityListener {
    private final static Logger LOG = Logger.getLogger(VersionedEntityListener.class.getName());


    @PreUpdate
    public void preUpdate(final VersionedEntity entity) {
        long started = System.currentTimeMillis();

        doWithEntityManager(em -> {
            setDataVersion(entity, em);
            updateParent(entity, em);
            entity.setChangedOn(new Date());
        });

        long took = System.currentTimeMillis() - started;
        LOG.log(Level.SEVERE, "?????????????????? PRE-UPDATE took " + took + "  on" + entity.getClass().getSimpleName());
    }

    @PrePersist
    public void prePersist(final VersionedEntity entity) {
        long started = System.currentTimeMillis();

        doWithEntityManager(em -> setDataVersion(entity, em));

        long took = System.currentTimeMillis() - started;
        LOG.log(Level.SEVERE, "?????????????????? PRE-PERSIST took " + took + "  on" + entity.getClass().getSimpleName());
    }

    @PreRemove
    public void preRemove(final VersionedEntity entity) {
        doWithEntityManager( em -> updateParent(entity, em));
    }


    private void updateParent(VersionedEntity entity, EntityManager em) {
        if (entity instanceof ChildEntity){
            ((ChildEntity) entity).updateParent(em);
        }
    }

    private void setDataVersion(VersionedEntity entity, EntityManager em) {
        Query q = em.createNativeQuery("SELECT nextval('public.data_version_sequence')");
        BigInteger version = (BigInteger) q.getSingleResult();
        entity.setDataVersion(Long.valueOf(version.toString()));
    }

    private void doWithEntityManager(Consumer<EntityManager> consumer) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sprievodca_db");
        EntityManager em = emf.createEntityManager();
        consumer.accept(em);
        em.flush();
        em.clear();
        emf.close();
    }


}
