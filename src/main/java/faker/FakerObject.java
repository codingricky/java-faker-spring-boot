package faker;

import java.io.Serializable;
import java.util.List;

public class FakerObject implements Serializable {
    private final String name;
    private final List<FakerField> fields;

    public FakerObject(String name, List<FakerField> fields) {
        this.name = name;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public List<FakerField> getFields() {
        return fields;
    }
}
