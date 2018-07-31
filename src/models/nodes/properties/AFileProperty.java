package models.nodes.properties;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by stratosphr on 31/07/2018.
 */
public abstract class AFileProperty extends AProperty<File> {

    private final List<EFileFilters> filters;

    protected AFileProperty(String name, File initialValue, EFileFilters... filters) {
        super(name, initialValue);
        this.filters = Arrays.asList(filters);
    }

    public List<EFileFilters> getFilters() {
        return filters;
    }

    public enum EFileFilters {
        BMP,
        PNG,
        JPG,
        JPEG,
        IMAGES(BMP, PNG, JPG, JPEG);

        private final Set<EFileFilters> filters;

        EFileFilters(EFileFilters... filters) {
            if (filters.length == 0) {
                this.filters = Collections.singleton(this);
            } else {
                this.filters = new LinkedHashSet<>(Arrays.asList(filters));
            }
        }

        public FileChooser.ExtensionFilter getExtensionFilter() {
            return new FileChooser.ExtensionFilter(this.toString() + " " + filters.stream().map(eFileFilters -> "*." + eFileFilters.toString().toLowerCase()).collect(Collectors.joining(", ", "(", ")")), filters.stream().map(eFileFilters -> "*." + eFileFilters.toString().toLowerCase()).toArray(String[]::new));
        }

    }

}
