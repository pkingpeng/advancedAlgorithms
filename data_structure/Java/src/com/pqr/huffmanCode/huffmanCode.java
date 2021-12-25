package com.pqr.huffmanCode;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * @file: hummanCode.java
 * @time: 2021/4/28 11:25 PM
 * @Author by Pking
 */
public class huffmanCode {
    public static void main(String[] args) {

        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();//长度40

        //一步完成
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果:" + Arrays.toString(huffmanCodesBytes));
        //压缩后长度17

        /**
         * 分步
         *         System.out.println("contentBytes" + Arrays.toString(contentBytes));
         *         System.out.println(contentBytes.length); // 40个 char --> length 是40
         *
         *         List<Node> nodes = getNodes(contentBytes);
         *         System.out.println("nodes:" + nodes);
         *
         *         //nodes 创建的huffman二叉树
         *         System.out.println("huffman树");
         *         Node huffmanTreeRoot = createHuffmanTree(nodes);
         *         preOrder(huffmanTreeRoot);
         *
         *         //测试是否生成对应的huffman编码
         *         getCodes(huffmanTreeRoot, "", stringBuilderByCode);
         *
         *         //重载方法以后
         *         Map<Byte, String> huffmanCodeMap = getCodes(huffmanTreeRoot);
         *         System.out.println("huffmanCodeTable" + huffmanCodeMap);
         *
         *         //测试压缩
         *         byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodeMap);
         *         System.out.println("huffmanCodeMap:" + Arrays.toString(huffmanCodeBytes));
         *
         * 输出结果
         * contentBytes[105, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 106, 97, 118, 97, 32, 100, 111, 32, 121, 111, 117, 32, 108, 105, 107, 101, 32, 97, 32, 106, 97, 118, 97]
         * 40
         * nodes:[Node{data=32, weight=9}, Node{data=97, weight=5}, Node{data=100, weight=1}, Node{data=101, weight=4}, Node{data=117, weight=1}, Node{data=118, weight=2}, Node{data=105, weight=5}, Node{data=121, weight=1}, Node{data=106, weight=2}, Node{data=107, weight=4}, Node{data=108, weight=4}, Node{data=111, weight=2}]
         * huffman树
         * Node{data=null, weight=40}
         * Node{data=null, weight=17}
         * Node{data=null, weight=8}
         * Node{data=108, weight=4}
         * Node{data=null, weight=4}
         * Node{data=106, weight=2}
         * Node{data=111, weight=2}
         * Node{data=32, weight=9}
         * Node{data=null, weight=23}
         * Node{data=null, weight=10}
         * Node{data=97, weight=5}
         * Node{data=105, weight=5}
         * Node{data=null, weight=13}
         * Node{data=null, weight=5}
         * Node{data=null, weight=2}
         * Node{data=100, weight=1}
         * Node{data=117, weight=1}
         * Node{data=null, weight=3}
         * Node{data=121, weight=1}
         * Node{data=118, weight=2}
         * Node{data=null, weight=8}
         * Node{data=101, weight=4}
         * Node{data=107, weight=4}
         * huffmanCodeTable{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
         * huffmanCodeMap:[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
         *
         *
         * **/

        //测试解码过程
        byte[] sourceBytes = decode(huffmanCodeMap, huffmanCodesBytes);
        System.out.println("原来的字符串:" + new String(sourceBytes));
        //byte[] = [105, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 106, 97, 118, 97, 32, 100, 111, 32, 121, 111, 117, 32, 108, 105, 107, 101, 32, 97, 32, 106, 97, 118, 97]
        //--> String = "i like like like java do you like a java"

        //测试压缩文件
        String srcFile = "/Users/Pking/IdeaProjects/algorithms&dataStructures/data_structure/Java/src/com/pqr/huffmanCode/src.jpg";
        String dstFile = "/Users/Pking/IdeaProjects/algorithms&dataStructures/data_structure/Java/src/com/pqr/huffmanCode/src.zip";

        zipFile(srcFile, dstFile);
        System.out.println("压缩完成～");


        //测试解压缩文件
        String zipFile = "/Users/Pking/IdeaProjects/algorithms&dataStructures/data_structure/Java/src/com/pqr/huffmanCode/src.zip";
        String dstFile2 = "/Users/Pking/IdeaProjects/algorithms&dataStructures/data_structure/Java/src/com/pqr/huffmanCode/src2.jpg";
        unZipFile(zipFile, dstFile2);
        System.out.println("解压成功");



    }

    //使用一个方法封装前面所有过程，便于直接调用
    //输入:bytes --> contentbytes
    //return: 霍夫曼编码压缩后的byte数组
    private static byte[] huffmanZip(byte[] contentBytes) {
        //统计节点的权重
        List<Node> nodes = getNodes(contentBytes);
        //根绝nodes 生成huffman树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //生成对应huffman编码
        Map<Byte, String> huffmanCodeMap = getCodes(huffmanTreeRoot);
        //根据huffman编码压缩byte数组
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodeMap);

        return huffmanCodeBytes;
    }


    //input --> 字节数组
    //return --> List: Node
    public static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> Nodes = new ArrayList<Node>();

        //遍历bytes, 统计每个byte出现的次数 --> Map[key,value];
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
//        for (byte b : bytes) {
//            counts.merge(b, 1, Integer::sum); //Integer::sum等价于 (a,b) --> a + b
//        }

        //每个key, value --> Node 并加入到nodes集合
        //遍历Map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            Nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return Nodes;
        //list --> nodes:[Node{data=32, weight=9}, Node{data=97, weight=5}, Node{data=100, weight=1}]
    }


    //通过nodes的list来创建 对应的哈夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }

        return nodes.get(0);
    }

    //hfm树 --> 生成hfm编码表 例如=01 a=100 d=11000 u=11001 e=1110 v=11011 i=101 y=11010 j=0010 k=1111 l=000 o=0011
    //1.hfm编码表 --> Map<Byte, String> 例如: 32 --> 01 97 --> 100 等
    static Map<Byte, String> huffmanCodeMap = new HashMap<Byte, String>();
    //2.生成编码表，需要不停的拼接路径，定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilderByCode = new StringBuilder();

    //将传入node节点中所有的叶子节点的编码获得，并放到huffmanCodeMap中
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        //node --> 节点
        //code --> 路径, left:0; right:1
        //stringbuilder --> 拼接路径
        StringBuilder stringBuilderByRoute = new StringBuilder(stringBuilder);
        stringBuilderByRoute.append(code);
        //用于拼接 每个叶子节点的code路径
        if (node != null) {
            if (node.data == null) {//date为null -->非叶子节点
                getCodes(node.left, "0", stringBuilderByRoute);
                getCodes(node.right, "1", stringBuilderByRoute);
            } else {
                //完成一个叶子节点的路径
                huffmanCodeMap.put(node.data, stringBuilderByRoute.toString());
            }
        }

    }

    //为了调用方便，overload getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }

        getCodes(root.left, "0", stringBuilderByCode);
        getCodes(root.right, "1", stringBuilderByCode);
        return huffmanCodeMap;
    }

    //功能：将字符串的byte[]，通过生成的hfmCodeTable，返回霍夫曼编码压缩后的byte[]
    //即输入:  "i like like like java do you like a java" --> [105, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 106, 97, 118, 97, 32, 100, 111, 32, 121, 111, 117, 32, 108, 105, 107, 101, 32, 97, 32, 106, 97, 118, 97]
    //根据huffmanCodeMap: huffmanCodeTable{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    //返回一个字符串"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
    //字符串对应的byte[] 即8位对应一个byte --> huffmanCodeBytes
    //huffmanCodeBytes[0] = 10101000(补码) --> byte[10101000 --> 补码: 11011000 = -88]
    //huffmanCodeBytes[1] = -88;
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodeMap.get(b));
        }

        //将"1010100010111111....."转成byte数组
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //或者 int len = (stringBuilder.length() + 7) / 8;

        byte[] huffmanCodeBytes = new byte[len]; //将stringbuilder每8位的放进byte
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String stringByte = "";
            if (i + 8 > stringBuilder.length()) {
                stringByte = stringBuilder.substring(i);
            } else {
                stringByte = stringBuilder.substring(i, i + 8);
            }
            //将8位stringbyte 转成byte
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(stringByte, 2); //二进制转换 10101000(补码) --> -88; 并将int型转换成byte
        }

        return huffmanCodeBytes;
    }

    //huffman编码解压
    //1. huffmanCodeBytes = [-88, -65, -56, -65, -56, -65, -55, 77 , -57, 6, -24, -14, -117, -4, -60, -90, 28] --> "101010001011111111001000101....."
    //2. "101010001011111111001000101....." 根据huffman编码表 --> "i like like like java do you like a java"

    //功能：补码返回byte的二进制字符串 eg: -88 --> 11011000
    //byte --> 二进制字符穿;
    //flag标志表示是否需要补高位(正数需要补), 最后一个字节不用补高位
    //true --> 补
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b; // b --> int
        if (flag) {
            temp |= 256; //正数需要补位
        }

        String str = Integer.toBinaryString(temp);//str是temp对应二进制的补码

        if (flag) {
            return str.substring(str.length() - 8); //取最后的8位
        } else {
            return str;
        }
    }

    //功能：压缩数据解码
    //huffmanCodeMap --> 霍夫曼编码表
    //huffmanCodeBytes --> 编码得到的字节数 eg:  [-88, -65, -56, -65, -56, -65, -55, 77 , -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //return 原来字符串对应的数组
    public static byte[] decode(Map<Byte, String> huffmanCodeMap, byte[] huffmanCodeBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanCodeBytes.length; i++) {
            byte b = huffmanCodeBytes[i];
            boolean flag = (i != huffmanCodeBytes.length - 1); //表示如果是数组最后一个byte 则为false --> 不补
            stringBuilder.append(byteToBitString(flag, b));
        }

        //System.out.println("字节数组对应的二进制字符串为:" + stringBuilder.toString());

        //按照huffman编码表进行解码
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
            map.put(entry.getValue(), entry.getKey());//调换: a:100 -->  100:a
        }

        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //10100100010111...
                //取出key 1
                String key = stringBuilder.substring(i, i + count);//i不动，移动count，直到匹配到一个字符
                b = map.get(key); // 101 --> 105: 'i'
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }

            list.add(b);
            i += count;
        }

        //list --> byte[]
        //System.out.println("decodeList:" + list);
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //文件压缩
    //srcFile --> 压缩文件的目录
    //dstFile --> 压缩后文件放到的目录
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);//输入流
            byte[] b = new byte[fileInputStream.available()];

            fileInputStream.read(b);// src --> b

            //获取文件对应的huffman编码表, 并压缩获得byte数组
            byte[] huffmanBytes = huffmanZip(b);
            //输出流，存放压缩文件
            outputStream = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的objectOutputStream
            objectOutputStream = new ObjectOutputStream(outputStream);
            //以对象流的方式写入huffman编码表和字节数组， 为了以后解压恢复原文件使用
            objectOutputStream.writeObject(huffmanBytes); //huffmanBytes = [105, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 106, 97, 118, 97, 32, 100, 111, 32, 121, 111, 117, 32, 108, 105, 107, 101, 32, 97, 32, 106, 97, 118, 97]
            objectOutputStream.writeObject(huffmanCodeMap);//huffmanCodeMap = {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                objectOutputStream.close();
                outputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    //读取压缩文件，并解压
    public static void unZipFile(String zipFile, String dstFile) {
        //文件输入流
        InputStream inputStream = null;
        //对象流
        ObjectInputStream objectInputStream = null;
        //输出流
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(zipFile);
            objectInputStream = new ObjectInputStream(inputStream);

            byte[] huffmanBytes = (byte[]) objectInputStream.readObject(); //object --> byte[]
            //读取huffman编码表
            Map<Byte, String> codes = (Map<Byte, String>) objectInputStream.readObject();
            //解码
            byte[] bytes = decode(codes, huffmanBytes);
            outputStream = new FileOutputStream(dstFile);

            outputStream.write(bytes);
          } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                objectInputStream.close();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空");
        }
    }
}

class Node implements Comparable<Node> {
    Byte data; //'a' --> 97
    int weight; //权值 --> 字符中 char出现的次数
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(@NotNull Node o) {
        return this.weight - o.weight;
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
//        //只打印data有值的
//        if(this.data != null){
//            System.out.println(this);
//        }

        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
