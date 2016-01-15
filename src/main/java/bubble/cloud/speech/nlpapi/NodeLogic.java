package bubble.cloud.speech.nlpapi;

import bubble.cloud.speech.nlpapi.model.Node;
import edu.stanford.nlp.trees.Tree;

import java.util.List;

/**
 * Logic for working with node tree.
 *
 * @author Tommi S.E. Laukkanen
 */
public class NodeLogic {

    /**
     * Finds first node by label prefix. Iterates current nodes children first before
     * propagating deeper.
     *
     * @param node the node
     * @param label the label (prefix)
     * @param recursive is search recursive
     * @param labelsToSkip the labels (prefixes) to skip
     * @return the first node found.
     */
    public static Node findFirstNodeByLabel(final Node node, final String label, boolean recursive,
                                            final List<String> labelsToSkip) {
        for (final Node childNode : node.getChildren()) {
            if (childNode.getLabel().startsWith(label)) {
                return childNode;
            }
        }

        if (recursive) {
            for (final Node childNode : node.getChildren()) {
                if (labelsToSkip != null) {
                    boolean skip = false;
                    for (final String labelToSkip : labelsToSkip) {
                        if (childNode.getLabel().startsWith(labelToSkip)) {
                            skip = true;
                            break;
                        }
                    }
                    if (skip) {
                        continue;
                    }
                }
                final Node resultNode = findFirstNodeByLabel(childNode, label, recursive, labelsToSkip);
                if (resultNode != null) {
                    return resultNode;
                }
            }
        }

        return null;
    }

    /**
     * Prints node recursively to string builder.
     * @param node the node
     * @param stringBuilder the string builder
     */
    public static void printNodeRecursively(final Node node, final StringBuilder stringBuilder) {
        for (int i = 0; i < node.getDepth(); i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(node.getLabel());
        stringBuilder.append('\n');
        for (final Node childNode : node.getChildren()) {
            printNodeRecursively(childNode, stringBuilder);
        }
    }

    /**
     * Populates node recursively.
     *
     * @param tree the tree
     * @param node the node
     * @param depth the current depth
     */
    public static void populateNodeRecursively(final Tree tree, final Node node, int depth) {
        node.setLabel(tree.label().value());
        node.setDepth(depth);
        for (final Tree childTree : tree.getChildrenAsList()) {
            final Node childNode = new Node();
            node.getChildren().add(childNode);
            populateNodeRecursively(childTree, childNode, depth + 1);
        }
    }
}
