package com.particle.global.datasource.sqlinit;

import com.particle.global.tool.condition.ConditionalOnClassTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自定义脚本初始化，主要是兼容schema中带有import的注释字符引入
 * </p>
 *
 * @author yangwei
 * @since 2021-09-17 17:19
 */

public class CustomDataSourceScriptDatabaseInitializer extends SqlDataSourceScriptDatabaseInitializer {

	public static String IMPORT_PREFIX = "-- import";

	@Autowired
	private SqlInitializationProperties initializationProperties;

	/**
	 * Creates a new {@link DataSourceScriptDatabaseInitializer} that will initialize the
	 * given {@code DataSource} using the given settings.
	 *
	 * @param dataSource data source to initialize
	 * @param settings   initialization settings
	 */
	public CustomDataSourceScriptDatabaseInitializer(DataSource dataSource, DatabaseInitializationSettings settings) {
		super(dataSource, settings);
	}

	@Override
	protected void runScripts(Scripts scripts) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.setContinueOnError(scripts.isContinueOnError());
		populator.setSeparator(scripts.getSeparator());
		if (scripts.getEncoding() != null) {
			populator.setSqlScriptEncoding(scripts.getEncoding().name());
		}
		List<String> importScriptsLocation = new ArrayList<>();
		List<String> temp = null;
		for (Resource resource : scripts) {

			temp = schemaImportSupport(resource);
			if (!CollectionUtils.isEmpty(temp)) {
				importScriptsLocation.addAll(temp);
			}
			if (!isEmpty(resource,scripts.getSeparator())) {
				populator.addScript(resource);
			}
		}


		if (!CollectionUtils.isEmpty(importScriptsLocation)) {
			// import 添加 createFrom
			DatabaseInitializationSettings settings = CustomDataSourceInitializationConfiguration.createFrom(importScriptsLocation, initializationProperties);
			try {
				CustomDataSourceScriptDatabaseInitializer customDataSourceScriptDatabaseInitializer = new CustomDataSourceScriptDatabaseInitializer(getDataSource(), settings);
				customDataSourceScriptDatabaseInitializer.setInitializationProperties(initializationProperties);
				customDataSourceScriptDatabaseInitializer.afterPropertiesSet();
			} catch (Exception e) {
				throw new RuntimeException(e);

			}

		}

		DatabasePopulatorUtils.execute(populator, getDataSource());



	}

	/**
	 * import 支持
	 * @param resource
	 */
	private List<String> schemaImportSupport(Resource resource) {

		List<String> result = new ArrayList<>();

		try (LineNumberReader lineNumberReader = new LineNumberReader(new EncodedResource(resource).getReader())) {
			String line = lineNumberReader.readLine();

			while (line != null){

				if (line.startsWith(IMPORT_PREFIX)) {
					ConditionalOnClassTool.ConditionalOnClassResult conditionalOnClassResult = ConditionalOnClassTool.checkResult(line);
					if (conditionalOnClassResult.getHasCondition()) {
						// -- import classpath:db/schema.component_dict.sql condition on class com.particle.dict.infrastructure.dos.Dict
						boolean present = conditionalOnClassResult.getClassPresent();
						if (!present) {
							line = lineNumberReader.readLine();
							continue;
						}else {
							result.add(conditionalOnClassResult.getExpression().replace(IMPORT_PREFIX,"").trim());
						}
					}else {
						result.add(conditionalOnClassResult.getExpression().replace(IMPORT_PREFIX,"").trim());
					}

				}
				line = lineNumberReader.readLine();
			}
			return result;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isEmpty(Resource resource,String separator){
		try {
			String script = readScript(new LineNumberReader(new EncodedResource(resource).getReader()), ScriptUtils.DEFAULT_COMMENT_PREFIXES, separator, ScriptUtils.DEFAULT_BLOCK_COMMENT_END_DELIMITER);
			if (StringUtils.hasText(script)) {
				return false;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	public void setInitializationProperties(SqlInitializationProperties initializationProperties) {
		this.initializationProperties = initializationProperties;
	}

	/**
	 * 下面代码及其关联代码，复制于{@link ScriptUtils#readScript(EncodedResource, String, String[], String)}
	 * @param lineNumberReader
	 * @param commentPrefixes
	 * @param separator
	 * @param blockCommentEndDelimiter
	 * @return
	 * @throws IOException
	 */
	private static String readScript(LineNumberReader lineNumberReader, @Nullable String[] commentPrefixes,
									 @Nullable String separator, @Nullable String blockCommentEndDelimiter) throws IOException {

		String currentStatement = lineNumberReader.readLine();
		StringBuilder scriptBuilder = new StringBuilder();
		while (currentStatement != null) {
			if ((blockCommentEndDelimiter != null && currentStatement.contains(blockCommentEndDelimiter)) ||
					(commentPrefixes != null && !startsWithAny(currentStatement, commentPrefixes, 0))) {
				if (scriptBuilder.length() > 0) {
					scriptBuilder.append('\n');
				}
				scriptBuilder.append(currentStatement);
			}
			currentStatement = lineNumberReader.readLine();
		}
		appendSeparatorToScriptIfNecessary(scriptBuilder, separator);
		return scriptBuilder.toString();
	}

	private static void appendSeparatorToScriptIfNecessary(StringBuilder scriptBuilder, @Nullable String separator) {
		if (separator == null) {
			return;
		}
		String trimmed = separator.trim();
		if (trimmed.length() == separator.length()) {
			return;
		}
		// separator ends in whitespace, so we might want to see if the script is trying
		// to end the same way
		if (scriptBuilder.lastIndexOf(trimmed) == scriptBuilder.length() - trimmed.length()) {
			scriptBuilder.append(separator.substring(trimmed.length()));
		}
	}
	private static boolean startsWithAny(String script, String[] prefixes, int offset) {
		for (String prefix : prefixes) {
			if (script.startsWith(prefix, offset)) {
				return true;
			}
		}
		return false;
	}
}
