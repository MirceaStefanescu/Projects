import java.util.ListResourceBundle;

public class MyLabels_de_DE extends ListResourceBundle {
    private final Object[][] labels = {{"integer_value", Integer.valueOf(100)},
            {"string_value", "MILES"},};

    @Override protected Object[][] getContents() {
        return labels;
    }
}
