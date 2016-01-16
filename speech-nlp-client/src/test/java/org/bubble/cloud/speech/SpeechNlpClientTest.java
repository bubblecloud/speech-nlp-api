package org.bubble.cloud.speech;

import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;
import junit.framework.TestCase;
import org.junit.Test;

public class SpeechNlpClientTest extends TestCase {

    @Test
    public void testUtteranceAnalysis() {
        final SpeechNlpClient client = new SpeechNlpClient("127.0.0.1", 8089);
        final UtteranceAnalysisResult result = client.analyseUtterance("Open the door.");
        System.out.println(result);
    }
}