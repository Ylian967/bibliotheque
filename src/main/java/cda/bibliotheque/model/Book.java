package cda.bibliotheque.model;

import java.sql.Date;
import java.time.LocalDate;

public class Book {

    private int id;
    private String title;
    private LocalDate releaseDate;
    private boolean isAvailable;

    public Book() {
    }

    public Book(int id, String title, LocalDate releaseDate, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
    }

    public Book(int id, String title, Date releaseDate, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate != null ? releaseDate.toLocalDate() : null;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Date getReleaseDateAsDate() {
        return releaseDate != null ? Date.valueOf(releaseDate) : null;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate != null ? releaseDate.toLocalDate() : null;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}