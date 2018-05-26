package com.marianni.mobileguide.interfaces.dto;

import java.io.Serializable;

public class VoipDTO implements Serializable {
    private Long id;
    private Long employeeId;
    private String voip;

    public VoipDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getVoip() {
        return voip;
    }

    public void setVoip(String voip) {
        this.voip = voip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoipDTO voipDTO = (VoipDTO) o;

        if (id != null ? !id.equals(voipDTO.id) : voipDTO.id != null) return false;
        if (employeeId != null ? !employeeId.equals(voipDTO.employeeId) : voipDTO.employeeId != null) return false;
        return voip != null ? voip.equals(voipDTO.voip) : voipDTO.voip == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 17 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 17 * result + (voip != null ? voip.hashCode() : 0);
        return result;
    }
}
