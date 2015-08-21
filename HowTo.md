The purpose of this document is to provide a short introduction about how to use the library. More information about the individual methods can be found in the comments included in the source files.

You can download the source code from this subversion repository anonymously:
```
svn checkout http://colibri-java.googlecode.com/svn/trunk/ colibri-java
```

The relevant classes and interfaces are contained in the package `colibri.lib`.

## Step 1: Create a Relation ##

Formal concept analysis is an algebraic theory for binary relations between a set of objects and a set of attributes. Those relations are represented by the interface `Relation`. You can specify a relation by adding object-attribute pairs to an initially empty relation.

Create an initially empty `Relation`:
```
Relation rel = new TreeRelation();
```

The `add` method of `Relation` is used to add object-attribute pairs to the relation. For example,
```
rel.add(some_object, some_attribute);
```
adds the pair _(some\_object, some\_attribute)_ to the relation `rel`.

Note that all objects added to the relation must be mutually comparable. The same holds for attributes.

## Step 2: Create a Lattice ##

When `rel` is complete, a `Lattice` object can be generated from it. There are two implementations of the `Lattice` interface, `RawLattice` and `HybridLattice`. In general, `HybridLattice` performs much faster than `RawLattice`.

In order to generate a `Lattice` from `rel`, pass `rel` to the constructor:

```
Lattice lattice = new HybridLattice(rel);
```

## Step 3: Use the Lattice ##

The generated `lattice` represents the concept lattice of `rel`. It can be traversed manually or with iterators that iterate over the entire lattice. The methods of the class `Lattice` return instances of `Concept` and `Edge`.

  * `Concept` represents a formal concept, i.e. a pair of an object set and an attribute set. Those sets can be obtained by calling the methods `getObjects()` and `getAttributes()` on the `Concept`.

  * `Edge` represents a pair of two concepts that are neighbors of each other in a concept lattice. An `Edge` object corresponds to an edge in the Hasse diagram of the lattice. The upper and lower neighbor belonging to an `Edge` can be obtained by calling the methods `getUpperNeighbor()` and `getLowerNeighbor()`, respectively.

### Traversing the lattice with iterators ###

#### Iterating over all concepts ####

The method `conceptIterator(Traversal trav)` returns an iterator that iterates over all concepts of a concept lattice. The parameter `trav` specifies how the lattice will be traversed.

For example, `lattice.conceptIterator(Traversal.TOP_ATTRSIZE)` returns an iterator that iterates over all concepts of `lattice`. The argument `Traversal.TOP_ATTRSIZE` specifies, that the iterator will traverse the lattice from top to bottom and that concepts having fewer attributes will be returned before concepts having more attributes. That implies that a concept will always be returned before its subconcepts.

The library supports several traversals, including the following:
  * `Traversal.TOP_ATTRSIZE`: Top-down traversal, concepts having fewer attributes are returned before concepts having more attributes.
  * `Traversal.TOP_OBJSIZE`: Top-down traversal, concepts having more objects are returned before concepts having fewer objects.
  * `Traversal.BOTTOM_ATTRSIZE`: Bottom-up traversal, concepts having more attributes are returned before concepts having fewer attributes.
  * `Traversal.BOTTOM_OBJSIZE`: Bottom-up traversal, concepts having fewer objects are returned before concepts having more objects.

Which of those traversals you should choose depends on your intentions. If, for example, you want to iterate over all concepts that have at least ten objects it's best to choose `Traversal.TOP_OBJSIZE`:

```
//get the iterator
Iterator<Concept> it = lattice.conceptIterator(Traversal.TOP_OBJSIZE);

while(it.hasNext()) {
    Concept c = it.next();
	if (c.getObjects().size() >= 10) {
		//do something with the concept, e.g. print it:
		System.out.println(c);
	}
	else {
		//Since concepts are returned in decreasing order of their
		//object sizes, the iteration can be aborted when the iterator
		//returns a concept having less than 10 objects the first time.
		break;
	}
}
```

#### Iterating over all edges ####

The method `edgeIterator(Traversal trav)` returns an iterator that iterates over all edges (i.e. pairs of concepts that are neighbors of each other) of a concept lattice. The parameter `trav` specifies how the lattice will be traversed.

For example, `lattice.edgeIterator(Traversal.TOP_ATTRSIZE)` returns an iterator that iterates over all edges of `lattice`. The argument `Traversal.TOP_ATTRSIZE` specifies, that the iterator will traverse the lattice from top to bottom and that an edge whose upper neighbor has fewer attributes will be returned before an edge whose upper neighbor has more attributes. Other traversals are also supported.

### Traversing the lattice manually ###

There are also methods that allow to traverse the lattice manually:

  * `top()` returns the top concept of the lattice, i.e. the concept that contains all objects.
  * `bottom()` returns the bottom concept of the lattice, i.e. the concept that contains all attributes.
  * `lowerNeighbors(Concept c)` returns an iterator over the lower neighbors of the concept `c`.
  * `upperNeighbors(Concept c)` returns an iterator over the upper neighbors of the concept `c`.
  * `conceptFromObjects(Collection objects)` computes the least concept that contains all objects contained in the collection `objects`.
  * `conceptFromAttributes(Collection attributes)` computes the greatest concept that contains all attributes contained in the collection `attributes`.




