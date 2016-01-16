package org.bubblecloud.speech.nlpapi;

import org.bubblecloud.speech.nlpapi.model.UtteranceAnalysisResult;
import org.junit.Ignore;
import org.junit.Test;

public class SpeechNlpHttpClientTest {

    @Ignore
    @Test
    public void testUtteranceAnalysis() {
        final SpeechNlpHttpClient client = new SpeechNlpHttpClient("https://speech-nlp-api.herokuapp.com/");
        final UtteranceAnalysisResult result = client.analyseUtterance("en_US", "a dog escaped");
        System.out.println(result);
    }
}