package pl.edu.vistula.firstrestapispring.product.api.response;

public class ProductResponse {

    private Long id;
    private String name;

    public ProductResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}