package com.phr.rest.mybatis.shardingjdbc.enums;

import com.phr.rest.mybatis.shardingjdbc.algorithm.ModuloTableShardingAlgorithm;

public enum ShardingTableEnum {
	user("user","user_id",2,new ModuloTableShardingAlgorithm(2));

	public final String tableName;
	public final String shardingKey;
	public final Integer num;
	public final ModuloTableShardingAlgorithm moduloTableShardingAlgorithm;
	
	private ShardingTableEnum(String tableName,String shardingKey,Integer num,ModuloTableShardingAlgorithm moduloTableShardingAlgorithm) {
		this.tableName=tableName;
		this.shardingKey=shardingKey;
		this.num=num;
		this.moduloTableShardingAlgorithm=moduloTableShardingAlgorithm;
	}
}
