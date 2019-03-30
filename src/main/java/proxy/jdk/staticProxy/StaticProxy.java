package proxy.jdk.staticProxy;

import proxy.ITarget;

public class StaticProxy implements ITarget {

    private ITarget target;

    public StaticProxy(ITarget target){
        this.target = target;
    }

    @Override
    public String getColor() {
        String color = target.getColor();
        if (color == null || "".equals(color)) return "no color";
        return color;
    }

    @Override
    public void setColor(String color) {
        if (color == null || "".equals(color)) return;
        target.setColor(color);
    }
}
