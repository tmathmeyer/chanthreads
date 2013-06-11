package com.tmathmeyer.chan4v2.queue;

public interface IQueue<E>{
	/**
	 * 
	 * @return whether the queue is empty
	 */
	public boolean isEmpty();
	
	/**
	 * 
	 * @return the next element in the queue
	 */
	public E peek();
	
	/**
	 * 
	 * @return the rest of the queue
	 */
	public IQueue<E> pop();
	
	/**
	 * 
	 * @param element the element to add to the queue
	 * @return the new queue with the new elements
	 */
	public IQueue<E> add(E element);
}
