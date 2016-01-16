package org.bubble.cloud.speech;

import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

public class SpeechNlpTcpClientTest extends TestCase {

    @Ignore
    @Test
    public void testUtteranceAnalysis() {
        final SpeechNlpTcpClient client = new SpeechNlpTcpClient("127.0.0.1", 8089);
        final UtteranceAnalysisResult result = client.analyseUtterance("Open the door.");
        System.out.println(result);
    }
}