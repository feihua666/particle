package com.particle.cms.client.dto.data.dynamic;

import com.particle.common.client.dto.data.AbstractBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 分页模板模型VO
 * </p>
 *
 * @author yangwei
 * @since 2026/1/28 12:03
 */
@Data
@Accessors(chain = true)
public class PaginationTemplateModelVO extends AbstractBaseVO {

    @Schema(description = "当前页码", required = true, example = "1")
    private Long currentPageNo;

    @Schema(description = "总页数", required = true, example = "100")
    private Long totalPageCount;

    @Schema(description = "总记录数", required = true, example = "1000")
    private Long totalCount;

    @Schema(description = "每页记录数", required = true, example = "10")
    private Long pageSize;

    @Schema(description = "是否第一页")
    private Boolean isFirstPage;

    @Schema(description = "第一页页码")
    private Long firstPageNo;

    @Schema(description = "是否最后一页")
    private Boolean isLastPage;

    @Schema(description = "最后一页页码")
    private Long lastPageNo;

    @Schema(description = "是否有前一页")
    private Boolean hasPreviousPage;

    @Schema(description = "前一页页码")
    private Long previousPageNo;

    @Schema(description = "是否有下一页")
    private Boolean hasNextPage;

    @Schema(description = "下一页页码")
    private Long nextPageNo;

    @Schema(description = "显示的页码列表")
    private List<PageItem> pageItems;


    /**
     * 构建分页导航（使用自定义配置）
     */
    private void init() {
        if (this.totalPageCount == null || this.currentPageNo == null) {
            return;
        }

        // 设置其他辅助属性
        this.isFirstPage = this.currentPageNo.equals(1);
        this.isLastPage = this.currentPageNo.equals(this.totalPageCount);
        this.hasPreviousPage = this.currentPageNo > 1;
        this.hasNextPage = this.currentPageNo < this.totalPageCount;

        if (this.firstPageNo == null) {
            this.firstPageNo = 1L;
        }
        if (this.lastPageNo == null) {
            this.lastPageNo = this.totalPageCount;
        }
        this.previousPageNo = this.currentPageNo - 1;
        this.nextPageNo = this.currentPageNo + 1;
    }
    public static PaginationTemplateModelVO create(Long currentPageNo,Long pageSize,Long totalPageCount,Long totalCount) {

        PaginationTemplateModelVO paginationTemplateModelVO = new PaginationTemplateModelVO();
        paginationTemplateModelVO.currentPageNo = currentPageNo;
        paginationTemplateModelVO.pageSize = pageSize;
        paginationTemplateModelVO.totalPageCount = totalPageCount;
        paginationTemplateModelVO.totalCount = totalCount;
        paginationTemplateModelVO.init() ;
        paginationTemplateModelVO.pageItems = paginationTemplateModelVO.generatePageItems(new PageNavigationConfig());

        return paginationTemplateModelVO;
    }


    /**
     * 生成分页导航的页码项
     */
    private List<PageItem> generatePageItems(PageNavigationConfig config) {
        List<PageItem> items = new ArrayList<>();

        // 如果总页数不超过阈值，则显示所有页码
        if (totalPageCount <= config.getMinPagesForEllipsis()) {
            for (long i = 1; i <= totalPageCount; i++) {
                items.add(new PageItem(i, Objects.equals(i, currentPageNo)));
            }
            return items;
        }

        // 计算各部分边界
        long frontEnd = config.getFrontPages(); // 前部结束位置
        long middleStart = Math.max(currentPageNo - config.getMiddlePages(), frontEnd + 1);
        long middleEnd = Math.min(currentPageNo + config.getMiddlePages(), totalPageCount - config.getRearPages());
        long rearStart = totalPageCount - config.getRearPages() + 1;

        // 1. 添加前部页码
        for (long i = 1; i <= Math.min(frontEnd, totalPageCount); i++) {
            items.add(new PageItem(i, Objects.equals(i, currentPageNo)));
        }

        // 判断是否需要前部省略号
        if (middleStart > frontEnd + 1) {
            items.add(new PageItem(null, false)); // 前部省略号
        }

        // 2. 添加中部页码
        if (middleStart <= middleEnd) {
            for (Long i = middleStart; i <= middleEnd; i++) {
                // 避免重复添加前部已添加的页码
                if (i > frontEnd && i < rearStart) {
                    items.add(new PageItem(i, Objects.equals(i, currentPageNo)));
                }
            }
        }

        // 判断是否需要后部省略号
        if (middleEnd < rearStart - 1) {
            items.add(new PageItem(null, false)); // 后部省略号
        }

        // 3. 添加后部页码
        for (Long i = Math.max(rearStart, 1); i <= totalPageCount; i++) {
            // 避免重复添加前面已添加的页码
            if (i > middleEnd) {
                items.add(new PageItem(i, Objects.equals(i, currentPageNo)));
            }
        }

        return items;
    }


    /**
     * 页码项内部类，表示单个页码或省略号
     */
    @Data
    public static class PageItem {

        @Schema(description = "页码值，null表示省略号")
        private Long pageNo;

        @Schema(description = "是否为当前页")
        private Boolean isCurrent;

        @Schema(description = "是否为省略号")
        private Boolean isEllipsis;

        public PageItem(Long pageNo, Boolean isCurrent) {
            this.pageNo = pageNo;
            this.isCurrent = isCurrent;
            this.isEllipsis = (pageNo == null);
        }
    }

    /**
     * 分页导航配置类
     */
    @Data
    public static class PageNavigationConfig {
        @Schema(description = "前部保留页码数（包含第一页）", defaultValue = "3")
        private Long frontPages = 3L;

        @Schema(description = "中部保留页码数（当前页左右）", defaultValue = "2")
        private Long middlePages = 3L;

        @Schema(description = "后部保留页码数（包含最后一页）", defaultValue = "3")
        private Long rearPages = 3L;

        @Schema(description = "显示省略号的最小总页数", defaultValue = "10")
        private Long minPagesForEllipsis = 3L;

        public PageNavigationConfig() {}

        public PageNavigationConfig(Long frontPages, Long middlePages, Long rearPages, Long minPagesForEllipsis) {
            this.frontPages = frontPages;
            this.middlePages = middlePages;
            this.rearPages = rearPages;
            this.minPagesForEllipsis = minPagesForEllipsis;
        }
    }
}
