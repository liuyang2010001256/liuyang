package proxy.cglib;

import org.junit.Test;
import proxy.ITarget;
import proxy.TargetImpl;

public class CglibProxyTest {

    @Test
    public void cglibProxy(){
        ITarget target = new TargetImpl();
        CglibProxy cglibProxy = new CglibProxy();
        ITarget proxy = (ITarget)cglibProxy.getProxy(target);
        proxy.setColor("red");
        assert "".equals(proxy.getColor());
    }
}
