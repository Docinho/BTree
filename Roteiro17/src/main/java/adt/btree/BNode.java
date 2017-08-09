package adt.btree;

import java.util.Collections;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
	protected LinkedList<T> elements; // PODERIA TRABALHAR COM ARRAY TAMBEM
	protected LinkedList<BNode<T>> children; // PODERIA TRABALHAR COM ARRAY
												// TAMBEM
	protected BNode<T> parent;
	protected int order;

	public BNode(int order) {
		this.order = order;
		this.elements = new LinkedList<T>();
		this.children = new LinkedList<BNode<T>>();
	}

	@Override
	public String toString() {
		return this.elements.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if (obj != null) {
			if (obj instanceof BNode) {
				if (this.size() == ((BNode<T>) obj).size()) {
					resp = true;
					int i = 0;
					while (i < this.size() && resp) {
						resp = resp && this.getElementAt(i).equals(((BNode<T>) obj).getElementAt(i));
						i++;
					}
				}
			}
		}
		return resp;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public int size() {
		return this.elements.size();
	}

	public boolean isLeaf() {
		return this.children.size() == 0;
	}

	public boolean isFull() {
		return this.size() == order - 1;
	}

	public void addElement(T element) {
		this.elements.add(element);
		Collections.sort(elements);
	}

	public void removeElement(T element) {
		this.elements.remove(element);
	}

	public void removeElement(int position) {
		this.elements.remove(position);
	}

	public void addChild(int position, BNode<T> child) {
		this.children.add(position, child);
		child.parent = this;
	}

	public void removeChild(BNode<T> child) {
		this.children.remove(child);
	}

	public int indexOfChild(BNode<T> child) {
		return this.children.indexOf(child);
	}

	public T getElementAt(int index) {
		return this.elements.get(index);
	}

	protected void split() {
		int mid = (size()) / 2;

		BNode updateLeft = new BNode(this.order);
		for (int i = mid - 1; i >= 0; i--) {
			updateLeft.addElement(elements.get(i));
		
		}

		BNode updateRight = new BNode(this.order);
		for (int i = elements.size() - 1; i > mid; i--) {
			updateRight.addElement(elements.get(i));
		
		}

		if (!this.isLeaf()) {
			for (int i = 0; i <= mid; i++)
				updateLeft.addChild(i, children.get(i));

			for (int i = mid + 1; i < children.size(); i++)
				updateRight.addChild(i - mid - 1, children.get(i));

		}
		if (parent == null) {
			T midElement = elements.get(mid);
			this.elements = new LinkedList<T>();
			elements.add(midElement);
			this.children = new LinkedList<BNode<T>>();
			updateLeft.setParent(this);
			this.children.add(updateLeft);
			updateRight.setParent(this);
			this.children.add(updateRight);
		} else {
			
			int index = this.parent.indexOfChild(this);
			this.parent.removeChild(this);
			this.parent.addChild(index, updateLeft);
			this.parent.addChild(index + 1, updateRight);

			updateLeft.setParent(this.parent);
			updateRight.setParent(this.parent);

			this.promote();
		

		if (this.parent.size() >= getMaxChildren()) 
			this.parent.split();
}
	}

	protected void promote() {
		int mid = this.getElements().size() / 2;
		T element = this.getElementAt(mid);
				
		this.getParent().addElement(element);
	}

	public LinkedList<T> getElements() {
		return elements;
	}

	public void setElements(LinkedList<T> elements) {
		this.elements = elements;
	}

	public LinkedList<BNode<T>> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<BNode<T>> children) {
		this.children = children;
	}

	public BNode<T> copy() {
		BNode<T> result = new BNode<T>(order);
		result.parent = parent;
		for (int i = 0; i < this.elements.size(); i++) {
			result.addElement(this.elements.get(i));
		}
		for (int i = 0; i < this.children.size(); i++) {
			result.addChild(i, ((BNode<T>) this.children.get(i)).copy());
		}

		return result;
	}

	public BNode<T> getParent() {
		return parent;
	}

	public void setParent(BNode<T> parent) {
		this.parent = parent;
	}

	public int getMaxKeys() {
		return order - 1;
	}

	public int getMaxChildren() {
		return order;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
