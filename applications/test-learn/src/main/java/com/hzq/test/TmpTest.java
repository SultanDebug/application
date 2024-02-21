package com.hzq.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Huangzq
 * @description
 * @date 2023/12/13 21:04
 */
public class TmpTest {
    private final static String s = "asd";

    public static void test() {
        String test = "骨干网交换设备;无线转换设备;交换机;移动软交换设备;数据交换设备;集群交换设备;数据交换机;直通式交换机;网络中心交换机;以太网交换设备;路由器;网络交换设备;以太网交换机;核心路由器;数据中心交换机;无线通信设备;无线接入设备;无线通信模组;无线通信技术;无线通信芯片;无线发送设备;物联网设备;位置信号装置;电力信息系统;电力二次设备;电力通信设备;配电成套设备;电力监控设备;电力测控设备;电力载波通信设备;电力线宽带接入设备;电力通信终端设备;风光互补供电系统服务;电力运行辅助管理系统;电力遥信系统;电力通信设备柜;智能电力设施;智能配电设施;换电设备;电力成套设备;其他电力信息系统;电力调节设备;电力信号回路设备;电力系统控制设备;电力系统通信设备;电力通信传输设备;电力线载波通信设备;变电设备管理系统;输电设备管理系统;配电设备管理系统;电力通信设备箱;电信业务;电信行业信息系统;增值电信业务;电信业务运营支持系统;第一类基础电信业务;第二类基础电信业务;第一类增值电信业务;电信运营支持系统;卫星移动服务;固定通信业务;蜂窝移动通信业务;国内多方通信服务业务;固定网本地通信业务;卫星固定通信业务;基础电信业务;第二类增值电信业务;集群通信业务;国内通信设施服务业务;移动通信基站;电信工程;通信设备制造;工业无线通信设备;工业通信网关;工业通信设备;工业通信终端设备";
        Pattern pattern = Pattern.compile(";?([^;]{0,}交换设备[^;]{0,});?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(test);
        //提取括号中正则表达式中的元素[^;]{0,}交换设备a[^;]{0,}
        while (matcher.find()) {
            System.out.println(matcher.start(1));
            System.out.println(matcher.end(1));
            System.out.println(matcher.group(1));
        }
    }

}
