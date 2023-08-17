package com.hzq.nlp.util;

import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.apache.lucene.util.RamUsageEstimator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Huangzq
 * @description
 * @date 2023/6/7 11:01
 */
public class MemorySizeTool {

    public static void main(String[] args) {
        String s = "{\"classification_name\":\"Computer Science;Telecommunications\",\"classification_ori\":\"Computer Science;Telecommunications\",\"doc_title\":\"Ontology querying and its software component for design support in supply chains\",\"external_id\":\"00855JP1MPD86JPWMDD81JP1MPD08\",\"doc_type\":\"外文会议题录\",\"institution_name\":\"Univ Aizu, Fukushima, Japan\",\"institution_id\":\"\",\"subject_major\":\"\",\"first_author\":\"Paik, I\",\"first_classification_name\":\"Computer Science\",\"row_number\":131384278,\"author\":\"Paik, I;Akimoto, H\",\"doc_abstract_en\":\"Effective support of product design stage in the supply chain is important for raising the competitive power of a company. Existing technology for design support of supply chain has limitations on managing semantic data and handling logical operations, which agents will use for their intelligent and autonomous activities. In this paper, we suggest an ontology querying mechanism and software component for it, based on the supply chain network, for the agent's intelligent activities. This querying engine can service several operations, including reasoning and logical functions, together with the ontology of the specified domain of product attributes and its instance data. We defined operators to provide the necessary ontology query according to the operation category in the domain of design support in SCM, which will be provided as several interfaces in the form of server side components and Web service. Finally, we explain the meaning of our querying system, comparing with the early version and other querying systems.\",\"first_classification\":\"Computer Science\",\"region_id\":\"0034\",\"doc_abstract\":\"Effective support of product design stage in the supply chain is important for raising the competitive power of a company. Existing technology for design support of supply chain has limitations on managing semantic data and handling logical operations, which agents will use for their intelligent and autonomous activities. In this paper, we suggest an ontology querying mechanism and software component for it, based on the supply chain network, for the agent's intelligent activities. This querying engine can service several operations, including reasoning and logical functions, together with the ontology of the specified domain of product attributes and its instance data. We defined operators to provide the necessary ontology query according to the operation category in the domain of design support in SCM, which will be provided as several interfaces in the form of server side components and Web service. Finally, we explain the meaning of our querying system, comparing with the early version and other querying systems.\",\"classification\":\"Computer Science;Telecommunications\",\"doc_id\":\"1302026d93dc5f9ddf1bae44293b8c5b\",\"doc_search_score\":\"0\",\"publication_year\":\"2005\",\"doc_title_en\":\"Ontology querying and its software component for design support in supply chains\",\"first_institution_region\":\"Univ Aizu, Fukushima, Japan\",\"region\":\"un\",\"doc_title_cn\":\"用于供应链中的设计支持的本体查询及其软件组件\"}";
        JSONObject object = JSONUtil.parseObj(s);
        System.out.println(getSizeMb(object));
        System.out.println(getHumanSize(object));
    }

    public static String getSizeMb(Object obj) {
        System.setProperty("java.vm.name", "Java HotSpot(TM) ");
        long objectSize = ObjectSizeCalculator.getObjectSize(obj);
        BigDecimal o = new BigDecimal(objectSize);
        BigDecimal dvd = new BigDecimal(1024);
        BigDecimal divide = o.divide(dvd, 2, RoundingMode.HALF_UP).divide(dvd, 2, RoundingMode.HALF_UP);
        return divide + "Mb";
    }


    public static String getHumanSize(Object obj) {
        return RamUsageEstimator.humanSizeOf(obj);
    }
}
