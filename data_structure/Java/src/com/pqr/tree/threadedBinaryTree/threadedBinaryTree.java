package com.pqr.tree.threadedBinaryTree;

/**
 * @file: threadedBinaryTree.java
 * @time: 2021/4/25 11:51 PM
 * @Author by Pking
 */
public class threadedBinaryTree {
    public static void main(String[] args) {
        //       1
        //    3     6
        //  8  10 14
        //测试中序线索二叉树
        HeroNode root = new HeroNode(1, "tom");
        HeroNode heroNode2 = new HeroNode(3, "tony");
        HeroNode heroNode3 = new HeroNode(6, "smith");
        HeroNode heroNode4 = new HeroNode(8, "mary");
        HeroNode heroNode5 = new HeroNode(10, "king");
        HeroNode heroNode6 = new HeroNode(14, "dim");

        //create manully
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);

        TBtree threadedBinaryTree = new TBtree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //test: eg --> 10
        HeroNode leftNode = heroNode5.getLeft(); // left --> 3
        HeroNode rightNode = heroNode5.getRight(); // right --> 1
        System.out.println("10号节点的前驱节点是" + leftNode);
        System.out.println("10号节点的后继节点是" + rightNode);

        //线索二叉树遍历的方法，不能普通中序遍历的方法
        System.out.println("使用线索化的方法遍历");
        threadedBinaryTree.threadedList(); // 8 3 10 1 14 6

    }
}


//定义binarytree
class TBtree {
    private HeroNode root;


    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //overload
    public void threadedNodes() {
        this.threadedNodes(root);
    }


    //对二叉树中序线索化 即对 node 线索化
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }

        //中序线索化 --> 先左子树;
        threadedNodes(node.getLeft());
        // 当前节点;
        // 处理前驱节点
        if (node.getLeft() == null) {
            //当前节点的左指针为空，则指向前驱节点
            node.setLeft(pre);
            //修改类型, 表示当前左边指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        //！！ 处理后需要当前节点的为下一个节点的前驱节点
        pre = node;

        //右子树
        threadedNodes(node.getRight());
    }

    //遍历中序线索化二叉树的方法
    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            //循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);

            //如果当前节点的右指针指向后继节点, 就一直输出
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }

            //不是 --> 替换
            node = node.getRight();
        }
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


class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //notion
    //1.leftType = 0 --> 左子树; 1 --> 前驱节点
    //2.rightType = 0 --> 右子树; 1 --> 后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }


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