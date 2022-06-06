package cn.com.tcc.ofa.erm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hsw
 * @date 2022/5/10 16:35
 */
public class MarcUtil {
    /**
     * 解析集合
     */
    static Map<String, String> map = new HashMap<>();

    public static Map<String, String> jxMarc(String marc) {
        originalMap();
        String m = marc.split("\\u001D")[0];
        String s1 = m.split("\\u001E")[0];
        String s2 = (String) s1.subSequence(5, 6);
        if (s2.equals("d")) {
            System.err.println("检测到数据已废除！错误发生在：标识区状态为‘d’");
            return map;
        }
        int s3 = Integer.parseInt(s1.substring(12, 17));
        String s4 = s1.substring(24, s3 - 1);
        if (s4.length() % 12 != 0) {
            System.err.println("检测到数据格式错误！错误发生在：地址目次区");
            return map;
        }
        //目次区集合
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < s4.length() / 12; i++) {
            list1.add(s4.substring(12 * i, 12 * (i + 1)));
        }
        //第一个数组是标识+目次区
        int k = m.split("\\u001E").length - 1;
        String[] sz = m.split("\\u001E");
        //记录区集合
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list2.add(sz[i + 1]);
        }
        if (list1.size() != list2.size()) {
            System.err.println("检测到目次区与记录区数量不匹配！错误发生在：目次区或数据区，"
                    + "目次区：" + list1.size() + "组，记录区：" + list2.size() + "条");
            return map;
        }
        //匹配
        for (int i = 0; i < list1.size(); i++) {
            matchingMap(list1.get(i).substring(0, 3), list2.get(i));
        }
        return map;
    }

    /**
     * 初始化map
     * @return
     */
    public static Map<String, String> originalMap() {
        //ISBN
        map.put("isbn", null);
        //标题
        map.put("title", null);
        //分册名
        map.put("divisionname", null);
        //分册号
        map.put("divisionnumber", null);
        //作者
        map.put("author", null);
        //价格
        map.put("oneprice", null);
        //尺寸
        map.put("size", null);
        //页数、x册
        map.put("page", null);
        //出版地
        map.put("published", null);
        //中图分类号
        map.put("clc", null);
        //出版社
        map.put("press", null);
        //出版时间
        map.put("pubdate", null);
        //语言
        map.put("language", null);
        //主题
        map.put("type", null);
        //内容、简介
        map.put("Remarks", null);
        return map;
    }

    /**
     * 匹配并赋值map
     * @param key1
     * @param key2
     */
    public static void matchingMap(String key1, String key2) {
        switch (key1) {
            case "010":
                //isbn
                if (key2.split("\\u001Fa").length > 1) {
                    String f1 = key2.split("\\u001Fa")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("isbn", f2);
                }
                //价格
                if (key2.split("\\u001Fd").length > 1) {
                    String f1 = key2.split("\\u001Fd")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("oneprice", f2);
                }
                break;
            case "101":
                //语言
                if (key2.split("\\u001Fa").length > 1) {
                    String f1 = key2.split("\\u001Fa")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("language", f2);
                }
                break;
            case "200":
                //作者
                if (key2.split("\\u001Ff").length > 1) {
                    String f1 = key2.split("\\u001Ff")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("author", f2);
                }
                //题名
                if (key2.split("\\u001Fa").length > 1) {
                    String f1 = key2.split("\\u001Fa")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("title", f2);
                }
                //分册号***可能不止一个'@h'
                if (key2.split("\\u001Fh").length > 1) {
                    String f1 = key2.split("\\u001Fh")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("divisionnumber", f2);
                }
                //分册名***可能不止一个'@i'
                if (key2.split("\\u001Fi").length > 1) {
                    String f1 = key2.split("\\u001Fi")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("divisionname", f2);
                }
                break;
            case "210":
                //出版地
                if (key2.split("\\u001Fa").length > 1) {
                    String f1 = key2.split("\\u001Fa")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("published", f2);
                }
                //出版社
                if (key2.split("\\u001Fc").length > 1) {
                    String f1 = key2.split("\\u001Fc")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("press", f2);
                }
                //出版时间
                if (key2.split("\\u001Fd").length > 1) {
                    String f1 = key2.split("\\u001Fd")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("pubdate", f2);
                }
                break;
            case "215":
                //尺寸
                if (key2.split("\\u001Fd").length > 1) {
                    String f1 = key2.split("\\u001Fd")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("size", f2);
                }
                //页数***可能是层数
                if (key2.split("\\u001Fa").length > 1) {
                    String f1 = key2.split("\\u001Fa")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("page", f2);
                }
                break;
            case "330":
                //内容
                if (key2.split("\\u001Fa").length > 1) {
                    String f1 = key2.split("\\u001Fa")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("Remarks", f2);
                }
                break;
            case "606":
                //主题
                if (key2.split("\\u001Fa").length > 1) {
                    String f1 = key2.split("\\u001Fa")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("type", f2);
                }
                break;
            case "690":
                //分类号
                if (key2.split("\\u001Fa").length > 1) {
                    String f1 = key2.split("\\u001Fa")[1];
                    String f2 = f1.split("\\u001F")[0];
                    map.put("clc", f2);
                }
                break;
            default:
                break;
        }
    }
}
