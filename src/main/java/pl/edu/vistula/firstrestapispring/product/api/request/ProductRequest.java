package pl.edu.vistula.firstrestapispring.product.api.request;

public class ProductRequest {

    private String name;

    public ProductRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}