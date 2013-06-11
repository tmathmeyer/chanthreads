package com.tmathmeyer.chan4v2.queue;

public class EmptyQueue<E> implements IQueue<E> {

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public E peek() {
		return null;
	}

	@Override
	public IQueue<E> pop() {
		return this;
	}

	@Override
	public IQueue<E> add(E element) {
		return new Queue<E>(element);
	}

}
