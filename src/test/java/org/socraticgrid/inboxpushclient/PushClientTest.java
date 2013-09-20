package org.socraticgrid.inboxpushclient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PushClientTest 
   
{

    public PushClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPushClient() throws IOException, Exception {
        PushSessionMgr client = new PushSessionMgr();
        
        String jsonALert=
            "ALERTS=123,{"
            +"\"messagesFact\": {"
            +"\"successStatus\": true ,"
            +"\"messageObjects\": ["
            +"{"
            +"  \"messageId\": \"444\","
            +"  \"type\": \"Alert\","
            +"  \"location\": \"INBOX\","
            +"  \"labels\": [],"
            +"  \"messageDate\": \"2013-09-11\","
            +"  \"messageTime\": \"12:30\","
            +"  \"description\": \"This patient was prescribed Demerol that has a metabolite, which in the setting of kidney disease may result in increased seizure risk.   The average Creatinine value over the previous 6 measurements was 1.1 indicating impaired renal function.  If an opioid is needed for pain control, consider hydromorphone or fentanyl as alternatives.\","
            +"  \"from\": \"CDS\","
            +"  \"title\": \"Seizure Risk.\","
            +"  \"status\": \"Unread\","
            +"  \"priority\": \"HIGH\","
            +"  \"tasksCount\": 1,"
            +"  \"tasksComplete\": 0"
            +"}"
            +"]"
            +"}"
            +"} ";
        
        // PUll ALERTS from db
        
        
        client.pushMsg(jsonALert);

        
    }
}
