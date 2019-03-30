package proxy.jdk;

import org.junit.Test;
import proxy.ITarget;
import proxy.TargetImpl;
import proxy.jdk.dynamic.DynamicProxy;

public class DynamicProxyTest {

    @Test
    public void dynamicProxy(){
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ITarget proxy = (ITarget)DynamicProxy.getProxy(new TargetImpl());
        proxy.setColor("red");
        assert "".equals(proxy.getColor());
    }
}
