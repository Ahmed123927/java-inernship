public class Main {
    public static void main(String[] args) {
        LibraryManagement libraryManagement=new LibraryManagement();
        Book book=new Book("Java","ahmed",2013);
        Book book2=new Book("Javaa","ahmedd",2013);

        libraryManagement.addResource(book);

        libraryManagement.addResource(new DVD("DVD","ahmed",2001));

        libraryManagement.updateResource(book,book2);
        libraryManagement.showAllResources();
    }
}