package online.pandm.demo.util;
import java.io.*;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @ClassName: XmlToJson
 * @Description: xml报文转换为json报文
 * @Author: Lenovo
 * @Date: 2020/9/19
 * @Version: V1.0
 **/
@Slf4j
public class XmlToJson {

    /**
     * 父节点,可扩展
     */
    private static final String[] ROOT_ELEMENT_NAMES = {"request"};
    /**
     * 0：说明该节点所有子节点均有子节点
     */
    private static final int CHILD_TYPE_0 = 0;
    /**
     * 1：说明该节点的所有子节点均无子节点
     */
    private static final int CHILD_TYPE_1 = 1;
    /**
     * 2：说明了该节点的子节点有些拥有子节点，有些不拥有
     */
    private static final int CHILD_TYPE_2 = 2;

    public static void main(String[] args) {
        String filepath = "D:\\document\\1.xml";
        Map<String, Object> map = mainMethod(filepath);

        JSONObject jsonObject = new JSONObject(map);

        System.out.println(jsonObject.toJSONString());
    }

    public static Map<String, Object> mainMethod(String filepath) {
        Map<String, Object> mapFinal = new LinkedHashMap<>();
        try {
            File file = new File(filepath);
            // 读取xml文件，封装为doc对象
            SAXReader saxreader = new SAXReader();

            Document doc = saxreader.read(file);
            Element rootElement = doc.getRootElement();
            // 调用递归方法
            mapFinal.put(rootElement.getName(), pack(rootElement));
        } catch (DocumentException e) {
            e.printStackTrace();
            log.error("转换异常!!!");
        }
        return mapFinal;
    }

    public static Object pack(Element rootElement) {
        // 对节点进行判断
        int flag = hasGradeChild(rootElement);
        // 存储本层的map,采用LinkedHashMap,保证的顺序
        Map<String, Object> mapThis = new LinkedHashMap<>();
        // 存储子节点的map，采用LinkedHashMap,保证的顺序
        Map<String, Object> mapChildren;
        // 存储子节点的list
        List<Object> listChildren = new ArrayList<>();
        // 获取节点迭代器
        Iterator<Element> iterator = rootElement.elementIterator();
        //主节点
        if(Arrays.asList(ROOT_ELEMENT_NAMES).contains(rootElement.getName())){
            while (iterator.hasNext()) {
                Element childElement = iterator.next();
                mapChildren = (Map<String, Object>) pack(childElement);
                mapThis.put(childElement.getName(), mapChildren);
            }
        }
        // 说明该节点所有子节点均有子节点,进入递归（非主节点）
        if (flag == CHILD_TYPE_0 && !Arrays.asList(ROOT_ELEMENT_NAMES).contains(rootElement.getName())) {
            // 依次继续对节点进行操作
            while (iterator.hasNext()) {
                Element childElement = iterator.next();
                mapChildren = (Map<String, Object>) pack(childElement);
                listChildren.add(mapChildren);
            }
            return listChildren;
        }
        // 说明该节点的所有子节点均无子节点,封装数据
        if (flag == CHILD_TYPE_1) {
            while (iterator.hasNext()) {
                Element childElement = iterator.next();
                mapThis.put(childElement.getName(),childElement.getData());
            }
        }
        // 说明了该节点的子节点有些拥有子节点，有些不拥有
        if (flag == CHILD_TYPE_2) {
            // 获取子节点个数
            int nodes = rootElement.elements().size();
            while (nodes >= 1) {
                nodes--;
                while (iterator.hasNext()) {
                    Element element = iterator.next();
                    //对节点进行判断
                    flag = hasGradeChild(element);
                    if (flag == 1) {
                        //对于子节点，如果只是普通的子节点，那么直接将数进行封装
                        // 封装如map,String,String
                        mapThis.put(element.getName(), element.getData());
                    } else{
                        //非普通子节点，那么进行递归
                        Object children =  pack(element);
                        if (children instanceof Map){
                            mapChildren = (Map<String, Object>) pack(element);
                            mapThis.put(element.getName(), mapChildren);
                        }else{
                            listChildren = (List<Object>) pack(element);
                            mapThis.put(element.getName(), listChildren);
                        }
                    }
                }
            }
        }
        return mapThis;
    }

    /**
     * 用于判断该节点的类型
     *  0：说明该节点所有子节点均有子节点
     *  1：说明该节点的所有子节点均无子节点
     *  2：说明了该节点的子节点有些拥有子节点，有些不拥有
     *
     * @param rootElement
     * @return
     */
    public static int hasGradeChild(Element rootElement) {
        // 初始为1，用与处理对没有子节点的节点进行判断
        int flag = CHILD_TYPE_1;
        StringBuilder flagArr = new StringBuilder();
        Iterator<Element> iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            // 获取入参rootElement节点的子节点
            Element element = iterator.next();
            // 判断是否有子节点
            if (element.elements().size() > 0) {
                flagArr.append("0");
            } else {
                flagArr.append("1");
            }
        }
        // 如果只包含0，说明该节点所有子节点均有子节点
        boolean contains = flagArr.toString().contains(String.valueOf(CHILD_TYPE_0));
        if (contains) {
            flag = CHILD_TYPE_0;
        }
        // 如果只包含1，说明该节点的所有子节点均无子节点
        boolean contains1 = flagArr.toString().contains(String.valueOf(CHILD_TYPE_1));
        if (contains1) {
            flag = CHILD_TYPE_1;
        }
        // 如果同时包含了,0,1,说明了该节点的子节点有些拥有子节点，有些不拥有
        if (contains1 && contains) {
            flag = CHILD_TYPE_2;
        }
        return flag;
    }

}