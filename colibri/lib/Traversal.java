package colibri.lib;

public enum Traversal {
	/**
	 * Bottom-up and breadth-first traversal.
	 * Concepts will be returned in descending order
	 * of their attribute sets.
	 */
	BOTTOM_ATTR,
	
	/**
	 * Bottom-up and breadth-first traversal.
	 * Concepts will be returned in descending order
	 * of the size of their attribute sets,
	 * i.e. concepts containing more attributes will be returned
	 * before concepts containing fewer attributes.
	 */
	BOTTOM_ATTRSIZE,
	
	/**
	 * Bottom-up and breadth-first traversal.
	 * Concepts will be returned in ascending order
	 * of their object sets.
	 */
	BOTTOM_OBJ,
	
	/**
	 * Bottom-up and breadth-first traversal.
	 * Concepts will be returned in ascending order
	 * of the size of their object sets.
	 * i.e. concepts containing fewer objects will be returned
	 * before concepts containing more objects.
	 */
	BOTTOM_OBJSIZE,
	
	
	/**
	 * Bottom-up and depth-first traversal.
	 */
	BOTTOM_DEPTHFIRST,
	
	/**
	 * Bottom-up and breadth-first traversal.
	 * Concepts will be returned in ascending order
	 * of their attribute sets.
	 */
	TOP_ATTR,
	
	/**
	 * Top-down and breadth-first traversal.
	 * Concepts will be returned in ascending order
	 * of the size of their attribute sets.
	 * i.e. concepts containing fewer attributes will be returned
	 * before concepts containing more attributes.
	 */
	TOP_ATTRSIZE,
	
	/**
	 * Bottom-up and breadth-first traversal.
	 * Concepts will be returned in descending order
	 * of their object sets.
	 */
	TOP_OBJ,
	
	/**
	 * Top-down and breadth-first traversal.
	 * Concepts will be returned in descending order
	 * of the size of their object sets.
	 * i.e. concepts containing more objects will be returned
	 * before concepts containing fewer objects.
	 */
	TOP_OBJSIZE,
	
	/**
	 * Top-down and depth-first traversal.
	 */
	TOP_DEPTHFIRST;
}
