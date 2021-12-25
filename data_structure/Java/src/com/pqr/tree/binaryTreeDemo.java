package com.pqr.tree;

/**
 * @file: binaryTreeDemo.java
 * @time: 2021/4/25 2:42 PM
 * @Author by Pking
 */
public class binaryTreeDemo {
    public static void main(String[] args) {
        //create binary tree
        BinaryTree binaryTree = new BinaryTree();
        //create node
        HeroNode root = new HeroNode(1, "songjiang");
        HeroNode node2 = new HeroNode(2, "wuyong");
        HeroNode node3 = new HeroNode(3, "lujunyi");
        HeroNode node4 = new HeroNode(4, "linchong");
        HeroNode node5 = new HeroNode(5, "guansheng");


        //notion: create binary tree manully first, then by recursion
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //test
        System.out.println("preOrder"); // pre: 1 -> 2 -> 3 -> 5 -> 4
        binaryTree.preOrder();

        System.out.println("nifixOrder"); //nifix: 2 -> 1 -> 5 -> 3 -> 4
        binaryTree.nifixOrder();

        System.out.println("postOrder"); //post: 2 -> 5 -> 4 -> 3 -> 1
        binaryTree.postOrder();

        //search
        System.out.println("preOrderSearch");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }
        System.out.println();

        System.out.println("nifixOrderSearch");
        HeroNode resNode1 = binaryTree.preOrderSearch(3);
        if (resNode1 != null) {
            System.out.printf("找到了，信息为 no=%d name=%s", resNode1.getNo(), resNode1.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 3);
        }
        System.out.println();

        System.out.println("postOrderSearch");
        HeroNode resNode2 = binaryTree.preOrderSearch(2);
        if (resNode2 != null) {
            System.out.printf("找到了，信息为 no=%d name=%s", resNode2.getNo(), resNode2.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 2);
        }
        System.out.println();


        //del
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder(); // 1,2,3,5,4
        binaryTree.delNode(5);
        System.out.println("删除后");
        binaryTree.preOrder();// 1,2,3,4
    }
}


//定义binarytree
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //del node
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                //recursion del
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不用删除");
        }
    }


    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOreder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //nifix
    public void nifixOrder() {
        if (this.root != null) {
            this.root.nifixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //post
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode nifixOrderSearch(int no) {
        if (root != null) {
            return root.nifixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}


//创建heronode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历的方法
    public void preOreder() {
        System.out.println(this); //先输出父节点
        //递归向左子树
        if (this.left != null) {
            this.left.preOreder();
        }

        //递归向右子树
        if (this.right != null) {
            this.right.preOreder();
        }
    }

    //中序遍历
    public void nifixOrder() {
        //左
        if (this.left != null) {
            this.left.nifixOrder();
        }
        //输出父节点
        System.out.println(this);

        //右边
        if (this.right != null) {
            this.right.nifixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //左
        if (this.left != null) {
            this.left.postOrder();
        }
        //右边
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    //yes --> int no
    //No -->fgsq null
    public HeroNode preOrderSearch(int no) {
        //比较当前节点
        if (this.no == no) {
            return this;
        }
        //1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        //2.如果左递归前序查找，找到结点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }

        if (resNode != null) {//说明左子树成功找到
            return resNode;
        }

        //1.左递归前序查找，找到结点，则返回，否继续判断
        //2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    //nifix Search
    public HeroNode nifixOrderSearch(int no) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.nifixOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入中序查找");
        //如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if (this.no == no) {
            return this;
        }

        //否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.nifixOrderSearch(no);
        }

        return resNode;
    }


    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }

        if (resNode != null) {//左子树找到
            return resNode;
        }

        //如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入后序查找");
        //如果左右子树都没有找到，就比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //递归删除节点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {
        //思路
        /**
         1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断 当前这个结点是不是需要删除结点.
         2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null; 并且就返回 (结束递归删除)
         3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将 this.right= null ;并且就返回 (结束递归删除)
         4. 如果第 2 和第 3 步没有删除结点，那么我们就需要向左子树进行递归删除 5. 如果第 4 步也没有删除结点，则应当向右子树进行递归删除.
         **/
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null) {
            this.right.delNode(no);
        }

    }

}