package com.dcoj.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JsonArray格式处理器
 *
 * @author WANGQING
 */
@MappedTypes(JSONArray.class)       //javaType 配置java类型，例如String, 如果配上javaType, 那么指定的typeHandler就只作用于指定的类型
@MappedJdbcTypes(JdbcType.VARCHAR) //jdbcType 配置数据库基本数据类型，例如varchar, 如果配上jdbcType, 那么指定的typeHandler就只作用于指定的类型
public class JsonArrayTypeHandler extends BaseTypeHandler<JSONArray> {

    /**
     * 设置非空参数,把Java类型参数转换为对应的数据库类型
     *
     * @param ps        当前的PreparedStatement对象
     * @param i         当前参数位置
     * @param parameter 当前参数的Java对象
     * @param jdbcType  当前参数的数据库类型
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONArray parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toJSONString(parameter,SerializerFeature.WriteNullStringAsEmpty));
    }

    /**
     * 根据列名，获取可以为空的结果,获取数据结果集时把数据库类型转换为对应的Java类型
     *
     * @param rs         当前的结果集
     * @param columnName 当前的字段名称
     * @return 转换后的Java对象
     * @throws SQLException
     */
    @Override
    public JSONArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String sqlJson = rs.getString(columnName);
        if (null != sqlJson) {
            return JSONObject.parseArray(sqlJson);
        }
        return null;
    }

    /**
     * 根据列索引，获取字段数据时把数据库类型转换为对应的Java类型
     *
     * @param rs          当前的结果集
     * @param columnIndex 当前字段的位置
     * @return 转换后的Java对象
     * @throws SQLException
     */
    @Override
    public JSONArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String sqlJson = rs.getString(columnIndex);
        if (null != sqlJson) {
            return JSONObject.parseArray(sqlJson);
        }
        return null;
    }

    /**
     * 调用存储过程后把数据库类型的数据转换为对应的Java类型
     *
     * @param cs          当前的CallableStatement执行后的CallableStatement
     * @param columnIndex 当前输出参数的位置
     * @return
     * @throws SQLException
     */
    @Override
    public JSONArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String sqlJson = cs.getString(columnIndex);
        if (null != sqlJson) {
            return JSONObject.parseArray(sqlJson);
        }
        return null;
    }
}

