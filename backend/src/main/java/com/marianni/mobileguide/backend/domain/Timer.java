package com.marianni.mobileguide.backend.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "timer", schema = "public")
public class Timer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    @Column(name = "is_running_update")
    private Boolean isRunningUpdate;

    public Timer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getRunningUpdate() {
        return isRunningUpdate;
    }

    public void setRunningUpdate(Boolean runningUpdate) {
        isRunningUpdate = runningUpdate;
    }
}
