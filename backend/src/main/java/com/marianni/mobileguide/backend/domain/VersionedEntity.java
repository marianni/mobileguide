package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(VersionedEntityListener.class)
public class VersionedEntity implements Serializable {


    @Column(name = "data_version", columnDefinition = "serial")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "data_version_sequence")
    private Long dataVersion = 0L;


    public Long getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Long dataVersion) {
        this.dataVersion = dataVersion;
    }
}
