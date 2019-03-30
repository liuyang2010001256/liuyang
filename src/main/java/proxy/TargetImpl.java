package proxy;

public class TargetImpl implements ITarget {

    private String color;

    @Override
    public String getColor() {
            return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }
}
