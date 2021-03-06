/**
 * 判断一棵二叉树是否是平衡二叉树
 */

 public class IsBalancedTree {
     public static class Node {
         public int value;
         public Node left;
         public Node right;

         public Node(int value) {
             this.value = value;
         }
     }

     /**
      * 需要定义返回值
      * @param head
      * @return
      */
     public static boolean isBalance1(Node head) {
         return process(head).isB;
     }

     public static class ReturnData {
         public boolean isB;
         public int h;

         public ReturnData(boolean isB, int h) {
             this.isB = isB;
             this.h = h;
         }
     }

     public static ReturnData process(Node node) {
         if (node == null) {
             return new ReturnData(true, 0);
         }
         ReturnData leftData = process(node.left);
         if (!leftData.isB) {//以当前节点为头结点的子树的左子树平衡
             return new ReturnData(false, 0);
         }
         ReturnData rightData = process(node.right);
         if (!rightData.isB) {//以当前节点为头结点的子树的右子树平衡
             return new ReturnData(false, 0);
         }
         if (Math.abs(leftData.h - rightData.h) > 1) {//左子树与右子树高度差不超过1
             return new ReturnData(false, 0);
         }
         return new ReturnData(true, Math.max(leftData.h, rightData.h));//以当前节点为头结点的子树为平衡二叉树
     }

     /**
      * 不定义返回值
      * @param head
      * @return
      */
     public static boolean isBalance2(Node head) {
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(head, 1, res);
        return res[0];
    }

    public static int getHeight(Node head, int level, boolean[] res) {
        if (head == null) {
            return level;
        }
        int lH = getHeight(head.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rH = getHeight(head.right, level + 1, res);
        if (!res[0]) {
            return level;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }
        return Math.max(lH, rH);
    }

     public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance1(head));

    }
 }
