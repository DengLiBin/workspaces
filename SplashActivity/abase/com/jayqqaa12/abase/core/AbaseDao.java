package com.jayqqaa12.abase.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import net.tsz.afinal.db.sqlite.CursorUtils;
import net.tsz.afinal.db.sqlite.DbModel;
import net.tsz.afinal.db.sqlite.SqlBuilder;
import net.tsz.afinal.db.sqlite.SqlInfo;
import net.tsz.afinal.db.table.KeyValue;
import net.tsz.afinal.db.table.ManyToOne;
import net.tsz.afinal.db.table.OneToMany;
import net.tsz.afinal.db.table.TableInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jayqqaa12.abase.exception.DaoException;
import com.jayqqaa12.abase.util.common.TAG;

public class AbaseDao
{
	private static HashMap<String, AbaseDao> daoMap = new HashMap<String, AbaseDao>();

	private SQLiteDatabase db;
	private DaoConfig config;

	private AbaseDao()
	{
	};

	private AbaseDao(DaoConfig config)
	{
		this.db = new SqliteDbHelper(Abase.getContext(), config.getDbName(), config.getDbVersion(), config.getDbUpdateListener())
				.getWritableDatabase();
		this.config = config;
	}

	private synchronized static AbaseDao getInstance(String dbPath, String dbName, boolean debug)
	{

		AbaseDao dao = daoMap.get(dbPath);
		if (dao == null)
		{
			dao = new AbaseDao();

			dao.db = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
			DaoConfig dc = new DaoConfig();
			dc.setDbName(dbName);
			dc.setDbVersion(1);
			dc.setDebug(debug);
			dao.config = dc;

			daoMap.put(dbPath, dao);
		}
		return dao;

	}

	private synchronized static AbaseDao getInstance(DaoConfig daoConfig)
	{
		AbaseDao dao = daoMap.get(daoConfig.getDbName());
		if (dao == null)
		{
			dao = new AbaseDao(daoConfig);
			daoMap.put(daoConfig.getDbName(), dao);
		}
		return dao;
	}

	/**
	 * 当 使用 已经存在的 数据库时 用这个方法 打开
	 * 
	 * @param dbPath
	 *            不包括数据库名
	 * @param dbName
	 *            数据库名字 不用加后缀
	 * @return
	 */
	public static AbaseDao open(String dbPath, String dbName)
	{
		return getInstance(dbPath + File.separator + dbName + ".db", dbName + ".db", true);

	}

	/**
	 * 当 使用 已经存在的 数据库时 用这个方法 打开
	 * 
	 * @param dbPath
	 *            包括数据库名
	 * @return
	 */
	public static AbaseDao open(String dbPath, String dbName, boolean debug)
	{
		return getInstance(dbPath + File.separator + dbName + ".db", dbName + ".db", debug);

	}

	/**
	 * 创建db
	 * 
	 * @param context
	 */
	public static AbaseDao create()
	{
		DaoConfig config = new DaoConfig();
		return getInstance(config);

	}

	/**
	 * 
	 * @param context
	 * @param isDebug
	 *            是否是debug模式（debug模式进行数据库操作的时候将会打印sql语句）
	 */
	public static AbaseDao create(boolean isDebug)
	{
		DaoConfig config = new DaoConfig();
		config.setDebug(isDebug);
		return getInstance(config);

	}

	/**
	 * 创建db
	 * 
	 * @param context
	 * @param dbName
	 *            数据库名称
	 */
	public static AbaseDao create(String dbName)
	{
		DaoConfig config = new DaoConfig();
		config.setDbName(dbName);

		return getInstance(config);
	}

	/**
	 * 
	 * @param context
	 * @param dbName
	 *            数据库名称
	 * @param isDebug
	 *            是否为debug模式（debug模式进行数据库操作的时候将会打印sql语句）
	 */
	public static AbaseDao create(String dbName, boolean isDebug)
	{
		DaoConfig config = new DaoConfig();
		config.setDbName(dbName);
		config.setDebug(isDebug);
		return getInstance(config);
	}

	/**
	 * 
	 * @param context
	 *            上下文
	 * @param dbName
	 *            数据库名字
	 * @param isDebug
	 *            是否是调试模式：调试模式会log出sql信息
	 * @param dbVersion
	 *            数据库版本信息
	 * @param dbUpdateListener
	 *            数据库升级监听器：如果监听器为null，升级的时候将会清空所所有的数据
	 * @return
	 */
	public static AbaseDao create(String dbName, boolean isDebug, int dbVersion, DbUpdateListener dbUpdateListener)
	{
		DaoConfig config = new DaoConfig();
		config.setDbName(dbName);
		config.setDebug(isDebug);
		config.setDbVersion(dbVersion);
		config.setDbUpdateListener(dbUpdateListener);
		return getInstance(config);
	}

	/**
	 * 
	 * 
	 * 保存数据库，
	 * 
	 * 
	 * @param entity
	 */
	public void save(Object entity)
	{
		checkTableExist(entity.getClass());
		exeSqlInfo(SqlBuilder.buildInsertSql(entity));
	}

	/**
	 * 保存数据到数据库<br />
	 * <b>注意：</b><br />
	 * 保存成功后，entity的主键将被赋值（或更新）为数据库的主键， 只针对自增长的id有效
	 * 
	 * @param entity
	 *            要保存的数据
	 * @return ture： 保存成功 false:保存失败
	 */
	public boolean saveBindId(Object entity)
	{
		checkTableExist(entity.getClass());
		List<KeyValue> entityKvList = SqlBuilder.getSaveKeyValueListByEntity(entity);
		if (entityKvList != null && entityKvList.size() > 0)
		{
			TableInfo tf = TableInfo.get(entity.getClass());
			ContentValues cv = new ContentValues();
			insertContentValues(entityKvList, cv);
			Long id = db.insert(tf.getTableName(), null, cv);
			if (id == -1) return false;
			tf.getId().setValue(entity, id);
			return true;
		}
		return false;
	}

	/**
	 * 把List<KeyValue>数据存储到ContentValues
	 * 
	 * @param list
	 * @param cv
	 */
	private void insertContentValues(List<KeyValue> list, ContentValues cv)
	{
		if (list != null && cv != null)
		{
			for (KeyValue kv : list)
			{
				cv.put(kv.getKey(), kv.getValue().toString());
			}
		}
		else
		{
			Log.w(TAG.DB, "insertContentValues: List<KeyValue> is empty or ContentValues is empty!");
		}

	}

	/**
	 * 更新数据 （主键ID必须不能为空）
	 * 
	 * 根据主键id 更新
	 * 
	 * @param entity
	 */
	public void update(Object entity)
	{
		checkTableExist(entity.getClass());
		exeSqlInfo(SqlBuilder.getUpdateSqlAsSqlInfo(entity));
	}

	/**
	 * 根据更新所有数据
	 * 
	 * @param entity
	 */
	public void updateAll(Object entity)
	{
		update(entity, null);

	}

	/**
	 * 根据条件更新数据
	 * 
	 * @param entity
	 * @param where
	 *            条件为空的时候，将会更新所有的数据
	 */
	public void update(Object entity, String where)
	{
		checkTableExist(entity.getClass());
		exeSqlInfo(SqlBuilder.getUpdateSqlAsSqlInfo(entity, where));
	}

	/**
	 * 删除数据
	 * 
	 * @param entity
	 *            entity的主键不能为空
	 */
	public void delete(Object entity)
	{
		checkTableExist(entity.getClass());
		exeSqlInfo(SqlBuilder.buildDeleteSql(entity));
	}

	/**
	 * 根据主键删除数据
	 * 
	 * @param clazz
	 *            要删除的实体类
	 * @param id
	 *            主键值
	 */
	public void deleteById(Class<?> clazz, Object id)
	{
		checkTableExist(clazz);
		exeSqlInfo(SqlBuilder.buildDeleteSql(clazz, id));

	}

	/**
	 * 删除 全部
	 * 
	 * @param clazz
	 *            要删除的实体类
	 */
	public void deleteAll(Class<?> clazz)
	{
		deleteByWhere(clazz, null);
	}

	/**
	 * 根据条件删除数据
	 * 
	 * @param clazz
	 * @param where
	 *            条件为空的时候 将会删除所有的数据
	 */
	public void deleteByWhere(Class<?> clazz, String where)
	{
		checkTableExist(clazz);
		String sql = SqlBuilder.buildDeleteSql(clazz, where);
		debugSql(sql);
		db.execSQL(sql);
	}

	private void exeSqlInfo(SqlInfo sqlInfo)
	{
		if (sqlInfo != null)
		{
			debugSql(sqlInfo.getSql());
			db.execSQL(sqlInfo.getSql(), sqlInfo.getBindArgsAsArray());
		}
		else
		{
			Log.e(TAG.DB, "sava error:sqlInfo is null");
		}
	}

	/**
	 * 根据主键查找数据（默认不查询多对一或者一对多的关联数据）
	 * 
	 * @param id
	 * @param clazz
	 */
	public <T> T findById(Class<T> clazz, Object id)
	{
		checkTableExist(clazz);
		SqlInfo sqlInfo = SqlBuilder.getSelectSqlAsSqlInfo(clazz, id);
		if (sqlInfo != null)
		{
			debugSql(sqlInfo.getSql());
			Cursor cursor = db.rawQuery(sqlInfo.getSql(), sqlInfo.getBindArgsAsStringArray());
			try
			{
				if (cursor.moveToNext()) { return CursorUtils.getEntity(cursor, clazz); }
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				cursor.close();
			}
		}
		return null;
	}

	/**
	 * 根据主键查找，同时查找“多对一”的数据（如果有多个“多对一”属性，则查找所有的“多对一”属性）
	 * 
	 * @param id
	 * @param clazz
	 */
	public <T> T findWithManyToOneById(Object id, Class<T> clazz)
	{
		checkTableExist(clazz);
		String sql = SqlBuilder.getSelectSQL(clazz, id);
		debugSql(sql);
		DbModel dbModel = findDbModelBySQL(sql);
		if (dbModel != null)
		{
			T entity = CursorUtils.dbModel2Entity(dbModel, clazz);
			if (entity != null)
			{
				try
				{
					Collection<ManyToOne> manys = TableInfo.get(clazz).manyToOneMap.values();
					for (ManyToOne many : manys)
					{
						Object obj = dbModel.get(many.getColumn());
						if (obj != null)
						{
							@SuppressWarnings("unchecked")
							T manyEntity = (T) findById(many.getDataType(), Integer.valueOf(obj.toString()));
							if (manyEntity != null)
							{
								many.setValue(entity, manyEntity);
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			return entity;
		}

		return null;
	}

	/**
	 * 根据主键查找，同时查找“多对一”的数据（只查找findClass中的类的数据）
	 * 
	 * @param id
	 * @param clazz
	 * @param findClass
	 *            要查找的类
	 */
	public <T> T findWithManyToOneById(Object id, Class<T> clazz, Class<?>... findClass)
	{
		checkTableExist(clazz);
		String sql = SqlBuilder.getSelectSQL(clazz, id);
		debugSql(sql);
		DbModel dbModel = findDbModelBySQL(sql);
		if (dbModel != null)
		{
			T entity = CursorUtils.dbModel2Entity(dbModel, clazz);
			if (entity != null)
			{
				try
				{
					Collection<ManyToOne> manys = TableInfo.get(clazz).manyToOneMap.values();
					for (ManyToOne many : manys)
					{
						boolean isFind = false;
						for (Class<?> mClass : findClass)
						{
							if (many.getManyClass() == mClass)
							{
								isFind = true;
								break;
							}
						}

						if (isFind)
						{
							@SuppressWarnings("unchecked")
							T manyEntity = (T) findById(many.getDataType(), dbModel.get(many.getColumn()));
							if (manyEntity != null)
							{
								many.setValue(entity, manyEntity);
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			return entity;
		}
		return null;
	}

	/**
	 * 根据主键查找，同时查找“一对多”的数据（如果有多个“一对多”属性，则查找所有的一对多”属性）
	 * 
	 * @param id
	 * @param clazz
	 */
	public <T> T findWithOneToManyById(Object id, Class<T> clazz)
	{
		checkTableExist(clazz);
		String sql = SqlBuilder.getSelectSQL(clazz, id);
		debugSql(sql);
		DbModel dbModel = findDbModelBySQL(sql);
		if (dbModel != null)
		{
			T entity = CursorUtils.dbModel2Entity(dbModel, clazz);
			if (entity != null)
			{
				try
				{
					Collection<OneToMany> ones = TableInfo.get(clazz).oneToManyMap.values();
					for (OneToMany one : ones)
					{
						List<?> list = findAllByWhere(one.getOneClass(), one.getColumn() + "=" + id);
						if (list != null)
						{
							one.setValue(entity, list);
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			return entity;
		}

		return null;
	}

	/**
	 * 根据主键查找，同时查找“一对多”的数据（只查找findClass中的“一对多”）
	 * 
	 * @param id
	 * @param clazz
	 * @param findClass
	 */
	public <T> T findWithOneToManyById(Object id, Class<T> clazz, Class<?>... findClass)
	{
		checkTableExist(clazz);
		String sql = SqlBuilder.getSelectSQL(clazz, id);
		debugSql(sql);
		DbModel dbModel = findDbModelBySQL(sql);
		if (dbModel != null)
		{
			T entity = CursorUtils.dbModel2Entity(dbModel, clazz);
			if (entity != null)
			{
				try
				{
					Collection<OneToMany> ones = TableInfo.get(clazz).oneToManyMap.values();
					for (OneToMany one : ones)
					{
						boolean isFind = false;
						for (Class<?> mClass : findClass)
						{
							if (one.getOneClass().equals(mClass.getName()))
							{
								isFind = true;
								break;
							}
						}

						if (isFind)
						{
							List<?> list = findAllByWhere(one.getOneClass(), one.getColumn() + "=" + id);
							if (list != null)
							{
								one.setValue(entity, list);
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			return entity;
		}

		return null;
	}

	/**
	 * 查询数量
	 * 
	 * @param clazz
	 * @return
	 */
	public int count(Class<?> clazz)
	{
		return findAll(clazz).size();
	}

	/**
	 * 
	 * @param clazz
	 * @param where
	 * @return
	 */
	public int count(Class<?> clazz, String where)
	{
		return findAllByWhere(clazz, where).size();
	}

	/**
	 * 查找所有的数据
	 * 
	 * @param clazz
	 */
	public <T> List<T> findAll(Class<T> clazz)
	{
		checkTableExist(clazz);
		return findAllBySql(clazz, SqlBuilder.getSelectSQL(clazz));
	}

	/**
	 * 查找所有数据
	 * 
	 * @param clazz
	 * @param orderBy
	 *            排序的字段
	 */
	public <T> List<T> findAll(Class<T> clazz, String orderBy)
	{
		checkTableExist(clazz);
		return findAllBySql(clazz, SqlBuilder.getSelectSQL(clazz) + " ORDER BY " + orderBy + " DESC");
	}

	/**
	 * 根据条件查找所有数据
	 * 
	 * @param clazz
	 * @param where
	 *            条件为空的时候查找所有数据
	 */
	public <T> List<T> findAllByWhere(Class<T> clazz, String where)
	{
		checkTableExist(clazz);
		return findAllBySql(clazz, SqlBuilder.getSelectSQLByWhere(clazz, where));
	}

	/**
	 * 根据条件查找数据
	 * 
	 * true 找到了
	 * 
	 * false 没有找到
	 * 
	 * @param clazz
	 * @param where
	 *            条件为空的时候查找所有数据
	 */
	public boolean isFindByWhere(Class<?> clazz, String where)
	{
		return findAllByWhere(clazz, where).size()==0 ? false : true;
	}

	/**
	 * 根据条件查找数据 true 找到了
	 * 
	 * false 没有找到
	 * 
	 * @param clazz
	 * @param where
	 *            条件为空的时候查找所有数据
	 */
	public boolean isFindAllByWhere(Class<?> clazz, String where)
	{
		return findAllByWhere(clazz, where).size() == 0 ? false : true;
	}

	/**
	 * 根据条件查找数据
	 * 
	 * 如果找到的数量 大于1 @throws DbException
	 * 
	 * 
	 * @param clazz
	 * @param where
	 *            条件为空的时候查找所有数据
	 */
	public <T> T findByWhere(Class<T> clazz, String where)
	{
		List<T> list = findAllByWhere(clazz, where);
		
		
		if(list.size()>1) throw new  DaoException("find size  >1  maybe find error !!"); 
		

		return list.size() > 0 ? list.get(0) : null;

	}

	/**
	 * 根据条件查找所有数据
	 * 
	 * @param clazz
	 * @param where
	 *            条件为空的时候查找所有数据
	 * @param orderBy
	 *            排序字段
	 */
	public <T> List<T> findAllByWhere(Class<T> clazz, String where, String orderBy)
	{
		checkTableExist(clazz);
		return findAllBySql(clazz, SqlBuilder.getSelectSQLByWhere(clazz, where) + " ORDER BY '" + orderBy + "' DESC");
	}

	/**
	 * 根据条件查找所有数据
	 * 
	 * @param clazz
	 * @param strSQL
	 */
	private <T> List<T> findAllBySql(Class<T> clazz, String strSQL)
	{
		checkTableExist(clazz);
		debugSql(strSQL);
		Cursor cursor = db.rawQuery(strSQL, null);
		try
		{
			List<T> list = new ArrayList<T>();
			while (cursor.moveToNext())
			{
				T t = CursorUtils.getEntity(cursor, clazz);
				list.add(t);
			}
			return list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (cursor != null) cursor.close();
			cursor = null;
		}
		return null;
	}

	/**
	 * 直接 通过 sql 查 返回 单个的 string 字符串
	 * 
	 * @param sql
	 */
	public String findStringBySql(String sql, String columName)
	{
		DbModel model = findDbModelBySQL(sql);
		if (model != null) return model.getString(columName);
		else return null;
	}

	/**
	 * 直接 通过 sql 查 返回 多个的 string 字符串
	 * 
	 * @param sql
	 */
	public List<String> findAllStringBySql(String sql, String columName)
	{
		List<String> list = new ArrayList<String>();

		for (DbModel dm : findDbModelListBySQL(sql))
		{
			list.add(dm.getString(columName));
		}
		return list;
	}

	/**
	 * 根据sql语句查找数据，这个一般用于数据统计
	 * 
	 * @param strSQL
	 */
	public DbModel findDbModelBySQL(String strSQL)
	{
		debugSql(strSQL);
		Cursor cursor = db.rawQuery(strSQL, null);
		try
		{
			if (cursor.moveToNext()) { return CursorUtils.getDbModel(cursor); }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cursor.close();
		}
		return null;
	}

	/**
	 * 批量
	 * 
	 * @param strSQL
	 * @return
	 */

	public List<DbModel> findDbModelListBySQL(String strSQL)
	{
		debugSql(strSQL);
		Cursor cursor = db.rawQuery(strSQL, null);
		List<DbModel> dbModelList = new ArrayList<DbModel>();
		try
		{
			while (cursor.moveToNext())
			{
				dbModelList.add(CursorUtils.getDbModel(cursor));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cursor.close();
		}
		return dbModelList;
	}

	private void checkTableExist(Class<?> clazz)
	{
		if (!tableIsExist(TableInfo.get(clazz)))
		{
			String sql = SqlBuilder.getCreatTableSQL(clazz);
			debugSql(sql);
			db.execSQL(sql);
		}
	}

	private boolean tableIsExist(TableInfo table)
	{
		if (table.isCheckDatabese()) return true;

		Cursor cursor = null;
		try
		{
			String sql = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='" + table.getTableName() + "' ";
			debugSql(sql);
			cursor = db.rawQuery(sql, null);
			if (cursor != null && cursor.moveToNext())
			{
				int count = cursor.getInt(0);
				if (count > 0)
				{
					table.setCheckDatabese(true);
					return true;
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (cursor != null) cursor.close();
			cursor = null;
		}

		return false;
	}

	private void debugSql(String sql)
	{
		if (config != null && config.isDebug()) android.util.Log.d("Debug SQL", ">>>>>>  " + sql);
	}

	public static class DaoConfig
	{
		private String dbName = "abase.db";// 数据库名字
		private int dbVersion = 1;// 数据库版本
		private boolean debug = true;
		private DbUpdateListener dbUpdateListener;

		public String getDbName()
		{
			return dbName;
		}

		public void setDbName(String dbName)
		{
			this.dbName = dbName;
		}

		public int getDbVersion()
		{
			return dbVersion;
		}

		public void setDbVersion(int dbVersion)
		{
			this.dbVersion = dbVersion;
		}

		public boolean isDebug()
		{
			return debug;
		}

		public void setDebug(boolean debug)
		{
			this.debug = debug;
		}

		public DbUpdateListener getDbUpdateListener()
		{
			return dbUpdateListener;
		}

		public void setDbUpdateListener(DbUpdateListener dbUpdateListener)
		{
			this.dbUpdateListener = dbUpdateListener;
		}

	}

	class SqliteDbHelper extends SQLiteOpenHelper
	{

		private DbUpdateListener mDbUpdateListener;

		public SqliteDbHelper(Context context, String name, int version, DbUpdateListener dbUpdateListener)
		{
			super(context, name, null, version);
			this.mDbUpdateListener = dbUpdateListener;
		}

		public void onCreate(SQLiteDatabase db)
		{
		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			if (mDbUpdateListener != null) mDbUpdateListener.onUpgrade(db, oldVersion, newVersion);

			else
			{ // 清空所有的数据信息
				Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type ='table'", null);
				if (cursor != null)
				{
					while (cursor.moveToNext())
					{
						db.execSQL("DROP TABLE " + cursor.getString(0));
					}
				}
				if (cursor != null)
				{
					cursor.close();
					cursor = null;
				}
			}
		}

	}

	public interface DbUpdateListener
	{
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
	}

}
