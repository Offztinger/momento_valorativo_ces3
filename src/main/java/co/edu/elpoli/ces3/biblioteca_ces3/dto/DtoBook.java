package co.edu.elpoli.ces3.biblioteca_ces3.dto;

public class DtoBook {
    public int id;

    protected String author;

    private String bookname;

    public DtoBook(int id, String author, String bookname){
        this.id = id;
        this.author = author;
        this.bookname = bookname;
    }

    public DtoBook(String author, String bookname){
        this.bookname = bookname;
        this.author = author;
    }

    public DtoBook() {

    }

    public int getId(){
        return this.id;
    }


    private void setId(int id){
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }


    @Override
    public String toString() {
        return "El libro se llama " + this.bookname + " su autor es " + this.author;
    }
}
