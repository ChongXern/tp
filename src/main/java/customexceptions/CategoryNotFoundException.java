package customexceptions;

import java.util.Arrays;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(Enum<?>[] categories) {
        super("Category not found. Available categories are:\n" + Arrays.toString(categories));
    }
}
