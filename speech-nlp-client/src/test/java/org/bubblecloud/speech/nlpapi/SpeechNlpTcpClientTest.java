package org.bubblecloud.speech.nlpapi;

import org.bubblecloud.speech.nlpapi.model.UtteranceAnalysisResult;
import org.junit.Ignore;
import org.junit.Test;

public class SpeechNlpTcpClientTest {

    @Ignore
    @Test
    public void testUtteranceAnalysis() {
        final SpeechNlpTcpClient client = new SpeechNlpTcpClient("127.0.0.1", 8089);
        final UtteranceAnalysisResult result = client.analyseUtterance("en_US", "Open the door.");
        System.out.println(result);
    }
}