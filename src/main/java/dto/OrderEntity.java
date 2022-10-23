package dto;

import java.util.List;

public class OrderEntity {
    private int price;
    private int number;
    private String updatedat;
    private String createdat;
    private String name;
    private String status;
    private OwnerEntity owner;
    private String Id;
    private List<IngredientsEntity> ingredients;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public List<IngredientsEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsEntity> ingredients) {
        this.ingredients = ingredients;
    }
}
