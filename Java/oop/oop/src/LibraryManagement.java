import java.util.ArrayList;
import java.util.List;

public class LibraryManagement {
    private List<ResourceInfo> resources;
    public LibraryManagement() {
        this.resources = new ArrayList<>();
    }
    public void addResource(ResourceInfo resource) {
        resources.add(resource);
    }
    public void removeResource(ResourceInfo resource) {
        resources.remove(resource);
    }
    public void updateResource(ResourceInfo oldResource, ResourceInfo newResource) {
        int index = resources.indexOf(oldResource);
        if (index != -1) {
            resources.set(index, newResource);
            System.out.println("Resource updated successfully!");
        } else {
            System.out.println("Resource not found in the library.");
        }
    }
    public void showAllResources() {
        for (ResourceInfo resource : resources) {
            resource.displayInfo();
            System.out.println("*******************");
        }
    }
}
