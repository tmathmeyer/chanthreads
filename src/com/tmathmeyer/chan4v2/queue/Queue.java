package com.tmathmeyer.chan4v2.queue;

public class Queue<E> implements IQueue<E> {
	
	private IQueue<E> rest = new EmptyQueue<E>();
	private E data;
	
	
	public Queue(E d){
		this.data = d;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public E peek() {
		return this.data;
	}

	@Override
	public IQueue<E> pop() {
		return this.rest;
	}

	@Override
	public IQueue<E> add(E element) {
		rest = rest.add(element);
		return this;
	}

}
