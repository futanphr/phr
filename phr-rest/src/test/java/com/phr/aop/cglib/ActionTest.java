package com.phr.aop.cglib;

import org.junit.Before;
import org.junit.Test;

public class ActionTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        Action action = ActionFactory.getActionInstance();
        action.playGame();
        action.smoke();
    }
    
    @Test
    public void TestCglibAction() {
        ActionCglibProxy proxy = new ActionCglibProxy("老板");
        Action action = proxy.getAction();
        action.playGame();
        action.smoke();
    }
}