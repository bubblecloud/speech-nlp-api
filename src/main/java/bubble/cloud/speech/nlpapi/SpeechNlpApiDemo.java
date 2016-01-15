package bubble.cloud.speech.nlpapi;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

/**
 * A demo illustrating how to call the SpeechNlpApi programmatically.
 *
 * @author Tommi S.E. Laukkanen
 */
public class SpeechNlpApiDemo {

    public static void main(String[] args) throws Exception {
        final SpeechNlpApi speechNlpApi = new SpeechNlpApi();

        System.out.println(speechNlpApi.analyseUtterance("the door id is 52"));
        System.out.println(speechNlpApi.analyseUtterance("where is the door"));
        System.out.println(speechNlpApi.analyseUtterance("open the door"));
        System.out.println(speechNlpApi.analyseUtterance("the time is fifteen to twelve"));
        System.out.println(speechNlpApi.analyseUtterance("today is monday"));
    }

}
