package org.bubblecloud.speech.nlpapi;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tlaukkan on 1/16/2016.
 */
public class SpeechNlpApiTest {

    @Test
    public void testUtteranceAnalysis() {
        final SpeechNlpApiImpl speechNlpApi = new SpeechNlpApiImpl();
        Assert.assertEquals("Locale not supported: fi_FI", speechNlpApi.analyseUtterance("fi_FI", "koira karkasi").getErrorMessage());
        Assert.assertEquals(UtteranceType.STATEMENT, speechNlpApi.analyseUtterance("en_US", "dog escaped").getType());
        Assert.assertEquals(UtteranceType.STATEMENT, speechNlpApi.analyseUtterance("en_US", "a dog escaped").getType());
        Assert.assertEquals(UtteranceType.STATEMENT, speechNlpApi.analyseUtterance("en_US", "the door id is 52").getType());
        Assert.assertEquals(UtteranceType.QUESTION, speechNlpApi.analyseUtterance("en_US", "where is the door").getType());
        Assert.assertEquals(UtteranceType.COMMAND, speechNlpApi.analyseUtterance("en_US", "open the door").getType());
        Assert.assertEquals(UtteranceType.STATEMENT, speechNlpApi.analyseUtterance("en_US", "the time is fifteen to twelve").getType());
        Assert.assertEquals(UtteranceType.STATEMENT, speechNlpApi.analyseUtterance("en_US", "today is monday").getType());
        Assert.assertEquals(UtteranceType.QUESTION, speechNlpApi.analyseUtterance("en_US", "who is there").getType());
        Assert.assertEquals(UtteranceType.STATEMENT, speechNlpApi.analyseUtterance("en_US", "i am Tommi").getType());
    }

}
