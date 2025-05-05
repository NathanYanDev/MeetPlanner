package dev.nathanyan.MeetPlanner.model.enums;

public enum AttendanceStatus {
    CONFIRMED("confirmed"),
    PENDING("pending"),
    DECLINED("declined");

    private final String status;

    AttendanceStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public boolean isConfirmed() {
        return this == CONFIRMED;
    }
}
