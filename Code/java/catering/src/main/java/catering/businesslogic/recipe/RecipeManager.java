package catering.businesslogic.recipe;

import catering.businesslogic.event.ServiceInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class RecipeManager {
    private ArrayList<Recipe> recipes;

    public RecipeManager() {
        Recipe.loadAllRecipes();
    }

    public ObservableList<Recipe> getRecipes() {
        return FXCollections.unmodifiableObservableList(Recipe.getAllRecipes());
    }

    public Recipe createRecipe(String name, boolean prep) {
        Recipe rec = new Recipe(name, prep);
        return rec;
    }

    public void insertRecipe(Recipe rec) {
        recipes.add(rec);
    }
}
