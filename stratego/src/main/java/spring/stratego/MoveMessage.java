package spring.stratego;

public class MoveMessage {
    private String sessionId;
    private int sourceX;
    private int sourceY;
    private int targetX;
    private int targetY;

    public MoveMessage() {
    }

    public MoveMessage(String sessionId, int sourceX, int sourceY, int targetX, int targetY) {
        this.sessionId = sessionId;
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getSourceX() {
        return sourceX;
    }

    public void setSourceX(int sourceX) {
        this.sourceX = sourceX;
    }

    public int getSourceY() {
        return sourceY;
    }

    public void setSourceY(int sourceY) {
        this.sourceY = sourceY;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public String toString() {
        return "MoveMessage {" +
                "sessionId='" + sessionId + '\'' +
                ", sourceX=" + sourceX +
                ", sourceY=" + sourceY +
                ", targetX=" + targetX +
                ", targetY=" + targetY +
                '}';
    }
}

