package com.kkey;


import javax.script.*;
import java.util.HashMap;

public class ScriptExecutorDirect implements ScriptExecutor {
    private final ScriptEngine myEngine;

    public ScriptExecutorDirect() {
        ScriptEngineManager factory = new ScriptEngineManager();
        myEngine = factory.getEngineByName("groovy");
    }

    public <T> T executeScript(String script) {
        try {
            CompiledScript compile = ((Compilable) myEngine).compile(script);
            return (T) compile.eval(new SimpleScriptContext());
        } catch (ScriptException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
