package dto

class ForOrder {
    ForOrder(String[] ingredients) {
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
