package com.libs;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface GraphNode {
/**
* Returns all the GraphNodes directly linked
* to this GraphNode.
* These are considered to be distance 1 from this node.
*/
Set<GraphNode> getDirectlyLinkedNodes();
}
