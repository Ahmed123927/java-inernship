import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
    Product product=new Product();

        List<Product> products=product.addDummyData();
        List<Product> books = products.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .toList();

        List<Product> booksPrice=books.stream()
                .filter(p->p.getPrice()>100).
                toList();

        List<Product> Toys = products.stream()
                        .filter(p -> p.getCategory().equals("Toys"))
                                .map(p ->{
                                    p.setPrice(p.getPrice()*0.10);
                                    return p;
                                }).
                toList();

        Optional<Product> minPriceProduct = products.stream()
                .min(Comparator.comparingDouble(Product::getPrice));
        Toys .forEach(System.out::println);

    }
}