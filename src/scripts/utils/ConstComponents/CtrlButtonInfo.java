package src.scripts.utils.ConstComponents;

public class CtrlButtonInfo {
    private final String Value;
    private final String Name;

    public CtrlButtonInfo(String value, String name) {
        this.Value = value;
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public String getValue() {
        return Value;
    }
}
