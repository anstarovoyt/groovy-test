package com.kkey;

import org.junit.Test;

import javax.script.*;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static final int COUNTER = 10000;


    public static String SCRIPT = "class A{\n" +
            "static arr = new String[1000]; \n" +
            "def do1() { %s }\n" +
            "}\n" +
            "\n" +
            "class C {} \n" +
            "class D {} \n" +
            "class E {} \n" +
            "class F {} \n" +
            "class G {} \n" +
            "class H {} \n" +
            "class H1 {static arr = new String[1000];} \n" +
            "new C() \n" +
            "new D() \n" +
            "new E() \n" +
            "new F() \n" +
            "new G() \n" +
            "new H() \n" +
            "new H1() \n" +
            "return new A().do1()\n";


    public static String SCRIPT_2 = "" +
            "import com.kkey.AccessTo\n" +
            "class A{\n" +
            "static arr = new String[1000]; \n" +
            "def do1() { %s }\n" +
            "}\n" +
            "\n" +
            "class C {} \n" +
            "class D {} \n" +
            "class E {} \n" +
            "class F {} \n" +
            "class G {} \n" +
            "class H {} \n" +
            "class H1 {static arr = new String[1000];} \n" +
            "new C() \n" +
            "new D() \n" +
            "new E() \n" +
            "new F() \n" +
            "new G() \n" +
            "new H() \n" +
            "new H1() \n" +
            "return new A().do1() + new AccessTo().get()\n";

    @Test
    public void testSimple() {
        ScriptExecutor scriptExecutorJSR223 = getScriptExecutor();

        String script = String.format(SCRIPT, "1");
        int result = 0;
        for (int i = 0; i < COUNTER; i++) {

            result += scriptExecutorJSR223.<Integer>executeScript(script);
        }

        assertEquals(COUNTER, result);
    }

    @Test
    public void testWithConcat() {
        ScriptExecutor scriptExecutorJSR223 = getScriptExecutor();

        int result = 0;
        for (int i = 0; i < COUNTER; i++) {

            result += scriptExecutorJSR223.<Integer>executeScript(String.format(SCRIPT, "1"));
        }

        assertEquals(COUNTER, result);
    }

    /**
     * Here we generate a new unique String every time
     */
    @Test
    public void testWithConcatNewString() {
        ScriptExecutor scriptExecutorJSR223 = getScriptExecutor();

        int result = 0;
        for (int i = 0; i < COUNTER; i++) {
            String header = "/* " + i + " */\n";
            result += scriptExecutorJSR223.<Integer>executeScript(header + String.format(SCRIPT, "1"));
        }

        assertEquals(COUNTER, result);
    }

    @Test
    public void testCompileOnly() throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine groovy = scriptEngineManager.getEngineByName("groovy");

        String script = String.format(SCRIPT, "1");
        for (int i = 0; i < COUNTER; i++) {
            String header = "/* " + i + " */\n";
            CompiledScript compile = ((Compilable) groovy).compile(header + script);
            if (compile.toString().equals("aaa")){
                throw new RuntimeException("Cannot be");
            }
        }
    }

    @Test
    public void testCompileOnly_for2() throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine groovy = scriptEngineManager.getEngineByName("groovy");

        String script = String.format(SCRIPT_2, "1");
        for (int i = 0; i < COUNTER; i++) {
            String header = "/* " + i + " */\n";
            CompiledScript compile = ((Compilable) groovy).compile(header + script);
            if (compile.toString().equals("aaa")){
                throw new RuntimeException("Cannot be");
            }
        }
    }



    private ScriptExecutor getScriptExecutor() {
//        return new ScriptExecutorJSR223();
        return new ScriptExecutorDirect();
//        return new ScriptCreateEveryTime();
    }
}
