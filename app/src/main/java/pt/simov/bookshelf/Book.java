package pt.simov.bookshelf;

/**
 * Created by André on 09/01/2016.
 */
public class Book {

    private String title;
    private String thumbnailUrl;
    private String year;
    private int rating;
    private int pages;
    private String author;
    private String publishedDate;
    private String synopsis;

    public Book() {
    }

    public Book(String title, String thumbnailUrl, String year, int rating, int pages,
                String author, String publishedDate, String synopsis) {

        this.setTitle(title);
        this.setThumbnailUrl(thumbnailUrl);
        this.setYear(year);
        this.setRating(rating);
        this.setPages(pages);
        this.setAuthor(author);
        this.setPublishedDate(publishedDate);
        this.setSynopsis(synopsis);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
