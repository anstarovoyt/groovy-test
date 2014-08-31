package com.kkey;


import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptCreateEveryTime implements ScriptExecutor {

    private final ScriptEngineManager myFactory;

    public ScriptCreateEveryTime() {
        myFactory = new ScriptEngineManager();
    }

    @Override
    public <T> T executeScript(String script) {
        try {
            return (T) myFactory.getEngineByName("groovy").eval(script);

        } catch (ScriptException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
