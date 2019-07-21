package faker;

import org.apache.commons.text.WordUtils;

import java.io.Serializable;

public class FakerField implements Serializable {
    private String label;
    private String value;

    public FakerField(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FakerField{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
