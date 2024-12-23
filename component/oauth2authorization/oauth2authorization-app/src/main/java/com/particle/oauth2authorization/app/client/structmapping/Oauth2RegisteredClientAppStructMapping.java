package com.particle.oauth2authorization.app.client.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientPageQueryCommand;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientQueryListCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClient;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClientId;
import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * oauth2客户端 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class Oauth2RegisteredClientAppStructMapping  implements IBaseQueryCommandMapStruct<Oauth2RegisteredClientDO>{
	public static Oauth2RegisteredClientAppStructMapping instance = Mappers.getMapper( Oauth2RegisteredClientAppStructMapping.class );

	protected Long map(Oauth2RegisteredClientId oauth2RegisteredClientId){
		if (oauth2RegisteredClientId == null) {
			return null;
		}
		return oauth2RegisteredClientId.getId();
	}
	/**
	 * oauth2客户端领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link Oauth2RegisteredClientAppStructMapping#map(Oauth2RegisteredClientId)}
	 * @param oauth2RegisteredClient
	 * @return
	 */
	public abstract Oauth2RegisteredClientVO toOauth2RegisteredClientVO(Oauth2RegisteredClient oauth2RegisteredClient);


	/**
	 * 数据对象转视图对象
	 * @param oauth2RegisteredClientDO
	 * @return
	 */
	public abstract Oauth2RegisteredClientVO oauth2RegisteredClientDOToOauth2RegisteredClientVO(Oauth2RegisteredClientDO oauth2RegisteredClientDO);

	/**
	 * 批量转换
	 * @param oauth2RegisteredClientDOs
	 * @return
	 */
	public abstract List<Oauth2RegisteredClientVO> oauth2RegisteredClientDOsToOauth2RegisteredClientVOs(List<Oauth2RegisteredClientDO> oauth2RegisteredClientDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<Oauth2RegisteredClientVO> infrastructurePageToPageResponse(Page<Oauth2RegisteredClientDO> page) {
		return PageResponse.of(oauth2RegisteredClientDOsToOauth2RegisteredClientVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public Oauth2RegisteredClientDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof Oauth2RegisteredClientPageQueryCommand) {
			return pageQueryCommandToDO((Oauth2RegisteredClientPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof Oauth2RegisteredClientQueryListCommand) {
			return queryListCommandToDO(((Oauth2RegisteredClientQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract Oauth2RegisteredClientDO pageQueryCommandToDO(Oauth2RegisteredClientPageQueryCommand oauth2RegisteredClientPageQueryCommand);

	public abstract Oauth2RegisteredClientDO queryListCommandToDO(Oauth2RegisteredClientQueryListCommand oauth2RegisteredClientQueryListCommand);
}
