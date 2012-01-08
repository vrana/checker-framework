package checkers.flow.cfg.node;

import checkers.flow.util.HashCodeUtils;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;

/**
 * A node for a conditional or expression:
 * 
 * <pre>
 *   <em>expression</em> || <em>expression</em>
 * </pre>
 * 
 * @author Stefan Heule
 * 
 */
public class ConditionalOrNode extends Node {

	protected BinaryTree tree;
	protected Node lhs;
	protected Node rhs;

	// TODO: is this actually needed?
	protected Boolean truthValue;

	public ConditionalOrNode(BinaryTree tree, Node lhs, Node rhs,
			Boolean truthValue) {
		assert tree.getKind().equals(Kind.CONDITIONAL_OR);
		this.tree = tree;
		this.lhs = lhs;
		this.rhs = rhs;
		this.truthValue = truthValue;
	}

	public Node getLeftOperand() {
		return lhs;
	}

	public Node getRightOperand() {
		return rhs;
	}

	public Boolean getTruthValue() {
		return truthValue;
	}

	/**
	 * Guaranteed to return the same tree as {@link getTree}, but with a more
	 * specific type.
	 */
	public BinaryTree getBinaryTree() {
		return tree;
	}

	@Override
	public Tree getTree() {
		return getBinaryTree();
	}

	@Override
	public <R, P> R accept(NodeVisitor<R, P> visitor, P p) {
		return visitor.visitConditionalOr(this, p);
	}

	@Override
	public String toString() {
		return "(" + getLeftOperand() + " || " + getRightOperand() + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ConditionalOrNode)) {
			return false;
		}
		ConditionalOrNode other = (ConditionalOrNode) obj;
		return getLeftOperand().equals(other.getLeftOperand())
				&& getRightOperand().equals(other.getRightOperand());
	}
	
	@Override
	public int hashCode() {
		return HashCodeUtils.hash(getLeftOperand(), getRightOperand());
	}

}
