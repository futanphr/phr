package com.phr.common.mybatis;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phr.common.mybatis.SplitSql.SplitSqlResult;
import com.phr.common.utils.StringUtils;


@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class ShardingInterceptor implements Interceptor {
	// 日志对象
	protected static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final DefaultReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		ShardingResultInterceptor.hasMerge.set(false);

		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		BoundSql boundSql = statementHandler.getBoundSql();

		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

		if (StringUtils.isNotNull(originalSql)) {

			Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");

			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
					.getValue("delegate.mappedStatement");
			String id = mappedStatement.getId();
			String className = id.substring(0, id.lastIndexOf("."));
			Class<?> classObj = Class.forName(className);
			// 根据配置自动生成分表SQL
			TableSeg tableSeg = classObj.getAnnotation(TableSeg.class);
			if (tableSeg != null) {
				SplitSql makeSql = new SplitSql(mappedStatement, parameterObject, boundSql);
				SplitSqlResult splitSqlResult = makeSql.getShardingSql(originalSql, tableSeg);

				originalSql = splitSqlResult.getSql();
				metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);

				metaStatementHandler.setValue("delegate.boundSql.parameterMappings",
						splitSqlResult.getParameterMappings());

			}

		}

		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds != null && rowBounds != RowBounds.DEFAULT) {

			Dialect dialect = new MySQLDialect();
			originalSql = dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
			metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);
			metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
			metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		}

		Object object = invocation.proceed();
		return object;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}
}
