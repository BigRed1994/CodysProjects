package com.lendingcatalog;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class UnitTest1 {


    @Test
    public void testMethod1() {
        fail("This is just a test");
    }
}

