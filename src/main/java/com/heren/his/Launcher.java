package com.heren.his;


import com.heren.i0.core.ServletContainer;

public class Launcher {
    /**
     * Launcher版本有更新原来的执行方式不再有效，在IDE里面启动Launcher，请执行本类的main方法。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        CompositeModule module = new CompositeModule();
//        CompositeModule.class.getConstructor().newInstance();
        ServletContainer servletContainer = com.heren.i0.core.Launcher.launch(module, false);
        CompositeModule.injector = servletContainer.injector();
        servletContainer.start(true);
    }
}
