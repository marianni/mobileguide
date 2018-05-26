package com.marianni.mobileguide.backend.domain;

import javax.persistence.EntityManager;

/**
 *
 * @author mariannaracheªlova
 */

public interface ChildEntity {

    void updateParent(EntityManager em);
}
