package bubble.cloud.speech.nlpapi;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Properties;

/**
 * A demo illustrating how to call the OpenIE system programmatically.
 *
 * @author Tommi S.E. Laukkanen
 */
public class NlpApiMain {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        System.out.println(UtteranceAnalysisLogic.analyseUtterance(pipeline, "the door id is 52"));
        System.out.println(UtteranceAnalysisLogic.analyseUtterance(pipeline, "where is the door"));
        System.out.println(UtteranceAnalysisLogic.analyseUtterance(pipeline, "open the door"));
        System.out.println(UtteranceAnalysisLogic.analyseUtterance(pipeline, "the time is fifteen to twelve"));
        System.out.println(UtteranceAnalysisLogic.analyseUtterance(pipeline, "it is monday today"));
    }

}
