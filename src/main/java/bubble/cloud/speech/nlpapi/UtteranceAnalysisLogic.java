package bubble.cloud.speech.nlpapi;

import bubble.cloud.speech.nlpapi.model.Node;
import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Logic for analysing utterance.
 *
 * @author Tommi S.E. Laukkanen
 */
public class UtteranceAnalysisLogic {
    /**
     * Analyses utterance using StanfordCoreNLP pipeline.
     * @param pipeline the pipeline
     * @param utterance the utterance
     * @return the utterance analysis result
     */
    public static UtteranceAnalysisResult analyseUtterance(StanfordCoreNLP pipeline, String utterance) {
        UtteranceAnalysisResult analysis = null;

        // Annotate an example document.
        Annotation doc = new Annotation(utterance);
        pipeline.annotate(doc);

        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        if (sentences != null && sentences.size() > 0) {
            for (CoreMap sentence : sentences) {
                Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
                System.out.println("The first sentence parsed is:");
                tree.pennPrint(System.out);

                final Map<String, String> lemmaMap = new HashMap<String, String>();
                for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    lemmaMap.put(token.value(), token.get(CoreAnnotations.LemmaAnnotation.class));
                }
                final Node node = new Node();
                NodeLogic.populateNodeRecursively(tree, lemmaMap, node, 0);
                analysis = new UtteranceAnalysisResult();

                analysis.setUtterance(node);
                final Node clause = node.find("S", false, null);
                if (clause != null) {
                    analysis.setSubjectPhrase(clause.find("NP", true, Arrays.asList("VP")));
                    analysis.setVerb(node.find("VB", true, null));
                    final Node verbPhrase = clause.find("VP", false, null);
                    if (verbPhrase != null) {
                        analysis.setObjectPhrase(verbPhrase.find("NP", true, null));
                    }
                    analysis.setWhPhrase(node.find("WH", true, null));
                }

                if (analysis.getSubjectPhrase() != null && analysis.getVerb() != null &&  analysis.getObjectPhrase() != null) {
                    analysis.setType(UtteranceType.STATEMENT);
                } else if (analysis.getSubjectPhrase() == null && analysis.getVerb() != null && analysis.getObjectPhrase() != null) {
                    analysis.setType(UtteranceType.COMMAND);
                } else if (analysis.getSubjectPhrase() != null && analysis.getVerb() != null && analysis.getObjectPhrase() == null) {
                    analysis.setType(UtteranceType.QUESTION);
                } else {
                    analysis.setType(UtteranceType.UNKNOWN);
                }
            }
        }
        return analysis;
    }
}
