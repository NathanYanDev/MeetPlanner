package dev.nathanyan.MeetPlanner.model.enums;

public enum MeetingStatus {
    CONFIRMED("confirmed"),
    PENDING("pending"),
    DECLINED("declined");

    private final String status;

    MeetingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
