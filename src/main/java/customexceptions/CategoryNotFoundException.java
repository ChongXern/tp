package customexceptions;

import java.util.Arrays;
import java.util.EnumSet;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(Enum<?>[] categories) {
        super("Category not found. Available categories are: " + Arrays.toString(categories));
    }
}
