package com.marianni.mobileguide.backend.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author mariannarachelova
 */

@MappedSuperclass
public class SoftDeleteEntity extends VersionedEntity {

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}



