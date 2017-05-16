package com.heren.his.commons;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joshua on 2016/7/27.
 */
public class ReflectionTestTest {
    @Test
    public void reflectionRun() throws Exception {
        String[] className = {"java.util.Date"};
        ReflectionTest.reflectionRun(className);
    }

}