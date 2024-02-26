package com.particle.feedback.domain.feedback;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.exception.Assert;
import com.particle.global.validation.ValidateTool;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 意见反馈 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Data
@Entity
public class Feedback extends AggreateRoot {

    private FeedbackId id;

    /**
    * 问题建议内容
    */
    private String feedbackContent;

    /**
    * 问题建议时间
    */
    private LocalDateTime feedbackAt;
    
    /**
    * 问题建议用户id，如果存在登录用户，记录提建议的用户id
    */
    private Long feedbackUserId;

    /**
    * 联系姓名
    */
    private String contactUsername;

    /**
    * 联系电话
    */
    private String contactTelephone;

    /**
    * 联系邮箱
    */
    private String contactEmail;

    /**
    * 是否联系电话为手机号
    */
    private Boolean isContactTelephoneMobile;

    /**
    * 是否已处理
    */
    private Boolean isHandle;

    /**
    * 处理结果，描述文本
    */
    private String handleResult;

	/**
	 * 处理时间
	 */
	private LocalDateTime handleAt;

    /**
    * 第一条意见反馈id，主要串联起一个话题
    */
    private Long firstFeedbackId;

    /**
    * 描述
    */
    private String remark;


    public void initForAdd() {
        this.feedbackAt = LocalDateTime.now();

        this.isContactTelephoneMobile = Validator.isMobile(this.contactTelephone);
        this.isHandle = false;

    }


    /**
     * 手动处理
     * @param handleResult
     */
    public void manualHandle(String handleResult) {
        this.handleResult = handleResult;
        this.isHandle = true;
        this.handleAt = LocalDateTime.now();
    }



    /**
     * 创建意见反馈领域模型对象
     * @return 意见反馈领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static Feedback create(){
        return DomainFactory.create(Feedback.class);
    }
}