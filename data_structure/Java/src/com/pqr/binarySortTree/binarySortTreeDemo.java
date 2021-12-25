package com.pqr.binarySortTree;

/**
 * @file: binarySortTree.java
 * @time: 2021/4/29 9:01 PM
 * @Author by Pking
 * <p>
 * 二叉排序树又叫二叉搜索树
 */
public class binarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加节点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //nifixOrder
        System.out.println("中序遍历");
        binarySortTree.nifixOrder(); // 1 2 3 5 7 9 10 12

        //del node
        //binarySortTree.delNode(2);
        //binarySortTree.delNode(1);
        //binarySortTree.delNode(7);
        //binarySortTree.delNode(10);
        System.out.println("after delete");
        binarySortTree.nifixOrder();

        /**
         * //对删除结点的各种情况的思路分析:
         *
         *        7
         *    3     10
         *  1   5 9    12
         * n 2
         *
         * 情况一:
         * 删除叶子节点 (比如:2, 5, 9, 12)
         * 思路
         * (1) 需求先去找到要删除的结点 targetNode
         * (2) 找到 targetNode 的 父结点 parent
         * (3) 确定 targetNode 是 parent 的左子结点 还是右子结点
         * (4) 根据前面的情况来对应删除
         * 左子结点 parent.left = null
         * 右子结点 parent.right = null;
         *
         * 情况二: 删除只有一颗子树的节点 比如 1
         * 思路
         * (1) 需求先去找到要删除的结点 targetNode
         * (2) 找到 targetNode 的 父结点 parent
         * (3) 确定 targetNode 的子结点是左子结点还是右子结点
         * (4) targetNode 是 parent 的左子结点还是右子结点
         * (5) 如果 targetNode 有左子结点
         * 5.1 如果 targetNode 是 parent 的左子结点 parent.left = targetNode.left;
         * 5.2 如果 targetNode 是 parent 的右子结点 parent.right = targetNode.left;
         * (6) 如果 targetNode 有右子结点
         * 6.1 如果 targetNode 是 parent 的左子结点 parent.left = targetNode.right;
         * 6.2 如果 targetNode 是 parent 的右子结点 parent.right = targetNode.right;
         *
         * 情况三 : 删除有两颗子树的节点. (比如:7, 3，10 ) 思路
         * (1) (2) (3) (4) (5) (6)
         * 需求先去找到要删除的结点 targetNode
         * 找到 targetNode 的 父结点 parent
         * 从 targetNode 的右子树找到最小的结点
         * 用一个临时变量，将 最小结点的值保存 temp 删除该最小结点
         * targetNode.value = temp
         * **/

    }
}

//创建bst
class BinarySortTree {
    private Node root;

    //添加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node getRoot(){
        return root;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }


    //查找要删除的节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除node节点的右子树的最小值，并返回该最小值
    public int delRightTreeMinNode(Node node) {
        Node target = node.right;
        //循环查找左节点，找到最小值
        while (target.left != null) {
            target = target.left;
        }

        //删除最小节点
        delNode(target.value);
        return target.value;
    }


    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //找targetNode的父节点
            Node parent = searchParent(value);

            //如果删除的是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断是左子还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }


            } else if (targetNode.left != null && targetNode.right != null) { //删除有两颗子树的节点
                //从 targetNode 的右子树找到最小的结点
                //用一个临时变量，将 最小结点的值保存 temp 删除该最小结点
                //targetNode.value = temp

                //int minVal = delRightTreeMinNode(targetNode);
                //targetNode.value = minVal;
                targetNode.value = delRightTreeMinNode(targetNode);

            } else {//删除只有一个子树的节点 要么左；要么右
                //如果只有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果是parent的左子
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//要删的节点有右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }


        }
    }

    //中序遍历
    public void nifixOrder() {
        if (root != null) {
            root.nifixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }
}


class Node {
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) { //查找的 值< 当前节点，左递归查找
            //左为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//右递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //查找的值小于当前节点的值
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }

    }


    //add node
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }


    //中序遍历二叉树
    public void nifixOrder() {
        if (this.left != null) {
            this.left.nifixOrder();
        }
        System.out.println(this);

        if (this.right != null) {
            this.right.nifixOrder();
        }
    }

}
