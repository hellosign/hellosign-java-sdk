package com.hellosign.sdk;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneralTest {

    @Test
    public void test() throws Exception {
        HelloSignClient client = new HelloSignClient("chris@hellosign.com", "michaeljfax");
        assertTrue(client.isOnline());
    }
}
