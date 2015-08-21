## Colibri-Java ##

Colibri-Java is an implementation of Formal Concept Analysis in Java.
Formal concept analysis is an algebraic theory for binary relations ,
which can be represented as cross tables. It identifies all maximal
rectangles in such a table; these rectangles from a hierarchy, the
so-called concept lattice. The concept lattice gives insight into the
original cross table.

The implementation of formal concept analysis is designed as a library
but includes a small demo application that computes the concept lattice
for a relation read from a file.

## Overview ##

**`colibri/`    Java implementation**

**`doc/`        Papers**

The `doc/` directory contains Daniel Götzmann's Bachelor Thesis (in
German) about this implementation. In addition, a paper by Christian
Lindig explains the idea how to use formal concept analysis to mine data
for rules and exceptions from these rules. The implementation provides
iterators for this purpose.

## See Also ##

A similar library like this but implemented in Objective Caml can be
found at http://code.google.com/p/colibri-ml/. An older and less
advanced implementation in C can be found at
http://code.google.com/p/colibri-concepts/.
