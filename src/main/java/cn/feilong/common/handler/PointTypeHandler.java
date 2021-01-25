package cn.feilong.common.handler;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ByteOrderValues;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKBWriter;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理mysql空间数据类型和java类型直接转换,主要要用mybatis
 */
@MappedTypes(Point.class)
@MappedJdbcTypes({JdbcType.STRUCT})
public class PointTypeHandler extends BaseTypeHandler<Point> {

    /**
     * 把Java类型参数转换为对应的数据库类型
     *
     * @param ps        当前的PreparedStatement对象
     * @param i         当前参数位置
     * @param parameter 当前参数的Java对象
     * @param jdbcType  当前参数的数据库类型
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Point parameter, JdbcType jdbcType) throws SQLException {
        String wkt = parameter.toText();
        ps.setString(i, wkt);
    }

    /**
     * 获取数据结果集时把数据库类型转换为对应的Java类型
     *
     * @param rs         当前的结果集
     * @param columnName 当前的字段名称
     * @return 转换后的Java对象
     * @throws SQLException
     */
    @Override
    public Point getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Point point = convertToGeo(rs.getBytes(columnName));
        return point;
    }

    /**
     * 通过字段位置获取字段数据时把数据库类型转换为对应的Java类型
     *
     * @param rs          当前的结果集
     * @param columnIndex 当前字段的位置
     * @return 转换后的Java对象
     * @throws SQLException
     */
    @Override
    public Point getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertToGeo(rs.getBytes(columnIndex));
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
    public Point getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertToGeo(cs.getBytes(columnIndex));
    }



    /**
     * bytes转Geo对象
     * @param bytes
     * @return
     */
    private Point convertToGeo(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            byte[] geomBytes = ByteBuffer.allocate(bytes.length - 4).order(ByteOrder.LITTLE_ENDIAN)
                    .put(bytes, 4, bytes.length - 4).array();
            WKBReader wkbReader = new WKBReader();
            Geometry geometry = wkbReader.read(geomBytes);
            return (Point) geometry;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * geo转bytes
     * @param geometry
     * @return
     * @throws IOException
     */
    private byte[] convertToBytes(Geometry geometry) throws IOException {
        WKBWriter wkbWriter = new WKBWriter();
        byte[] geometryBytes = wkbWriter.write(geometry);
        byte[] wkb = new byte[geometryBytes.length + 4];
        //设置SRID为4326
        ByteOrderValues.putInt(4326, wkb, ByteOrderValues.LITTLE_ENDIAN);
        System.arraycopy(geometryBytes, 0, wkb, 4, geometryBytes.length);
        return wkb;
    }
}