package imooc.seckill.interceptor;

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
import org.apache.ibatis.reflection.SystemMetaObject;

@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }))
public class MybatisInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		// 创建MetaObject，用来获取statementHandler的私有变量值
		MetaObject metaObject = MetaObject.forObject(statementHandler,
				SystemMetaObject.DEFAULT_OBJECT_FACTORY,
				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
				new DefaultReflectorFactory());
		MappedStatement mappedStatement = (MappedStatement) metaObject
				.getValue("delegate.mappedStatement");
		String id = mappedStatement.getId();
		// if (id.matches(".+ByPage$")) {
		// 获取将要执行的sql
		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		String sql = boundSql.getSql();
		String countSql = "Select count(*) From (" + sql + ")";
		System.out.println(sql);
		// 查询总条数
//		String countSql = "select count(*) from (" + sql + ") a";
//		Connection connection = (Connection) invocation.getArgs()[0];
//		PreparedStatement countStatement = connection
//				.prepareStatement(countSql);
//		ParameterHandler parameterHandler = (ParameterHandler) metaObject
//				.getValue("delegate.parameterHandler");
//		parameterHandler.setParameters(countStatement);
//		ResultSet rs = countStatement.executeQuery();

//		Map<?, ?> parameter = (Map<?, ?>) boundSql.getParameterObject();
		// parameter.get("")
		// }
//		invocation.getMethod();
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		String test = properties.getProperty("test");
		System.out.println(test);
	}

}
