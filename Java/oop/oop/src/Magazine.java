public class Magazine extends ResourceInfo{
    public Magazine(String title, String author, int publicationYear) {
        super(title, author, publicationYear);
    }

    @Override
    public void displayInfo() {
        System.out.println("Magazine Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publication Year: " + getPublicationYear());
    }
}
