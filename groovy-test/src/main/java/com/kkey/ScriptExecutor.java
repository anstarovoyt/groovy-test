package com.kkey;

public interface ScriptExecutor {
    <T> T executeScript(String script);
}
