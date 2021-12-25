package com.pqr.avl;

/**
 * @file: avlBinaryTreeDemo.java
 * @time: 2021/5/6 6:01 PM
 * @Author by Pking
 */
public class avlBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};

        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.nifixOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); // 3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
    }
}

class AVLTree {
    private Node root;

    //添加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node getRoot() {
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

    //左旋转
    public void leftRotate() {
        Node newNode = new Node(value);
        //新的节点左子树设置成当前节点的左子树
        newNode.left = this.left;
        //新节点的 右子树 设置成 当前节点的右子树的左子树
        newNode.right = this.right.left;
        //当前节点的 值改成 右子节点的值
        this.value = right.value;
        //当前节点的右子树 改成右子树的右子树
        this.right = this.right.right;
        //当前节点左子树设置为新节点
        this.left = newNode;
    }

    //右旋转
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.left = this.left.right;
        newNode.right = this.right;

        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;

    }

    //返回当前为根节点的树的 高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
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


        //添加完节点后
        //如果 右子树高度 - 左子树高度 > 1
        //需要左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果右子树的 左子树的高度 大于 右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
        }

        //添加完节点后
        //如果 左子树高度 - 右子树高度 > 1
        //需要右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果左子树的右子树的高度大于左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //当前节点的左子树左旋转
                left.leftRotate();
                //再对当前节点右旋转
                rightRotate();
            } else {
                rightRotate();
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
