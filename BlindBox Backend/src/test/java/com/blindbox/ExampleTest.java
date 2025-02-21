package com.blindbox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleTest {
    @Test
    public void exampleTest() {
        int a = 5;
        int b = 3;
        int sum = a + b;
        assertEquals(8, sum, "5 + 3 should equal 8");
    }
}
