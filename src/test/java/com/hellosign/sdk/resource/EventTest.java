package com.hellosign.sdk.resource;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2014 hellosign.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import static org.junit.Assert.*;

import org.junit.Test;

import com.hellosign.sdk.AbstractHelloSignTest;
import com.hellosign.sdk.HelloSignException;

/**
 * Exercises the Event object.
 * 
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class EventTest extends AbstractHelloSignTest {

    @Test
    public void testEventHashing() throws HelloSignException {
        Event event = new Event(getExpectedJSONResponse());
        assertNotNull(event); 
        assertFalse(event.isValid(null));
        assertFalse(event.isValid(""));

        // This works for the JSON response in EventTest/expectedResponse.txt
        // Ensures that if the hash checking changes, we are notified in the API
        assertTrue(event.isValid("4e94adf59d17c417ce0d1d2cb34b953ba7a1eebe1ecbe31bb1c586af92af1e1d"));
    }

}
