package com.particle.global.oss;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.particle.global.oss.client.aws.GlobalAwsOssClient;
import com.particle.global.oss.client.aws.GlobalAwsOssProperties;
import com.particle.global.tool.http.HttpClientTool;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/10/30 17:15
 */
public class UploadToMinio {

    static com.particle.global.oss.client.GlobalOssClient globalOssClient;

    static {
        GlobalAwsOssProperties globalAwsOssProperties = new GlobalAwsOssProperties();
        globalAwsOssProperties.setAccessKey("O1ZwugbowdwFDeGJ3buT");
        globalAwsOssProperties.setSecretKey("jSRFPgyhWsYb6J7nDdF4fjbcBWb7dzT7180LOPDd");
        globalAwsOssProperties.setEndpoint("http://218.246.21.152:8080");
        globalAwsOssProperties.setRegion("noneForMinio");

        globalOssClient = GlobalAwsOssClient.create(globalAwsOssProperties);

    }

    public static void main(String[] args) {
        List<String> readUtf8Lines = FileUtil.readUtf8Lines("/Users/yw/temp/newUrls.csv");
        List<String> newUrls = new ArrayList<>();
        for (String readUtf8Line : readUtf8Lines) {
            String[] split = readUtf8Line.split(",");
            String id = split[0];
            String url = split[1];

            String t = "update component_navigation_site set logo_url = '{}' where id = {};";

            String format = StrUtil.format(t, url, id);
            newUrls.add(format);


        }
        FileUtil.writeUtf8Lines(newUrls,"/Users/yw/temp/update.csv");
    }
    public static void main1(String[] args) throws IOException {
        List<String> readUtf8Lines = FileUtil.readUtf8Lines("/Users/yw/temp/urls.csv");
        List<String> newUrls = new ArrayList<>();
        for (String readUtf8Line : readUtf8Lines) {
            String[] split = readUtf8Line.split(",");
            String id = split[0];
            String url = split[1];

            byte[] d = (HttpClientTool.download(url, HttpClientTool.ExtConfig.create()));
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(d);

            String fileName = url.substring(url.lastIndexOf("/")).replace("-icon.png",".png");
            YearMonth now = YearMonth.now();
            String path = "logos/" + now.getYear() + "/" + now.getMonth().getValue() + fileName;

            globalOssClient.putObject("navigation",path, byteArrayInputStream,"image/png");

            String newUrl = "http://218.246.21.152:8080/m/navigation/" + path;

            newUrls.add(id + "," + newUrl);
        }
        FileUtil.writeUtf8Lines(newUrls,"/Users/yw/temp/newUrls.csv");
    }
}
