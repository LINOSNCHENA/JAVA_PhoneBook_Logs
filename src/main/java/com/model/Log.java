package com.model;

public class Log {
    private String user_id;
    private String dated;
    private String logger;
    private String level;
    private String message;

    public Log(String user_id, String dated, String logger, String level, String message) {
        this.user_id = user_id;
        this.dated = dated;
        this.logger = logger;
        this.level = level;
        this.message = message;
    }

    public Log(String dated, String logger, String level, String message) {

        this.dated = dated;
        this.logger = logger;
        this.level = level;
        this.message = message;
    }

    public Log() {
    }

    public String user_id() {
        return user_id;
    }

    public String getDated() {
        return dated;
    }

    public String getLogger() {
        return logger;
    }

    public String getLevel() {
        return level;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + user_id + ", Date='" + dated + '\'' + ", Logger=" + logger + ", Message =" + message
                + '}';
    }
}
