package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author mariannarachelova
 */

@MappedSuperclass
@EntityListeners(VersionedEntityListener.class)
public class VersionedEntity implements Serializable {



    @Column(name = "data_version", columnDefinition = "serial")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "data_version_sequence")
    private Long dataVersion = 0L;

    @Column(name = "changed_on")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date changedOn = new Date();

    public Long getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Long dataVersion) {
        this.dataVersion = dataVersion;
    }

    public Date getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }



}
