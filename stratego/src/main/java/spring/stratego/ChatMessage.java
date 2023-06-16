package spring.stratego;

public class ChatMessage {
    private String from;
    private String content;
    private String time;

    public ChatMessage() {
        // Default constructor required for JSON deserialization
    }

    public ChatMessage(String from, String content, String time) {
        this.from = from;
        this.content = content;
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String timestamp) {
        this.time = timestamp;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "ChatMessage {" +
                "from='" + from + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
    

}
