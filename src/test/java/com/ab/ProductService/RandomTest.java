package com.ab.ProductService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomTest {

    @Test
    void testAddition(){
        int num = 1+1;
//        assert num ==2+1;
        assertTrue(2 == num);
        assertEquals(2, num);
    }
}
