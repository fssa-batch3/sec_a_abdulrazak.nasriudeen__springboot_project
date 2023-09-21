package com.reparo.dto.service;

public class RejectServiceListDto {
    private int serviceListId;
    private String cancelReason;
    private boolean isCancelled;

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public int getServiceListId() {
        return serviceListId;
    }

    public void setServiceListId(int serviceListId) {
        this.serviceListId = serviceListId;
    }
}
