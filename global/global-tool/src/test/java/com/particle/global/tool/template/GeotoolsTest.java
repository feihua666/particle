package com.particle.global.tool.template;

import cn.hutool.core.io.FileUtil;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

/**
 * <p>
 * 测试geojson，指定一个经纬度，返回这个经纬度对应的所在的省份
 * </p>
 *
 * @author yangwei
 * @since 2024/5/20 17:46
 */
public class GeotoolsTest {
    public static void main(String[] args) throws Exception {


        FeatureJSON featureJSON = new FeatureJSON(new GeometryJSON(15));

        String readUtf8String = FileUtil.readUtf8String("/Users/yw/Downloads/china.geoJson");
        // 读取为FeatureCollection
        FeatureCollection featureCollection = featureJSON.readFeatureCollection(readUtf8String);
        FeatureIterator features = featureCollection.features();

        // 给定的经纬度点

        // 102.502125,26.841079
        // 110.89597，21.076984
        // double longitude = 110.89597;
        // double latitude = 21.076984;
        double longitude = 111;
        double latitude = 33;
        Geometry point = new GeometryFactory().createPoint(new Coordinate(longitude, latitude));
        point = point.buffer(0.000001);
        // 遍历所有区域，检查点是否在多边形内
        try (SimpleFeatureIterator iterator = (SimpleFeatureIterator) features) {
            while (iterator.hasNext()) {
                SimpleFeature feature = iterator.next();
                // 假设GeoJSON中的多边形存储在名为"geometry"的属性中
                Geometry geometry = (Geometry) feature.getDefaultGeometry();
                geometry = geometry.buffer(0.000001);
                // 检查点是否在多边形内
                if (geometry.intersects(point)) {
                    // 如果点在多边形内，从feature中提取区域编码（假设编码存储在名为"code"的属性中）
                    Object code = feature.getAttribute("name"); // 根据你的GeoJSON数据修改属性名
                    System.out.println("The point is within the area with name: " + code);
                    // 假设你只想找到第一个匹配的区域
                    // 处理或返回编码
                    break;

                }
            }
        }
    }

}
