public class DVD extends ResourceInfo{
    public DVD(String title, String author, int publicationYear) {
        super(title, author, publicationYear);
    }

    @Override
    public void displayInfo() {
        System.out.println("DVD Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publication Year: " + getPublicationYear());
    }
}
