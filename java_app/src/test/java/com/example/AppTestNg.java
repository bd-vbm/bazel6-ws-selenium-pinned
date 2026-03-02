package com.example;
import org.testng.annotations.Test;import static org.testng.Assert.assertEquals;
public class AppTestNg { @Test public void hello_returns_greeting(){ assertEquals(App.hello("World"), "Hello, World!"); } }
