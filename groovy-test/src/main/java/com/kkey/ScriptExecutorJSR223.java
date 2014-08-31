package com.kkey;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.logging.Logger;

public class ScriptExecutorJSR223 {

    public static final Logger LOGGER = Logger.getLogger(ScriptExecutorJSR223.class.getName());

    private final ScriptEngine myEngine;

    public ScriptExecutorJSR223() {
        ScriptEngineManager factory = new ScriptEngineManager();
        myEngine = factory.getEngineByName("groovy");
    }

    @SuppressWarnings("unchecked")
    public <T> T executeScript(String scriptText) {

        try {
            LOGGER.fine(scriptText);
            return (T) myEngine.eval(scriptText);

        } catch (ScriptException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
