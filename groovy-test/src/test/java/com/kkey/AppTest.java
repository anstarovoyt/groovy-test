package com.kkey;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static String SCRIPT = "class A{\n" +
            "static arr = new String[100000]; \n" +
            "def do1() { %s }\n" +
            "}\n" +
            "\n" +
            "class C {} \n" +
            "class D {} \n" +
            "class E {} \n" +
            "class F {} \n" +
            "class G {} \n" +
            "class H {} \n" +
            "class H1 {static arr = new String[100000];} \n" +
            "new C() \n" +
            "new D() \n" +
            "new E() \n" +
            "new F() \n" +
            "new G() \n" +
            "new H() \n" +
            "new H1() \n" +
            "return new A().do1()\n";


    @Test
    public void testSimple() {
        ScriptExecutorJSR223 scriptExecutorJSR223 = new ScriptExecutorJSR223();

        String script = String.format(SCRIPT, "1");
        int result = 0;
        for (int i = 0; i < 100000000; i++) {

            result += scriptExecutorJSR223.<Integer>executeScript(script);
        }

        assertEquals(100000000, result);
    }

    @Test
    public void testWithConcat() {
        ScriptExecutorJSR223 scriptExecutorJSR223 = new ScriptExecutorJSR223();

        int result = 0;
        for (int i = 0; i < 100000000; i++) {

            result += scriptExecutorJSR223.<Integer>executeScript(String.format(SCRIPT, "1"));
        }

        assertEquals(100000000, result);
    }

    /**
     * Here we generate a new unique String every time
     */
    @Test
    public void testWithConcatNewString() {
        ScriptExecutorJSR223 scriptExecutorJSR223 = new ScriptExecutorJSR223();

        int result = 0;
        for (int i = 0; i < 100000000; i++) {

            String header = "/* " + i + " */\n";
            result += scriptExecutorJSR223.<Integer>executeScript(header + String.format(SCRIPT, "1"));
        }

        assertEquals(100000000, result);
    }
}
