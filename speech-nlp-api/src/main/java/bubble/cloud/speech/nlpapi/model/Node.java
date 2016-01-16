package bubble.cloud.speech.nlpapi.model;

import bubble.cloud.speech.nlpapi.NodeLogic;
import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object for parse tree nodes.
 *
 * @author Tommi S.E. Laukkanen
 */
public class Node {
    /**
     * The node label.
     */
    private String label;
    /**
     * The lemma.
     */
    private String lemma;
    /**
     * The node depth.
     */
    private int depth;
    /**
     * The children of the node.
     */
    private List<Node> children = new ArrayList<Node>();

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node find(final String label, boolean recursive, final List<String> labelsToSkip) {
        return NodeLogic.findFirstNodeByLabel(this, label, recursive, labelsToSkip);
    }

    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        NodeLogic.printNodeRecursively(this, stringBuilder);
        return stringBuilder.toString();
    }

}
