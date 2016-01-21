package com.lovelymonkey.core.plugin;

public class TestPlugin implements Plugin{

    private static final String TEST_PLUGIN = "test";

    @Override
    public Anything serve(Anything input) {
        return null;
    }

    @Override
    public String getSymbol() {
        return TEST_PLUGIN;
    }

    @Override
    public boolean isRetriable() {
        // TODO Auto-generated method stub
        return false;
    }

}
