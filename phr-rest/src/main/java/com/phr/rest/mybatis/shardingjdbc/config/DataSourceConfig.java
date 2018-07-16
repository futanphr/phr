package com.phr.rest.mybatis.shardingjdbc.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule.TableRuleBuilder;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.phr.rest.mybatis.shardingjdbc.algorithm.ModuloDatabaseShardingAlgorithm;
import com.phr.rest.mybatis.shardingjdbc.algorithm.ModuloTableShardingAlgorithm;
import com.phr.rest.mybatis.shardingjdbc.constants.DataSourceConstants;
import com.phr.rest.mybatis.shardingjdbc.enums.ShardingTableEnum;

/**
 * 数据源配置
 *
 * @author happyxiaofan
 * @since 0.0.1
 */
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = DataSourceConstants.DATASOURCE_PREFIX)
@MapperScan(basePackages = { DataSourceConstants.MAPPER_PACKAGE }, sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class DataSourceConfig {

    @Bean(name = "mybatisDataSource")
    public DataSource getDataSource() throws SQLException {
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("springboot_0", mybatisDataSource1());
        dataSourceMap.put("springboot_1", mybatisDataSource2());

        //设置默认库，两个库以上时必须设置默认库。默认库的数据源名称必须是dataSourceMap的key之一
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "springboot_0");

        //设置分表映射
        /*TableRule userTableRule = TableRule.builder("user")
                .generateKeyColumn("user_id") //将user_id作为分布式主键
                .actualTables(Arrays.asList("user_0", "user_1"))
                .dataSourceRule(dataSourceRule)
                .build();*/
        List<TableRule> tableRules=getTableRuleList(dataSourceRule);

        //具体分库分表策略
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(tableRules)
                .databaseShardingStrategy(new DatabaseShardingStrategy("city_id", new ModuloDatabaseShardingAlgorithm()))
//                .tableShardingStrategy(new TableShardingStrategy("user_id", new ModuloTableShardingAlgorithm())).build();
                .build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);

        //return new ShardingDataSource(shardingRule);
        return dataSource;
    }

    private List<TableRule> getTableRuleList(DataSourceRule dataSourceRule){
    	List<TableRule> tableRuleList=new ArrayList<TableRule>();
    	for(ShardingTableEnum shardingTableEnum:ShardingTableEnum.values()) {
    		List<String> tableList=new ArrayList<String>();
    		for(int i=0;i<shardingTableEnum.num;i++) {
    			tableList.add(new StringBuffer().append(shardingTableEnum.tableName).append("_").append(i).toString());
    		}
    		TableRule tableRule=new TableRule.TableRuleBuilder(shardingTableEnum.tableName).actualTables(tableList).dataSourceRule(dataSourceRule).tableShardingStrategy(new TableShardingStrategy(shardingTableEnum.shardingKey, shardingTableEnum.moduloTableShardingAlgorithm)).build();
    		tableRuleList.add(tableRule);
    	}
    	return tableRuleList;
    }
    @ConfigurationProperties(prefix = "mybatis.biz.datasource1")
    @Bean("datasource1")
    @Primary
    public DataSource mybatisDataSource1() throws SQLException {
        return new DruidDataSource();
    }
    @ConfigurationProperties(prefix = "mybatis.biz.datasource2")
    @Bean("datasource2")
    public DataSource mybatisDataSource2() throws SQLException {
    	return new DruidDataSource();
    }

    /**
     * Sharding-jdbc的事务支持
     *
     * @return
     */
    @Bean(name = "mybatisTransactionManager")
    public DataSourceTransactionManager mybatisTransactionManager(@Qualifier("mybatisDataSource") DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisSqlSessionFactory")
    public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mybatisDataSource);
        return sessionFactory.getObject();
    }

}
