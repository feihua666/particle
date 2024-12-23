package com.particle.area.test;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.area.client.dto.command.AreaCreateCommand;
import com.particle.area.client.dto.command.AreaUpdateCommand;
import com.particle.area.client.dto.command.representation.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.domain.enums.AreaType;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.global.tool.http.HttpClientTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.map.BaiduMapTool;
import com.particle.global.tool.pinyin.Pinyin;
import com.particle.global.tool.pinyin.PinyinTool;
import lombok.Getter;
import lombok.Setter;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.hc.core5.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by yangwei
 * Created at 2020/11/19 17:48
 */
@SpringBootTest
public class AreaImportTest{



    @Getter
    @Setter
    public static class AreaTree{
        private AreaDO area;
        private String parentNames = "";
        private List<AreaTree> children;
        private String childrenUrl;
    }

    private static Map<AreaType,String> areaTypeMap = new HashMap<>();
    static {
        areaTypeMap.put(AreaType.province,"1189838482257219585");
        areaTypeMap.put(AreaType.city,"1189838538121154562");
        areaTypeMap.put(AreaType.county,"1189838597441196034");
        areaTypeMap.put(AreaType.country,"1189838938333253633");
        areaTypeMap.put(AreaType.town,"1785175340499382273");
    }

    private static String areaListUrl = "http://localhost:8080/admin/web/area/list";
    private static String areaCreateUrl = "http://localhost:8080/admin/web/area/create";
    private static String areaUpdateUrl = "http://localhost:8080/admin/web/area/update";

    private static String originDomain = "http://www.stats.gov.cn";
    private static String delegateDomain = "http://localhost/stats_gov_cn";

    private static String cTokenId = "b8dc74c5-b716-4a13-a959-e7669fa55767";

    public static void main(String[] args) throws IOException, BadHanyuPinyinOutputFormatCombination, ParseException, URISyntaxException {
        importTest();

    }

    /**
     * 执行保存操作
     * @param areaTree
     * @param parentId
     */
    public static void save(AreaTree areaTree, String parentId) throws IOException, ParseException, URISyntaxException {


        AreaVO areaVO = getAreaVO(areaTree, parentId);
        System.out.println("areaVO={}" + JsonTool.toJsonStr(areaVO));

        if (areaVO == null || areaVO.getLatitude() == null) {
            String address = areaTree.getParentNames().toString() + "" + areaTree.getArea().getName();
            Map<String, Object> r = BaiduMapTool.getGeo(address, "69a5a1e7260cd2cd298c33666e436530");

            System.out.println("r={}" + JsonTool.toJsonStr(r));
            if ("0".equals(r.get("status").toString())) {
                Map<String, Object> result = (Map<String, Object>) r.get("result");
                Map<String, Object> location = (Map<String, Object>) result.get("location");
                areaTree.getArea().setLongitude(location.get("lng").toString());
                areaTree.getArea().setLatitude(location.get("lat").toString());
            }
        }


        // 不存在，添加
        if (areaVO == null) {
            areaVO = doCreate(areaTree, parentId);
        }else {
            if (areaVO.getLatitude() == null) {
                doUpdate(areaTree, parentId,areaVO);
            }
        }

        if (!CollectionUtil.isEmpty(areaTree.getChildren())) {
            for (AreaTree child : areaTree.getChildren()) {
                save(child, areaVO.getId().toString());
            }
        }
    }

    /**
     * 获取
     * @param areaTree
     * @param parentId
     * @return
     * @throws IOException
     */
    private static AreaVO getAreaVO(AreaTree areaTree, String parentId) throws IOException, ParseException, URISyntaxException {
        // 构建查询参数，判断是否已经存在

        AreaQueryListCommand listCommand = new AreaQueryListCommand();
        if (StrUtil.isNotEmpty(areaTree.getArea().getCode())) {
            listCommand.setCode(areaTree.getArea().getCode());
        }else {
            listCommand.setName(areaTree.getArea().getName());
        }
        listCommand.setTypeDictId(areaTree.getArea().getTypeDictId());
        listCommand.setParentId(Optional.ofNullable(parentId).map(Long::valueOf).orElse(null));

        String listCommandJsonStr = JsonTool.toJsonStr(listCommand);
        Map<String, String> listCommandMap = JSONUtil.parseObj(listCommandJsonStr).toBean(Map.class);
        Iterator<Map.Entry<String, String>> iterator = listCommandMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if (next.getValue() == null) {
                iterator.remove();
            }
        }
        String listParamsQuery = URLUtil.buildQuery(listCommandMap, Charset.forName("UTF-8"));
        String listResponse = HttpClientTool.get(areaListUrl + "?" + listParamsQuery, config());
        JSONArray listData = JSONUtil.parseObj(listResponse).getJSONArray("data");
        JSONObject listDataFirst = null;
        if (listData != null && listData.size() > 0) {
            // 因为是前缀匹配
            for (Object listDatum : listData) {
                JSONObject listDatum1 = (JSONObject) listDatum;
                if (StrUtil.isNotEmpty(areaTree.getArea().getCode()) && areaTree.getArea().getCode().equals(listDatum1.getStr("code"))) {
                    return JSONUtil.toBean(listDatum1, AreaVO.class);
                } else if (areaTree.getArea().getName().equals(listDatum1.getStr("name"))) {
                    return JSONUtil.toBean(listDatum1, AreaVO.class);
                }
            }
        }
        return null;
    }

    /**
     * 添加
     * @param areaTree
     * @param parentId
     * @return
     * @throws IOException
     */
    private static AreaVO doCreate(AreaTree areaTree, String parentId) throws IOException, ParseException, URISyntaxException {

        AreaCreateCommand areaCreateCommand = new AreaCreateCommand();
        areaCreateCommand.setCode(areaTree.getArea().getCode());
        areaCreateCommand.setName(areaTree.getArea().getName());
        areaCreateCommand.setNameSimple(areaTree.getArea().getNameSimple());
        areaCreateCommand.setTypeDictId(areaTree.getArea().getTypeDictId());

        areaCreateCommand.setLatitude(areaTree.getArea().getLatitude());
        areaCreateCommand.setLongitude(areaTree.getArea().getLongitude());

        areaCreateCommand.setSeq(areaTree.getArea().getSeq());
        areaCreateCommand.setParentId(Optional.ofNullable(parentId).map(Long::valueOf).orElse(null));

        String jsonStr = JsonTool.toJsonStr(areaCreateCommand);
        String postJson = HttpClientTool.postJson(areaCreateUrl, jsonStr, config());

        System.out.println("doCreate result = {}" + postJson);
        JSONObject data = JSONUtil.parseObj(postJson).getJSONObject("data");
        return JSONUtil.toBean(data, AreaVO.class);

    }
    /**
     * 更新，主要是更新经度和纬度
     * @param areaTree
     * @param parentId
     * @return
     * @throws IOException
     */
    private static AreaVO doUpdate(AreaTree areaTree, String parentId,AreaVO oldAreaVO) throws IOException, ParseException, URISyntaxException {

        AreaUpdateCommand areaUpdateCommand = new AreaUpdateCommand();
        areaUpdateCommand.setId(oldAreaVO.getId());
        areaUpdateCommand.setCode(oldAreaVO.getCode());
        areaUpdateCommand.setName(oldAreaVO.getName());
        areaUpdateCommand.setNameSimple(oldAreaVO.getNameSimple());
        areaUpdateCommand.setTypeDictId(oldAreaVO.getTypeDictId());

        areaUpdateCommand.setLatitude(areaTree.getArea().getLatitude());
        areaUpdateCommand.setLongitude(areaTree.getArea().getLongitude());

        areaUpdateCommand.setSeq(oldAreaVO.getSeq());
        areaUpdateCommand.setParentId(Optional.ofNullable(parentId).map(Long::valueOf).orElse(null));

        areaUpdateCommand.setVersion(oldAreaVO.getVersion());

        String jsonStr = JsonTool.toJsonStr(areaUpdateCommand);
        String putJson = HttpClientTool.putJson(areaUpdateUrl, jsonStr, config());
        System.out.println("doUpdate result = {}" + putJson);
        JSONObject data = JSONUtil.parseObj(putJson).getJSONObject("data");
        return JSONUtil.toBean(data, AreaVO.class);

    }

    /**
     * 请求头配置
     * @return
     */
    private static HttpClientTool.ExtConfig config() {
        return HttpClientTool.ExtConfig.builder().build().addHeader("c-token-id", cTokenId);
    }
    /**
     * 省
     * @return
     * @throws IOException
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static List<AreaTree> province() throws IOException, BadHanyuPinyinOutputFormatCombination {

        List<AreaTree> result = new ArrayList<>();


        // http://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/index.html
        // 从国家统计局获取的数据，但本地访问有问题，证书问题，需要本地nginx转发一下
        URL url = URLUtil.url(delegateDomain + "/sj/tjbz/tjyqhdmhcxhfdm/2023/index.html");
        Document document = Jsoup.parse(url, 5000);
        Elements elements = document.select(".provincetable .provincetr a");
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            String text = element.text();
            String href = element.attribute("href").getValue();
            AreaTree areaTree = newAreaTree(newAreaDO(text,null, AreaType.province.itemValue(),i * 10));
            areaTree.setChildrenUrl(href);
            city(areaTree);
            result.add(areaTree);
        }
        return result;
    }

    /**
     * 市
     * @param provinceTree
     * @return
     * @throws IOException
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static List<AreaTree> city(AreaTree provinceTree) throws IOException, BadHanyuPinyinOutputFormatCombination {

        List<AreaTree> result = new ArrayList<>();

        URL url = URLUtil.url(provinceTree.getChildrenUrl());
        Document document = Jsoup.parse(url, 5000);

        Elements elements = document.select(".citytable .citytr");
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            Elements children = element.children();
            // 编码
            Element element1 = children.get(0);
            Element element_1 = element1.children().get(0);
            // 名称
            Element element2 = children.get(1);
            Element element2_2 = element2.children().get(0);

            String text1 = element1.text();
            String text2 = element2.text();
            String href1 = element_1.attribute("href").getValue();
            provinceTree.getArea().setCode(text1.substring(0, 2) + "0000000000");
            AreaTree areaTree = newAreaTree(newAreaDO(text2,text1, AreaType.city.itemValue(),provinceTree.getArea().getSeq() + i * 10));
            areaTree.setChildrenUrl(href1);

            areaTree.setParentNames(provinceTree.getParentNames() + provinceTree.getArea().getName());

            county(areaTree);
            result.add(areaTree);
        }
        provinceTree.setChildren(result);
        return result;
    }

    /**
     * 区
     * @param cityTree
     * @return
     * @throws IOException
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static List<AreaTree> county(AreaTree cityTree) throws IOException, BadHanyuPinyinOutputFormatCombination {

        System.out.println("countyPageUrl=" + cityTree.getChildrenUrl());
        List<AreaTree> result = new ArrayList<>();

        AreaType areaType = AreaType.county;

        URL url = URLUtil.url(cityTree.getChildrenUrl());
        Document document = Jsoup.parse(url, 5000);

        Elements elements = document.select(".countytable .countytr");

        if (elements.size() == 0) {
            areaType = AreaType.town;
            elements = document.select(".countytable .towntr");

        }
        for (int i = 0; i < elements.size(); i++) {

            Element element = elements.get(i);
            Elements children = element.children();
            // 编码
            Element element1 = children.get(0);
            Element element_1 = element1.children().get(0);
            // 名称
            Element element2 = children.get(1);
            Element element2_2 = element2.children().get(0);

            String text1 = element1.text();
            String text2 = element2.text();
            Attribute href = element_1.attribute("href");
            AreaTree areaTree = newAreaTree(newAreaDO(text2,text1, areaType.itemValue(),cityTree.getArea().getSeq() + i * 10));
            areaTree.setChildrenUrl(href == null? null: href.getValue());

            areaTree.setParentNames(cityTree.getParentNames() + cityTree.getArea().getName());

            result.add(areaTree);
        }
        cityTree.setChildren(result);
        return result;
    }

    /**
     * 构建对象
     * @param area
     * @return
     */
    public static AreaTree newAreaTree(AreaDO area) {
        AreaTree areaTree = new AreaTree();
        areaTree.setArea(area);

        return areaTree;
    }

    /**
     * 构建对象
     * @param name
     * @param code
     * @param type
     * @param seq
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static AreaDO newAreaDO(String name, String code, String type, Integer seq) throws BadHanyuPinyinOutputFormatCombination {
        Pinyin pinyin = PinyinTool.getPinyin(name);
        AreaDO area = new AreaDO();
        area.setName(name);
        area.setCode(code);
        area.setSpellFirst(pinyin.getFirst());
        area.setSpellSimple(pinyin.getSimple());
        area.setSpell(pinyin.getFull());
        String typeDictId = areaTypeMap.get(AreaType.valueOf(type));
        area.setTypeDictId(Long.parseLong(typeDictId));
        area.setSeq(seq);
        return area;
    }


    /**
     * 抓取数据
     * 数据地址 国家统计局 http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html
     */
    //@Test
    public static void importTest() throws IOException, BadHanyuPinyinOutputFormatCombination, ParseException, URISyntaxException {

        AreaTree areaTree = null;
        if (FileUtil.exist("/Users/yw/temp/area.json")) {
            String s = FileUtil.readUtf8String("/Users/yw/temp/area.json");
            areaTree = JSONUtil.toBean(s, AreaTree.class);
        }
        if (areaTree == null) {

            areaTree = newAreaTree(newAreaDO("中华人民共和国","1", AreaType.country.itemValue(),1));

            List<AreaTree> province = province();
            areaTree.setChildren(province);

            FileUtil.writeUtf8String(JSONUtil.toJsonStr(areaTree), "/Users/yw/temp/area.json");

        }
        save(areaTree,null);

    }
}
