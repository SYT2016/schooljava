package	com.dao;
import java.sql.*;
import java.util.*;
import com.vo.Classs_Address;
public class Classs_AddressDAO {
	/**
	* 根据id查询,返回Classs_Address,参数int id   根据id查询
	* @param id
	* @return
	*/
	public Classs_Address findById(int address_id) {
		String sql = "select * from Classs_Address where address_id=?";
		Object[] in = {address_id};
		ResultSet rs = DBUtil.executeQuery(sql, in);
		Classs_Address model = null;
		try {
			if(rs.next()) {
				model = new Classs_Address(
					rs.getInt("address_id"),
					rs.getInt("address_Classs"),
					rs.getInt("adderss_Name"),
					rs.getInt("adderss_Phone"),
					rs.getString("adderss_Note"));
			}
			DBUtil.close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	* 查询全部,返回List<Classs_Address>
	* @param id
	* @return
	*/
	public List<Classs_Address> findAll() {
		String sql = "select * from Classs_Address";
		ResultSet rs = DBUtil.executeQuery(sql);
		List<Classs_Address> modelList = new ArrayList<Classs_Address>();
		Classs_Address model = null;
		try {
			while(rs.next()) {
				model = new Classs_Address(
					rs.getInt("address_id"),
					rs.getInt("address_Classs"),
					rs.getInt("adderss_Name"),
					rs.getInt("adderss_Phone"),
					rs.getString("adderss_Note"));
				modelList.add(model);
			}
			DBUtil.close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelList;
	}
	/**
	* 分页查询,返回List<Classs_Address>,int page,int limit 第一个参数是第几页,第二个参数是多少条数据
	* @param id
	* @return
	*/
	public List<Classs_Address> Paging(int page,int limit) {
	//开始
	//第几页-1乘每页多少条数据+1
	int start=(page-1)*limit+1;//公式用于获取从哪里开始
	//结束
	//第几页乘每页多少条数据
	int end=page*limit;
	String sql = "select * from(SELECT *,ROW_NUMBER() over(order by address_id desc) row FROM [Classs_Address]) t where t.row>="+start+" and t.row<="+end;
		ResultSet rs = DBUtil.executeQuery(sql);
		List<Classs_Address> modelList = new ArrayList<Classs_Address>();
		Classs_Address model = null;
		try {
			while(rs.next()) {
				model = new Classs_Address(
					rs.getInt("address_id"),
					rs.getInt("address_Classs"),
					rs.getInt("adderss_Name"),
					rs.getInt("adderss_Phone"),
					rs.getString("adderss_Note"));
				modelList.add(model);
			}
			DBUtil.close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modelList;
	}
	/**
	* 添加方法,返回是否删除成功
	* @param
	* @return
	*/
	public int insert(Classs_Address model) {
		String sql = "insert into Classs_Address values(?,?,?,?) ";
		Object[] in = {model.getAddress_Classs(),model.getAdderss_Name(),model.getAdderss_Phone(),model.getAdderss_Note()};
		return DBUtil.executeUpdate(sql, in);
	}
	/**
	* 修改方法,返回是否删除成功,参数传入对象
	* @param model
	* @return
	*/
	public int update(Classs_Address model) {
		String sql = "update Classs_Address set address_Classs=?,adderss_Name=?,adderss_Phone=?,adderss_Note=? where address_id=? ";
		Object[] in = {model.getAddress_Classs(),model.getAdderss_Name(),model.getAdderss_Phone(),model.getAdderss_Note(),model.getAddress_id()};
		return DBUtil.executeUpdate(sql, in);
	}
	/**
	* 删除方法,返回是否删除成功,参数传入id 根据id进行删除
	* @param id
	* @return
	*/
	public int delete(int address_id) {
		String sql = "delete from Classs_Address where address_id=? ";
		Object[] in = {address_id};
		return DBUtil.executeUpdate(sql, in);
	}
	/**
	* 获取行数方法,返回多少行
	* @param
	* @return
	*/
	public int getcount(){
	int count=0;
	String sql = "select count(*) from Classs_Address";
	ResultSet rs = DBUtil.executeQuery(sql);
	try {
		while(rs.next()) {
			count=rs.getInt(1);
		}
		DBUtil.close(rs);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return count;
	}	
}
