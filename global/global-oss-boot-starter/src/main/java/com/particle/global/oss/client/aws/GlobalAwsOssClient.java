package com.particle.global.oss.client.aws;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.Assert;
import com.particle.global.oss.client.AbstractGlobalOssClient;
import com.particle.global.oss.dto.GlobalOssBucket;
import com.particle.global.oss.dto.GlobalOssObject;
import com.particle.global.oss.exception.BucketNotEmptyException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * aws 实现，aws已经有多家云存储厂商支持其sdk协议，只要是支持aws协议的都可以使用该客户端
 * 目前已知支持的：阿里云、腾讯云、七牛云、minio等
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 17:47
 */
@Slf4j
public class GlobalAwsOssClient extends AbstractGlobalOssClient {

	/**
	 * 原生客户端
	 */
	private S3Client s3Client;
	/**
	 * 配置
	 */
	private GlobalAwsOssProperties awsOssProperties;

	public GlobalAwsOssClient(GlobalAwsOssProperties awsOssProperties) {
		this.awsOssProperties = awsOssProperties;
		this.s3Client = s3Client(awsOssProperties);
	}

	@Override
	public void createBucket(String bucketName) {
		Assert.isTrue(StrUtil.isNotEmpty(bucketName),"bucketName 不能为空");

		try {
			CreateBucketResponse createBucketResponse = this.s3Client.createBucket((builder) -> {
				builder.bucket(bucketName).build();
			});
		} catch (BucketAlreadyOwnedByYouException e) {
			log.warn("bucket={} already exist",bucketName);
		}
	}

	@Override
	public List<GlobalOssBucket> getAllBuckets() {
		ListBucketsResponse listBucketsResponse = this.s3Client.listBuckets();

		if (!listBucketsResponse.hasBuckets()) {
			return Collections.emptyList();
		}
		return listBucketsResponse.buckets().stream().map(b -> GlobalOssBucket.create(b.name(), LocalDateTimeUtil.of(b.creationDate()),b)).collect(Collectors.toList());
	}

	@Override
	public void removeBucket(String bucketName) {
		Assert.isTrue(StrUtil.isNotEmpty(bucketName),"bucketName 不能为空");
		try {
			DeleteBucketResponse deleteBucketResponse = this.s3Client.deleteBucket(builder -> builder.bucket(bucketName));
		} catch (S3Exception e) {
			if ("The bucket you tried to delete is not empty".equals(e.getMessage())) {
				throw new BucketNotEmptyException(StrUtil.format("bucket={} is not empty", bucketName), e);
			}
		}
	}

	@SneakyThrows
	@Override
	public void putObject(String bucketName, String objectName, InputStream inputStream,String contentType) {
		String key = (objectName);

		this.s3Client.putObject(builder -> builder
				.bucket(bucketName)
				.contentType(contentType)
				.key(key), RequestBody.fromInputStream(inputStream, inputStream.available()));
		IoUtil.close(inputStream);
	}

	@Override
	public GlobalOssObject getObject(String bucketName, String objectName) {
		String key = (objectName);
		ResponseInputStream<GetObjectResponse> inputStream = null;
		try {
			inputStream = this.s3Client.getObject(builder -> builder.bucket(bucketName).key(key));
		} catch (NoSuchKeyException e) {
			return null;
		}
		GlobalOssObject globalOssObject = GlobalOssObject.create(objectName, bucketName, inputStream);
		globalOssObject.setContentLength(inputStream.response().contentLength());
		globalOssObject.setContentType(inputStream.response().contentType());
		globalOssObject.setLastUpdateAt(LocalDateTimeUtil.of(inputStream.response().lastModified()));
		// aws 没有创建时间，这里取最后修改时间
		globalOssObject.setCreateAt(globalOssObject.getLastUpdateAt());
		globalOssObject.setOrigin(inputStream);

		return globalOssObject;
	}

	@Override
	public void removeObject(String bucketName, String objectName) {
		this.s3Client.deleteObject(builder -> builder.key(objectName).bucket(bucketName));
	}

	@Override
	public void copyObject(String sourceBucketName, String sourceObjectName, String destBucketName, String destObjectName) {
		String sourceKey = (sourceObjectName);
		String destKey = (destObjectName);
		s3Client.copyObject(builder -> builder
				.sourceBucket(sourceBucketName)
				.sourceKey(sourceKey)
				.destinationBucket(destBucketName)
				.destinationKey(destKey));
	}

	@Override
	public Object getClientObject() {
		return s3Client;
	}

	@Override
	public void close() {
		if (s3Client != null) {
			s3Client.close();
		}
	}

	/**
	 * 允许外部获取配置
	 * @return
	 */
	public GlobalAwsOssProperties getAwsOssProperties() {
		return awsOssProperties;
	}

	/**
	 * s3Client实例
	 * @param awsOssProperties
	 * @return
	 */
	public static S3Client s3Client(GlobalAwsOssProperties awsOssProperties) {
		log.info("s3client building for accessKey={},bucketName={}",awsOssProperties.getAccessKey(),awsOssProperties.getBucketName());
		S3Client s3Client = S3Client.builder().credentialsProvider(() -> new AwsCredentials() {
			@Override
			public String accessKeyId() {
				return awsOssProperties.getAccessKey();
			}
			@Override
			public String secretAccessKey() {
				return awsOssProperties.getSecretKey();
			}
		}).region(Region.of(awsOssProperties.getRegion()))
				.endpointOverride(URI.create(Optional.ofNullable(awsOssProperties.getInnerEndpoint()).orElse(awsOssProperties.getEndpoint())))

				.serviceConfiguration(builder -> builder
						.accelerateModeEnabled(awsOssProperties.getAccelerateModeEnabled())
						.checksumValidationEnabled(awsOssProperties.getChecksumValidationEnabled())
						.multiRegionEnabled(awsOssProperties.getMultiRegionEnabled())
						.chunkedEncodingEnabled(awsOssProperties.getChunkedEncodingEnabled())
						.pathStyleAccessEnabled(awsOssProperties.getPathStyleAccessEnabled())
						.useArnRegionEnabled(awsOssProperties.getUseArnRegionEnabled())
				)
				.fipsEnabled(awsOssProperties.getFipsEnabled())
				.dualstackEnabled(awsOssProperties.getDualstackEnabled()).build();
		log.info("s3client built for accessKey={},bucketName={}",awsOssProperties.getAccessKey(),awsOssProperties.getBucketName());

		return s3Client;
	}

	public static GlobalAwsOssClient create(GlobalAwsOssProperties awsOssProperties) {
		return new GlobalAwsOssClient(awsOssProperties);
	}
}
