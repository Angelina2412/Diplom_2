package dto

public class ForOrder {
    public ForOrder(String[] ingredients) {
        this.ingredients = ingredients
    }

    String[] getIngredients() {
        return ingredients
    }

    void setIngredients(String[] ingredients) {
        this.ingredients = ingredients
    }
    private String [] ingredients;

}
