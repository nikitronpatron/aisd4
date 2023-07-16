public class Main{
    private NodeRB root;
    private class NodeRB {
        private int value;
        private Color color;
        private NodeRB left;
        private NodeRB right;

        @Override
        public String toString(){
            return "NodeRedblack: " + "value = " + value + ", color = " + color + "\n";
        }

    }
    private enum Color {
        BLACK,
        RED;
    }

    public boolean addValue (int value){
        if (root != null){
            boolean result = addNodeToRBTree(root,value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return  result;
        } else {
            root = new NodeRB();
            root.value = value;
            root.color = Color.BLACK;
            return  true;
        }
    }
    private boolean addNodeToRBTree (NodeRB noderb, int value) {
        if (noderb.value == value) {
            return false;
        } else {
            if (noderb.value > value){
                if (noderb.left!=null){
                    boolean result = addNodeToRBTree(noderb.left,value);
                    noderb.left = rebalance(noderb.left);
                    return result;
                } else {
                    noderb.left = new NodeRB();
                    noderb.left.color = Color.RED;
                    noderb.left.value = value;
                    return true;
                }
            } else {
                if (noderb.right!=null){
                    boolean result = addNodeToRBTree(noderb.right, value);
                    noderb.right = rebalance(noderb.right);
                    return result;
                } else {
                    noderb.right = new NodeRB();
                    noderb.right.color = Color.RED;
                    noderb.right.value = value;
                    return true;
                }
            }
        }

    }
    private NodeRB rebalance(NodeRB noderb){
        NodeRB result = noderb;
        boolean necessary;
        do {
            necessary = false;
            if (result.right!=null && result.right.color==Color.RED && (result.left==null || result.left.color == Color.BLACK)){
                necessary = true;
                result = turnRight(result);
            }
            if (result.left!=null && result.left.color==Color.RED && result.left.left!=null && result.left.left.color==Color.RED) {
                necessary = true;
                result = turnLeft(result);
            }
            if (result.left!=null && result.left.color==Color.RED && result.right!=null && result.right.color==Color.RED){
                necessary = true;
                changeColor(result);
            }
        } while (necessary);
        return result;
    }
    private void changeColor(NodeRB noderb){
        noderb.left.color = Color.BLACK;
        noderb.right.color = Color.BLACK;
        noderb.color = Color.RED;
    }
    private NodeRB turnLeft(NodeRB noderb){
        NodeRB leftCh = noderb.left;
        NodeRB middleCh = leftCh.right;
        leftCh.right = noderb; 
        noderb.left = middleCh;
        leftCh.color = noderb.color;
        noderb.color = Color.RED;
        return leftCh;
    }
    private NodeRB turnRight(NodeRB nodeRB){
        NodeRB rightCh = nodeRB.right;
        NodeRB middleCh = rightCh.left;
        rightCh.left = nodeRB;
        nodeRB.right = middleCh;
        rightCh.color = nodeRB.color;
        nodeRB.color = Color.RED;
        return rightCh;
    } 
}
