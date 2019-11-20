package by.gsu.epamlab.beans;

import java.time.LocalDate;

public class Tasks {
    private User user;
    private int idTask;
    private LocalDate date;
    private String title;
    private String body;
    private int isReady;
    private int isRecycle;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Tasks() {

    }

    public int getIsReady() {
        return isReady;
    }

    public void setIsReady(int isReady) {
        this.isReady = isReady;
    }

    public int getIsRecycle() {
        return isRecycle;
    }

    public void setIsRecycle(int isRecycle) {
        this.isRecycle = isRecycle;
    }

    public User getUser() {
        return user;

    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Tasks(User user, int idTask, LocalDate date, String title, String body, int isReady, int isRecycle, String url) {
        this.user = user;
        this.idTask = idTask;
        this.date = date;
        this.title = title;
        this.body = body;
        this.isReady = isReady;
        this.isRecycle = isRecycle;
        this.url = url;
    }
}
