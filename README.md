# Project Description

> Standalone java file that implements [Floyd's algorithm](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm) to find all-pairs shortest paths for a given weighted digraph. 

# Project Features

  - reads input weighted digraph from file via command line (see floydTest.txt). The input file consists of the following:
    - The input file may have zero or more lines of comments starting with 'c ' 
    - followed by an integer that represents the number of nodes in the graph 
    - followed by one edge per line. A triplet of integers represents a directed edge, where the third integer is the weight of the edge         from the first to the second integer. i.e. an edge 1  2  3 means that the weight of the edge from 1 to 2 is 3. 
  - Outputs results to file (see floydOutput.txt). Outputs each of the input and output graphs as 2-D matrices in the traditional (i.e.       row-colum arrangment) format without any extra punctuations. Also can output the path (not just the edge) for a pair of nodes and it's     weight.  
