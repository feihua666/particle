package com.particle.global.oss;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.particle.global.exception.Assert;
import com.particle.global.oss.client.GlobalOssClient;
import com.particle.global.oss.dto.GlobalOssBucket;
import com.particle.global.oss.dto.GlobalOssObject;
import com.particle.global.tool.file.FileTool;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 10:02
 */
public abstract class GlobalOssTestBase {

	GlobalOssClient ossClient;
	String testBucket = null;
	String objectName = "/testpath/arthas-boot.jar";
	String localSourceFileFullPath = "/Users/yw/fh/arthas-boot.jar";
	String localObjectFullPath = "/Users/yw/temp/" + objectName;

	/**
	 * 测试
	 */
	public void test() {

		Assert.isTrue(ossClient != null);
		Assert.isTrue(ossClient != null);

		createBucketTest();
		putObjectTest();
		getObjectTest();
		copyObjectTest();
		removeObjectTest();
		removeBucketTest();
		ossClient.close();
	}

	public void createBucketTest() {
		ossClient.createBucket(testBucket);

		List<GlobalOssBucket> allBuckets = ossClient.getAllBuckets();
		boolean b = allBuckets.stream().anyMatch(globalOssBucket -> globalOssBucket.getName().equals(testBucket));
		Assert.isTrue(b);
	}

	public void putObjectTest() {
		ossClient.putObject(testBucket,objectName, IoUtil.toStream(FileUtil.file(localSourceFileFullPath)), FileTool.getMimeType(localSourceFileFullPath));

	}
	public void getObjectTest() {
		GlobalOssObject globalOssObject = ossClient.getObject(testBucket, objectName);
		FileUtil.writeFromStream(globalOssObject.getObjectContent(), localObjectFullPath);

		IoUtil.close(globalOssObject.getObjectContent());
		boolean exist = FileUtil.exist(localObjectFullPath);
		Assert.isTrue(exist);
	}

	public void removeObjectTest() {
		ossClient.removeObject(testBucket,objectName);

		GlobalOssObject globalOssObject = ossClient.getObject(testBucket, objectName);

		Assert.isTrue(globalOssObject == null);
	}

	public void copyObjectTest() {
		String newObjectName = objectName + "_bak";
		ossClient.copyObject(testBucket,objectName,testBucket,newObjectName);
		GlobalOssObject newGlobalOssObject = ossClient.getObject(testBucket, objectName);
		Assert.isTrue(newGlobalOssObject != null);

		IoUtil.close(newGlobalOssObject.getObjectContent());
		ossClient.removeObject(testBucket,newObjectName);

	}

	public void removeBucketTest() {
		ossClient.removeBucket(testBucket);

		List<GlobalOssBucket> allBuckets = ossClient.getAllBuckets();
		boolean b = allBuckets.isEmpty();
		Assert.isTrue(b);
	}
}
