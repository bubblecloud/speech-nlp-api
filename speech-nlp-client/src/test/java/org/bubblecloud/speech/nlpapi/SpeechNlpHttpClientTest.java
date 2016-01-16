package org.bubblecloud.speech.nlpapi;

import org.bubblecloud.speech.nlpapi.model.UtteranceAnalysisResult;
import org.junit.Ignore;
import org.junit.Test;

public class SpeechNlpHttpClientTest {

    @Ignore
    @Test
    public void testUtteranceAnalysis() {
        final SpeechNlpHttpClient client = new SpeechNlpHttpClient("https://speech-nlp-api.herokuapp.com/");
        final UtteranceAnalysisResult result = client.analyseUtterance("en_US", "kindly open the red door");
        System.out.println(result);
    }
}