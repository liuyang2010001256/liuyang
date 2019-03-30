package proxy.jdk;

import org.junit.Test;
import proxy.ITarget;
import proxy.TargetImpl;
import proxy.jdk.staticProxy.StaticProxy;

public class StaticProxyTest {


    @Test
    public void staticProxy(){
        ITarget proxy = new StaticProxy(new TargetImpl());
        proxy.setColor("");
        String color = proxy.getColor();
        assert "no color".equals(color);
    }
}
