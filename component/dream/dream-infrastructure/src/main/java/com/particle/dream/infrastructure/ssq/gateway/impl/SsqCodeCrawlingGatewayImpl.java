package com.particle.dream.infrastructure.ssq.gateway.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.script.ScriptUtil;
import com.particle.dream.domain.ssq.gateway.SsqCodeCrawlingGateway;
import com.particle.dream.domain.ssq.value.SsqCodeCrawlingResult;
import com.particle.global.tool.http.HttpClientTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 双色球号码爬虫网关实现
 * </p>
 *
 * @author yangwei
 * @since 2024/5/20 11:49
 */
@Slf4j
@Component
public class SsqCodeCrawlingGatewayImpl implements SsqCodeCrawlingGateway {
    @Override
    public List<SsqCodeCrawlingResult> crawlingByYear(Integer openedPhaseYear) {
        // 2003 年到2012年的从彩宝贝获取，官方没有开奖相关数据
        assertValidYear(openedPhaseYear);

        // 旧年份
        if (isOldYear(openedPhaseYear)) {
            return crawlingByYearFromCbb(openedPhaseYear);
        }else {
            // 走官网
            return crawlingByYearFromGov(openedPhaseYear);
        }

    }

    @Override
    public List<SsqCodeCrawlingResult> crawlingByPhase(Integer startOpenedPhaseYear, Integer startOpenedPhaseNum,
                                                       Integer endOpenedPhaseYear, Integer endOpenedPhaseNum) {
        assertValidYear(startOpenedPhaseYear);
        assertValidYear(endOpenedPhaseYear);

        if (!startOpenedPhaseYear.equals(endOpenedPhaseYear)) {
            throw new IllegalArgumentException("年份必须在同一年");
        }
        // 旧年份
        if (isOldYear(startOpenedPhaseYear)) {

            //     todo
        }else {
            // 走官网
            //     todo
        }
        return Collections.emptyList();
    }

    private void assertValidYear(Integer year) {
        if (year < 2003) {
            throw new IllegalArgumentException("年份不能小于2003");
        }
    }

    /**
     * 是否为旧年份，从2003 - 2012年的数据官网没有找到相关开奖记录数据
     * @param year
     * @return
     */
    private boolean isOldYear(Integer year) {
        return year >= 2003 && year <= 2012;
    }


    /**
     * 从新浪网抓取的结果解析,但没有开奖日期，还得计算，也没有中奖信息，换成彩宝贝试试
     * @param openedPhaseYear
     * @return
     */
    private List<SsqCodeCrawlingResult> crawlingByYearFromSina(Integer openedPhaseYear) {
        String urlTemplate = "https://view.lottery.sina.com.cn/lotto/pc_zst/index?lottoType=ssq&actionType=chzs&year={}&dpc=1";
        // 使用 Jsoup 从 URL 加载 HTML 文档
        Document doc = null;
        try {
            String getStr = HttpClientTool.get(StrUtil.format(urlTemplate, openedPhaseYear), null);
            doc = Jsoup.parse(getStr);
        } catch (IOException | ParseException | URISyntaxException e) {
            log.error("crawlingByYear for old year error",e);
        }

        if (doc != null) {
            Elements trElements = doc.select("#cpdata tr");
            List<SsqCodeCrawlingResult> results = new ArrayList<>(trElements.size());
            for (int i = 0; i < trElements.size(); i++) {
                SsqCodeCrawlingResult ssqCodeCrawlingResult = SsqCodeCrawlingResult.create();
                // 按顺序存储，依次为 red1，red2，red3，red4，red5，red6，blue
                List<String> balls = new ArrayList<>(7);

                Elements tdElements = trElements.get(i).select("td");
                for (int i1 = 0; i1 < tdElements.size(); i1++) {
                    Element tdElement = tdElements.get(i1);
                    if (i1 == 0) {
                        // 期数如：2003001
                        String openedPhaseStr = tdElement.text();
                        String openedPhaseYearStr = openedPhaseStr.substring(0, openedPhaseStr.length() - 3);
                        String openedPhaseNumStr = openedPhaseStr.substring(openedPhaseYearStr.length());

                        // 新浪网开奖历史记录里没有日期，需要计算一下
                        // ssqCodeCrawlingResult.setOpenedDate();
                        ssqCodeCrawlingResult.setOpenedPhaseYear(Integer.valueOf(openedPhaseYearStr));
                        ssqCodeCrawlingResult.setOpenedPhaseNum(Integer.valueOf(openedPhaseNumStr));
                        ssqCodeCrawlingResult.setOpenedPhase(Integer.valueOf(openedPhaseStr));
                    }
                    if (i1 == 2) {
                        String openedWeekDayStr = tdElement.text();
                        ssqCodeCrawlingResult.setOpenedWeekDay(Integer.valueOf(openedWeekDayStr));

                    }

                    Set<String> classNames = tdElement.classNames();
                    if (classNames.stream().anyMatch(className -> StrUtil.startWith(className, "chartball"))) {
                        String ballStr = tdElement.text();
                        balls.add(ballStr);
                    }
                }

                ssqCodeCrawlingResult.fillBalls(balls);
                results.add(ssqCodeCrawlingResult);
            }


            if (results != null && !results.isEmpty()) {
                SsqCodeCrawlingResult firstOpened = results.iterator().next();
                results.stream().peek(ssqCodeCrawlingResult -> ssqCodeCrawlingResult.computeOpenedDate(firstOpened.getOpenedPhaseYear(), firstOpened.getOpenedWeekDay()));
                return results;
            }

            return results;
        }
        return null;
    }

    /**
     * 从彩宝贝获取数据
     * @param openedPhaseYear
     * @return
     */
    private List<SsqCodeCrawlingResult> crawlingByYearFromCbb(Integer openedPhaseYear) {

        String urlTemplate = "https://kaijiang.78500.cn/ssq/";
        // 使用 Jsoup 从 URL 加载 HTML 文档
        Document doc = null;
        try {
            Map<String, String> form = new HashMap<>();
            form.put("year", openedPhaseYear.toString());
            form.put("action", "years");
            HttpClientTool.ExtConfig extConfig = HttpClientTool.ExtConfig.create()
                    .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:126.0) Gecko/20100101 Firefox/126.0");
            String getStr = HttpClientTool.postForm(urlTemplate, form, extConfig);
            doc = Jsoup.parse(getStr);
        } catch (IOException | ParseException | URISyntaxException e) {
            log.error("parseSsqCodeCrawlingResultFromCbb error",e);
        }

        if (doc != null) {
            Elements trElements = doc.select(".list-tr tr");
            List<SsqCodeCrawlingResult> results = new ArrayList<>(trElements.size());
            // trElements.size() - 1 最后一行不是数据
            for (int i = 0; i < trElements.size() - 1; i++) {
                SsqCodeCrawlingResult ssqCodeCrawlingResult = SsqCodeCrawlingResult.create();
                // 按顺序存储，依次为 red1，red2，red3，red4，red5，red6，blue
                List<String> balls = new ArrayList<>(7);

                Elements tdElements = trElements.get(i).select("td");
                for (int i1 = 0; i1 < tdElements.size(); i1++) {
                    Element tdElement = tdElements.get(i1);
                    if (i1 == 0) {
                        // 期数如：2003001
                        String openedPhaseStr = tdElement.text();
                        String openedPhaseYearStr = openedPhaseStr.substring(0, openedPhaseStr.length() - 3);
                        String openedPhaseNumStr = openedPhaseStr.substring(openedPhaseYearStr.length());

                        ssqCodeCrawlingResult.setOpenedPhaseYear(Integer.valueOf(openedPhaseYearStr));
                        ssqCodeCrawlingResult.setOpenedPhaseNum(Integer.valueOf(openedPhaseNumStr));
                        ssqCodeCrawlingResult.setOpenedPhase(Integer.valueOf(openedPhaseStr));
                    }else if (i1 == 1) {
                        // 开奖日期：2024-05-21
                        String openedDateStr = tdElement.text();
                        LocalDate localDate = LocalDateTimeUtil.parseDate(openedDateStr);
                        ssqCodeCrawlingResult.setOpenedDate(localDate);

                        ssqCodeCrawlingResult.setOpenedWeekDay(localDate.getDayOfWeek().getValue());

                    }else if (i1 == 2) {
                        // 开奖号码
                        Elements elements = tdElement.select("span");
                        for (Element span : elements) {
                            balls.add(span.text());
                        }

                    } else if (i1 == 3) {
                        // 销售金额
                        String saleAmountStr = tdElement.text().replace(",", "");
                        ssqCodeCrawlingResult.setSaleAmount(Long.valueOf(saleAmountStr));

                    } else if (i1 == 4) {
                        // 最新奖池，这里是最新奖池，不是奖池金额，即下一期的奖池金额
                        String prizePoolAmountStr = tdElement.text().replace(",", "");
                        ssqCodeCrawlingResult.setNextPrizePoolAmount(Long.valueOf(prizePoolAmountStr));

                    } else if (i1 == 5) {
                        // 一等奖 注数
                        String win1NumStr = tdElement.text();
                        ssqCodeCrawlingResult.setWin1Num(Integer.valueOf(win1NumStr));

                    } else if (i1 == 6) {
                        // 一等奖 单注中奖金额
                        String win1AmountStr = tdElement.text().replace(",", "");
                        ssqCodeCrawlingResult.setWin1Amount(Integer.valueOf(win1AmountStr));

                        ssqCodeCrawlingResult.computeWin1TotalAmount();

                    } else if (i1 == 7) {
                        // 二等奖 注数
                        String win2NumStr = tdElement.text();
                        ssqCodeCrawlingResult.setWin2Num(Integer.valueOf(win2NumStr));

                    } else if (i1 == 8) {
                        // 二等奖 单注中奖金额
                        String win2AmountStr = tdElement.text().replace(",", "");
                        ssqCodeCrawlingResult.setWin2Amount(Integer.valueOf(win2AmountStr));
                        ssqCodeCrawlingResult.computeWin2TotalAmount();


                    } else if (i1 == 9) {
                        // 三等奖 注数
                        String win3NumStr = tdElement.text();
                        ssqCodeCrawlingResult.setWin3Num(Integer.valueOf(win3NumStr));

                    } else if (i1 == 10) {
                        // 三等奖 单注中奖金额
                        String win3AmountStr = tdElement.text().replace(",", "");
                        ssqCodeCrawlingResult.setWin3Amount(Integer.valueOf(win3AmountStr));
                        ssqCodeCrawlingResult.computeWin3TotalAmount();


                    } else if (i1 == 11) {
                        // 开奖详情，因列表中没有四、五、六等将信息，抓取详情页面获取
                        SsqCodeCrawlingResult ssqCodeCrawlingResultDetail = crawlingDetailFromCbb(ssqCodeCrawlingResult.getOpenedPhase().toString());
                        if (ssqCodeCrawlingResultDetail != null) {
                            ssqCodeCrawlingResult.setWin4Num(ssqCodeCrawlingResultDetail.getWin4Num());
                            ssqCodeCrawlingResult.setWin4Amount(ssqCodeCrawlingResultDetail.getWin4Amount());
                            ssqCodeCrawlingResult.setWin4TotalAmount(ssqCodeCrawlingResultDetail.getWin4TotalAmount());

                            ssqCodeCrawlingResult.setWin5Num(ssqCodeCrawlingResultDetail.getWin5Num());
                            ssqCodeCrawlingResult.setWin5Amount(ssqCodeCrawlingResultDetail.getWin5Amount());
                            ssqCodeCrawlingResult.setWin5TotalAmount(ssqCodeCrawlingResultDetail.getWin5TotalAmount());
                            ssqCodeCrawlingResult.setWin6Num(ssqCodeCrawlingResultDetail.getWin6Num());
                            ssqCodeCrawlingResult.setWin6Amount(ssqCodeCrawlingResultDetail.getWin6Amount());
                            ssqCodeCrawlingResult.setWin6TotalAmount(ssqCodeCrawlingResultDetail.getWin6TotalAmount());
                        }
                    }
                }

                ssqCodeCrawlingResult.fillBalls(balls);
                results.add(ssqCodeCrawlingResult);
            }

            // 将结果排序
            Collections.reverse(results);
            return results;
        }
        return null;
    }

    /**
     * 开奖详情
     * @param openedPhaseStr
     * @return
     */
    private SsqCodeCrawlingResult crawlingDetailFromCbb(String openedPhaseStr) {
        String urlTemplate = "https://kaijiang.78500.cn/ssq/{}/";
        // 使用 Jsoup 从 URL 加载 HTML 文档
        Document doc = null;
        try {
            HttpClientTool.ExtConfig extConfig = HttpClientTool.ExtConfig.create()
                    .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:126.0) Gecko/20100101 Firefox/126.0");
            String getStr = HttpClientTool.get(StrUtil.format(urlTemplate,openedPhaseStr), extConfig);
            doc = Jsoup.parse(getStr);
        } catch (IOException | ParseException | URISyntaxException e) {
            log.error("crawlingDetailFromCbb error",e);
        }
        if (doc != null) {
            SsqCodeCrawlingResult ssqCodeCrawlingResult = SsqCodeCrawlingResult.create();

            Elements trElements = doc.select("#winList tr");
            for (int i = 0; i < trElements.size(); i++) {
                Elements tdElements = trElements.get(i).select("td");
                String winNumStr = null;
                String winAmountStr = null;
                for (int i1 = 0; i1 < tdElements.size(); i1++) {
                    Element tdElement = tdElements.get(i1);
                    if (i1 == 1) {
                        winNumStr = tdElement.text().replace(",","");
                    }else if (i1 == 2) {
                        winAmountStr = tdElement.text().replace("元","").replace(",","");
                    }
                }
                if (i == 3) {
                    // 四等奖 注数
                    ssqCodeCrawlingResult.setWin4Num(Integer.valueOf(winNumStr));
                    ssqCodeCrawlingResult.setWin4Amount(Integer.valueOf(winAmountStr));
                    ssqCodeCrawlingResult.computeWin4TotalAmount();
                }else if (i == 4) {
                    // 五等奖 注数
                    ssqCodeCrawlingResult.setWin5Num(Integer.valueOf(winNumStr));
                    ssqCodeCrawlingResult.setWin5Amount(Integer.valueOf(winAmountStr));
                    ssqCodeCrawlingResult.computeWin5TotalAmount();
                }else if (i == 5) {
                    // 六等奖 注数
                    ssqCodeCrawlingResult.setWin6Num(Integer.valueOf(winNumStr));
                    ssqCodeCrawlingResult.setWin6Amount(Integer.valueOf(winAmountStr));
                    ssqCodeCrawlingResult.computeWin6TotalAmount();
                }
            }
            return ssqCodeCrawlingResult;
        }
        return null;
    }

    /**
     * 从官网获取
     * @param openedPhaseYear
     * @return
     */
    private List<SsqCodeCrawlingResult> crawlingByYearFromGov(Integer openedPhaseYear) {

        // 走官网
        // 原始url https://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart=2013001&issueEnd=2014001&dayStart=&dayEnd=&pageNo=1&pageSize=30&week=&systemType=PC
        String urlTemplate = "https://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart={}&issueEnd={}&dayStart=&dayEnd=&pageNo={}&pageSize=30&week=&systemType=PC";
        String issueStart = StrUtil.format("{}{}", openedPhaseYear, "001");
        String issueEnd = StrUtil.format("{}{}", openedPhaseYear + 1, "001");
        int pageNo = 1;
        List<SsqCodeCrawlingResult> results = new ArrayList<>();
        while (true) {
            String getStr = null;
            try {
                /**
                 * 获取数据结果示例
                 * {
                 *     "state": 0,
                 *     "message": "查询成功",
                 *     "total": 155,
                 *     "pageNum": 155,
                 *     "pageNo": 1,
                 *     "pageSize": 1,
                 *     "Tflag": 1,
                 *     "result":
                 *     [
                 *         {
                 *             "name": "双色球",
                 *             "code": "2014001",
                 *             "detailsLink": "/c/2014/01/02/384892.shtml",
                 *             "videoLink": "",
                 *             "date": "2014-01-02(四)",
                 *             "week": "四",
                 *             "red": "03,09,15,20,27,29",
                 *             "blue": "01",
                 *             "blue2": "",
                 *             "sales": "371341368",
                 *             "poolmoney": "136130274",
                 *             "content": "山西1注,黑龙江1注,新疆1注,共3注。",
                 *             "addmoney": "",
                 *             "addmoney2": "",
                 *             "msg": "",
                 *             "z2add": "",
                 *             "m2add": "",
                 *             "prizegrades":
                 *             [
                 *                 {
                 *                     "type": 1,
                 *                     "typenum": "3",
                 *                     "typemoney": "5000000"
                 *                 },
                 *                 {
                 *                     "type": 2,
                 *                     "typenum": "139",
                 *                     "typemoney": "251264"
                 *                 },
                 *                 {
                 *                     "type": 3,
                 *                     "typenum": "1152",
                 *                     "typemoney": "3000"
                 *                 },
                 *                 {
                 *                     "type": 4,
                 *                     "typenum": "66979",
                 *                     "typemoney": "200"
                 *                 },
                 *                 {
                 *                     "type": 5,
                 *                     "typenum": "1406407",
                 *                     "typemoney": "10"
                 *                 },
                 *                 {
                 *                     "type": 6,
                 *                     "typenum": "6924462",
                 *                     "typemoney": "5"
                 *                 },
                 *                 {
                 *                     "type": 7,
                 *                     "typenum": "",
                 *                     "typemoney": ""
                 *                 }
                 *             ]
                 *         }
                 *     ]
                 * }
                 */
                getStr = HttpClientTool.get(StrUtil.format(urlTemplate, issueStart, issueEnd, pageNo), null);
            } catch (IOException | ParseException | URISyntaxException e) {
                log.error("crawlingByYear error",e);
            }
            if (getStr != null) {
                JSONObject resultJsonObject = JSONUtil.parseObj(getStr);
                JSONArray resultJsonObjectJSONArray = resultJsonObject.getJSONArray("result");

                List<SsqCodeCrawlingResult> resultsTemp = parseSsqCodeCrawlingResultFromGov(resultJsonObjectJSONArray,openedPhaseYear);
                // 数据为空表示翻页结束
                if (CollectionUtil.isEmpty(resultsTemp)) {
                    break;
                }else {
                    results.addAll(resultsTemp);
                    pageNo++;
                }
            }else {
                break;
            }
        }

        // 将结果排序
        Collections.reverse(results);
        return results;
    }

    /**
     * 从官网获取数据详情，主要是获取开机号码
     * @param detailUrl
     * @return
     */
    private SsqCodeCrawlingResult crawlingDetailFromGov(String detailUrl) {
        String urlTemplate = detailUrl;
        // 使用 Jsoup 从 URL 加载 HTML 文档
        Document doc = null;
        try {
            String getStr = HttpClientTool.get(urlTemplate, null);
            doc = Jsoup.parse(getStr);
        } catch (IOException | ParseException | URISyntaxException e) {
            log.error("crawlingDetailFromGov error",e);
        }
        if (doc != null) {
            SsqCodeCrawlingResult ssqCodeCrawlingResult = SsqCodeCrawlingResult.create();
            for (Element script : doc.select("script")) {
                String scriptData = script.data();
                if (StrUtil.isEmpty(scriptData)) {
                    scriptData = script.html();
                }
                /**
                 * 脚本类似这样，这是2024058期的数据
                 *         var khHq = [
                 *             '27',
                 *             '29',
                 *             '17',
                 *             '12',
                 *             '08',
                 *             '13',
                 *         ]
                 *         var khLq = '13'
                 */
                if (StrUtil.contains(scriptData,"var khHq") && StrUtil.contains(scriptData,"var khLq")) {
                    try {
                        ScriptEngine jsEngine = ScriptUtil.getJsEngine();

                        jsEngine.eval(scriptData);

                        // 按顺序存储，依次为 red1，red2，red3，red4，red5，red6，blue
                        List<String> balls = new ArrayList<>(7);

                        Object khHqObject = jsEngine.get("khHq");
                        if (khHqObject instanceof ScriptObjectMirror) {
                            ScriptObjectMirror khHq = (ScriptObjectMirror) khHqObject;
                            Collection<Object> values = khHq.values();
                            balls.addAll(values.stream().map(item -> item.toString()).collect(Collectors.toList()));

                        }
                        Object khLq = jsEngine.get("khLq");
                        if (khLq instanceof String) {
                            balls.add((String) khLq);
                        }
                        if (!balls.isEmpty()) {
                            ssqCodeCrawlingResult.fillBalls(balls);
                        }
                    } catch (ScriptException e) {
                        log.error("crawlingDetailFromGov error",e);
                    }
                }
            }
            return ssqCodeCrawlingResult;
        }
        return null;
    }
    /**
     * 从官网获取
     * @param resultJsonObjectJSONArray
     * @param openedPhaseYear
     * @return
     */
    private List<SsqCodeCrawlingResult> parseSsqCodeCrawlingResultFromGov(JSONArray resultJsonObjectJSONArray,Integer openedPhaseYear) {
        if (resultJsonObjectJSONArray != null && !resultJsonObjectJSONArray.isEmpty()) {
            List<SsqCodeCrawlingResult> results = new ArrayList<>(resultJsonObjectJSONArray.size());
            for (Object o : resultJsonObjectJSONArray) {
                JSONObject jsonObject = (JSONObject) o;
                SsqCodeCrawlingResult ssqCodeCrawlingResult = SsqCodeCrawlingResult.create();

                // 期数如：2003001
                String openedPhaseStr = jsonObject.getStr("code");
                String openedPhaseYearStr = openedPhaseStr.substring(0, openedPhaseStr.length() - 3);
                String openedPhaseNumStr = openedPhaseStr.substring(openedPhaseYearStr.length());

                // 原始 date 为 2014-01-02(四)，需要处理一下
                String date = jsonObject.getStr("date");
                DateTime dateTime = DateUtil.parseDate(date.substring(0, 10));
                ssqCodeCrawlingResult.setOpenedDate(LocalDateTimeUtil.of(dateTime).toLocalDate());

                ssqCodeCrawlingResult.setOpenedPhaseYear(Integer.valueOf(openedPhaseYearStr));

                // 可能获取的数据年份不相同，这一份数据不需要
                if (!openedPhaseYear.equals(ssqCodeCrawlingResult.getOpenedPhaseYear())) {
                    continue;
                }

                ssqCodeCrawlingResult.setOpenedPhaseNum(Integer.valueOf(openedPhaseNumStr));
                ssqCodeCrawlingResult.setOpenedPhase(Integer.valueOf(openedPhaseStr));


                String openedWeekDayStr = jsonObject.getStr("week");
                switch (openedWeekDayStr) {
                    case "二":
                        openedWeekDayStr = "2";
                        break;
                    case "四":
                        openedWeekDayStr = "4";
                        break;
                    case "日":
                        openedWeekDayStr = "7";
                        break;
               }
                ssqCodeCrawlingResult.setOpenedWeekDay(Integer.valueOf(openedWeekDayStr));

                // 按顺序存储，依次为 red1，red2，red3，red4，red5，red6，blue
                List<String> balls = new ArrayList<>(7);
                List<String> redBalls = Arrays.stream(jsonObject.getStr("red").split(",")).collect(Collectors.toList());
                balls.addAll(redBalls);
                balls.add(jsonObject.getStr("blue"));

                ssqCodeCrawlingResult.fillBalls(balls);

                // 销售金额
                String saleAmountStr = jsonObject.getStr("sales");
                ssqCodeCrawlingResult.setSaleAmount(Long.valueOf(saleAmountStr));

                // 奖池金额，下一期的奖池金额
                String prizePoolAmountStr = jsonObject.getStr("poolmoney");
                ssqCodeCrawlingResult.setNextPrizePoolAmount(Long.valueOf(prizePoolAmountStr));

                JSONArray jsonArray = jsonObject.getJSONArray("prizegrades");
                for (Object object : jsonArray) {
                    JSONObject jsonObject1 = (JSONObject) object;
                    switch (jsonObject1.getInt("type")) {
                        case 1:
                            // 一等奖 注数
                            String win1NumStr = jsonObject1.getStr("typenum");
                            ssqCodeCrawlingResult.setWin1Num(Integer.valueOf(win1NumStr));
                            // 一等奖 单注中奖金额
                            // 可能存在类似这样的数据：5625000（含加奖625000）
                            String win1AmountStr = jsonObject1.getStr("typemoney").split("（")[0];
                            ssqCodeCrawlingResult.setWin1Amount(Integer.valueOf(win1AmountStr));

                            ssqCodeCrawlingResult.computeWin1TotalAmount();
                            break;
                        case 2:
                            // 二等奖 注数
                            String win2NumStr = jsonObject1.getStr("typenum");
                            ssqCodeCrawlingResult.setWin2Num(Integer.valueOf(win2NumStr));
                            // 二等奖 单注中奖金额
                            String win2AmountStr = jsonObject1.getStr("typemoney");
                            ssqCodeCrawlingResult.setWin2Amount(Integer.valueOf(win2AmountStr));

                            ssqCodeCrawlingResult.computeWin2TotalAmount();
                            break;
                        case 3:
                            // 三等奖 注数
                            String win3NumStr = jsonObject1.getStr("typenum");
                            ssqCodeCrawlingResult.setWin3Num(Integer.valueOf(win3NumStr));
                            // 三等奖 单注中奖金额
                            String win3AmountStr = jsonObject1.getStr("typemoney");
                            ssqCodeCrawlingResult.setWin3Amount(Integer.valueOf(win3AmountStr));

                            ssqCodeCrawlingResult.computeWin3TotalAmount();
                            break;
                        case 4:
                            // 四等奖 注数
                            String win4NumStr = jsonObject1.getStr("typenum");
                            ssqCodeCrawlingResult.setWin4Num(Integer.valueOf(win4NumStr));
                            // 四等奖 单注中奖金额
                            String win4AmountStr = jsonObject1.getStr("typemoney");
                            ssqCodeCrawlingResult.setWin4Amount(Integer.valueOf(win4AmountStr));

                            ssqCodeCrawlingResult.computeWin4TotalAmount();
                            break;
                        case 5:
                            // 五等奖 注数
                            String win5NumStr = jsonObject1.getStr("typenum");
                            ssqCodeCrawlingResult.setWin5Num(Integer.valueOf(win5NumStr));
                            // 五等奖 单注中奖金额
                            String win5AmountStr = jsonObject1.getStr("typemoney");
                            ssqCodeCrawlingResult.setWin5Amount(Integer.valueOf(win5AmountStr));

                            ssqCodeCrawlingResult.computeWin5TotalAmount();
                            break;
                        case 6:
                            // 六等奖 注数
                            String win6NumStr = jsonObject1.getStr("typenum");
                            ssqCodeCrawlingResult.setWin6Num(Integer.valueOf(win6NumStr));
                            // 六等奖 单注中奖金额
                            String win6AmountStr = jsonObject1.getStr("typemoney");
                            ssqCodeCrawlingResult.setWin6Amount(Integer.valueOf(win6AmountStr));

                            ssqCodeCrawlingResult.computeWin6TotalAmount();
                            break;
                    }
                }

                // 尝试抓取开机号码
                // "detailsLink": "/c/2014/01/02/384892.shtml",
                String detailsLink = jsonObject.getStr("detailsLink");
                SsqCodeCrawlingResult ssqCodeCrawlingResult1 = crawlingDetailFromGov(StrUtil.format("https://www.cwl.gov.cn{}", detailsLink));
                if (ssqCodeCrawlingResult1 != null) {
                    if (ssqCodeCrawlingResult1.getOpenedRed1() != null
                    && ssqCodeCrawlingResult1.getOpenedRed2() != null
                    && ssqCodeCrawlingResult1.getOpenedRed3() != null
                    && ssqCodeCrawlingResult1.getOpenedRed4() != null
                    && ssqCodeCrawlingResult1.getOpenedRed5() != null
                    && ssqCodeCrawlingResult1.getOpenedRed6() != null
                    && ssqCodeCrawlingResult1.getOpenedBlue() != null) {
                        ssqCodeCrawlingResult.setOpenedRed1(ssqCodeCrawlingResult1.getOpenedRed1());
                        ssqCodeCrawlingResult.setOpenedRed2(ssqCodeCrawlingResult1.getOpenedRed2());
                        ssqCodeCrawlingResult.setOpenedRed3(ssqCodeCrawlingResult1.getOpenedRed3());
                        ssqCodeCrawlingResult.setOpenedRed4(ssqCodeCrawlingResult1.getOpenedRed4());
                        ssqCodeCrawlingResult.setOpenedRed5(ssqCodeCrawlingResult1.getOpenedRed5());
                        ssqCodeCrawlingResult.setOpenedRed6(ssqCodeCrawlingResult1.getOpenedRed6());
                        ssqCodeCrawlingResult.setOpenedBlue(ssqCodeCrawlingResult1.getOpenedBlue());
                    }

                }
                results.add(ssqCodeCrawlingResult);
            }
            return results;
        }
        return null;
    }

}
