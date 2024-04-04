package customexceptions;

import command.BaseCommand;

import java.util.Arrays;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(Enum<?>[] categories) {
        super("Category not found. Available categories are:\n" + Arrays.toString(categories));
    }

    public void disableExecute(BaseCommand command) {
        command.setCanExecuteToFalse();
    }
}
