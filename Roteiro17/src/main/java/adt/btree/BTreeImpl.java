package adt.btree;

import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		int height = -1;
		if (!this.isEmpty())
			height += height(root);
		return height;
	}

	private int height(BNode<T> node) {
		int height = -1;
		if (node != null && !node.isEmpty()) {
			height = 1;
			if (node.getChildren().size() > 0)
				height += height(node.getChildren().getFirst());
		}

		return height;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		BNode<T>[] array = new BNode[this.countingNodes(root)];
		if (!this.isEmpty()) {
			int i = 0;
			depthLeftOrder(array, i, root);
		}
		return array;
	}

	private int depthLeftOrder(BNode<T> array[], int index, BNode<T> node) {
		if (node != null) {
			array[index++] = node;
			for (int i = 0; i < node.getChildren().size(); i++) {
				index = depthLeftOrder(array, index, node.getChildren().get(i));
			}
		}
		return index;

	}

	private int countingNodes(BNode<T> node) {
		int count = 0;
		if (node != null && !node.isEmpty()) {
			count += 1;
			for (int i = 0; i < node.getChildren().size(); i++)
				count += countingNodes(node.getChildren().get(i));

		}
		return count;
	}

	@Override
	public int size() {
		return size(this.getRoot());
	}

	public int size(BNode<T> node) {
		int size = 0;
		if (node != null && !node.isEmpty()) {
			LinkedList<BNode<T>> children = node.getChildren();
			for (int i = 0; i < children.size(); i++)
				size += size(children.get(i));

			size += node.getElements().size();
		}
		return size;

	}

	@Override
	public BNodePosition<T> search(T element) {
		BNodePosition<T> node = new BNodePosition<T>();
		if (element != null)
			node = search(element, this.root);

		return node;
	}

	public BNodePosition<T> search(T element, BNode<T> node) {
		BNodePosition<T> returnNode = new BNodePosition<T>();
		if (node != null && !node.isEmpty()) {
			LinkedList<T> nodesList = node.getElements();
			int i = 0;
			T elementSearching = nodesList.get(i);
			while (i < nodesList.size() && elementSearching.compareTo(element) < 0) {
				elementSearching = nodesList.get(i);
				i++;
			}

			if (elementSearching.compareTo(element) > 0) {
				if (i > 0)
					i--;
				returnNode = search(element, node.getChildren().get(i));
			} else if (elementSearching.compareTo(element) == 0) {
				if (i > 0)
					i--;
				returnNode = new BNodePosition<T>(node, i--);
			} else {
				if (node.getChildren().size() > i)
					returnNode = search(element, node.getChildren().get(i));
			}
		}
		return returnNode;
	}

	@Override
	public void insert(T element) {
		if (element != null)
			// if (this.root.isEmpty())
			// this.root.addElement(element);
			// else
			insert(element, this.root);
	}

	public void insert(T element, BNode<T> node) {

		if (node != null) {

			if (node.isLeaf()) {
				node.addElement(element);
			} else {

				LinkedList<T> nodeList = node.getElements();

				int i = 0;
				boolean working = true;
				while (i < nodeList.size() && working) {
					if (nodeList.get(i).compareTo(element) > 0) {
						if (node.getChildren().get(i) != null && !node.getChildren().get(i).isEmpty())
							insert(element, node.getChildren().get(i));

						working = false;
					} else if (nodeList.get(i).compareTo(element) < 0) {
						if ((i + 1 < nodeList.size() && nodeList.get(i + 1).compareTo(element) > 0)
								|| (i + 1 == nodeList.size())) {
							if (node.getChildren().get(i + 1) != null && !node.getChildren().get(i + 1).isEmpty())
								insert(element, node.getChildren().get(i + 1));
							working = false;
						}
					}
					i++;
				}
			}

			if (node.getElements().size() >= node.getOrder()) {
				if (node.getParent() != null) {
					if (node.getParent().getChildren().contains(node))
						node.split();
				} else
					node.split();

			}
		}
	}

	private void split(BNode<T> node) {
		node.split();
	}

	private void promote(BNode<T> node) {
		node.promote();
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
