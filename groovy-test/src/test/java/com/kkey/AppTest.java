package com.kkey;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest
{
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
            "class H2 {static arr = new String[100000];} \n" +
            "class H3 {static arr = new String[100000];} \n" +
            "class H4 {static arr = new String[100000];} \n" +
            "class H5 {static arr = new String[100000];} \n" +
            "class H6 {static arr = new String[100000];} \n" +
            "class H7 {static arr = new String[100000];} \n" +
            "class H8 {static arr = new String[100000];} \n" +
            "class H9 {static arr = new String[100000];} \n" +
            "new C() \n" +
            "new D() \n" +
            "new E() \n" +
            "new F() \n" +
            "new G() \n" +
            "new H() \n" +
            "new H1() \n" +
            "new H1() \n" +
            "new H2() \n" +
            "new H3() \n" +
            "new H4() \n" +
            "new H5() \n" +
            "new H6() \n" +
            "new H7() \n" +
            "new H8() \n" +
            "new H9() \n" +
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
}
