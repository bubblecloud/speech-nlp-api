package org.bubble.cloud.speech;

import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

public class SpeechNlpHttpClientTest {

    @Ignore
    @Test
    public void testUtteranceAnalysis() {
        final SpeechNlpHttpClient client = new SpeechNlpHttpClient("https://speech-nlp-api.herokuapp.com/");
        final UtteranceAnalysisResult result = client.analyseUtterance("Open the door.");
        System.out.println(result);
    }
}