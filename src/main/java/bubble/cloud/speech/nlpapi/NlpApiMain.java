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
        // Create the Stanford CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,natlog,openie");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        System.out.println(UtteranceAnalysisLogic.analyseUtterance(pipeline, "the door id is 52"));
        System.out.println(UtteranceAnalysisLogic.analyseUtterance(pipeline, "where is the door"));
        System.out.println(UtteranceAnalysisLogic.analyseUtterance(pipeline, "open the door"));


        // Loop over sentences in the document
        /*for (CoreMap sentence : doc.get(CoreAnnotations.SentencesAnnotation.class)) {
            // Get the OpenIE triples for the sentence
            Collection<RelationTriple> triples = sentence.get(NaturalLogicAnnotations.RelationTriplesAnnotation.class);
            // Print the triples
            for (RelationTriple triple : triples) {
                System.out.println(triple.confidence + "\t" +
                        triple.subjectLemmaGloss() + "\t" +
                        triple.relationLemmaGloss() + "\t" +
                        triple.objectLemmaGloss());
            }
        }*/
    }

}
