package ohtu;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TYYPPI PUUTTUU VIELÄ!!! MUISTETAAN LISÄTÄ SE KUN SOVITAAN MILLAINEN SE ON
 * TODO - toString
 *
 *
 */
public class Book implements BookmarkInterface {

    private String author;
    private String title;
//String vai erillinen luokka?
//    private String type;
//private Tyyppi type;
    private String ISBN;
    private ArrayList<String> tags;
    private HashMap<String, String> prerequisiteCourses;
    private HashMap<String, String> relatedCourses;

    //Mahdollinen dependency injection - älkää ottako huomioon konstruktorin parametriä ainakaan aluksi
    public Book(String author, String title, String ISBN) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
        this.tags = new ArrayList();
        this.prerequisiteCourses = new HashMap();
        this.relatedCourses = new HashMap();
    }

    public void addNewTag(String tag) {
        this.tags.add(tag);
    }

    public void addPrerequisiteCourse(String courseId, String courseName) {
        if (!this.prerequisiteCourses.containsKey(courseId)) {
            this.prerequisiteCourses.put(courseId, courseName);
        }
    }

    public void addRelatedCourse(String courseId, String courseName) {
        if (!this.relatedCourses.containsKey(courseId)) {
            this.prerequisiteCourses.put(courseId, courseName);
        }
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;

    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getISBN() {
        return this.ISBN;
    }

}
