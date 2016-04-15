package com.lhjl.yygh.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DaoUtil extends SQLiteOpenHelper {
	public static final String DBNAME = Params.DBNAME;
	public static final int DBVS = 1;
	private static final String TABLE_GOUCAIJILU = "gcjl";

	public SharedPreferences sp;

	public DaoUtil(Context context) {
		super(context, DBNAME, null, DBVS);
	}

	public DaoUtil(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_GOUCAIJILU + " ("
				+ GouCaiJiLuColumns.g_time + " text,"
				+ GouCaiJiLuColumns.g_haoma + " text,"
				+ GouCaiJiLuColumns.g_zhushu + " text,"
				+ GouCaiJiLuColumns.g_beishu + " text,"
				+ GouCaiJiLuColumns.g_qishu + " text,"
				+ GouCaiJiLuColumns.g_amount + " text,"
				+ GouCaiJiLuColumns.g_jiorzi + " text,"
				+ GouCaiJiLuColumns.g_type + " text,"
				+ GouCaiJiLuColumns.g_phone + " text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOUCAIJILU);
		onCreate(db);
	}

	public static final String[] GOUCAIJILU_COLUMNS = {
			GouCaiJiLuColumns.g_time, GouCaiJiLuColumns.g_haoma,
			GouCaiJiLuColumns.g_zhushu, GouCaiJiLuColumns.g_beishu,
			GouCaiJiLuColumns.g_qishu, GouCaiJiLuColumns.g_amount,
			GouCaiJiLuColumns.g_jiorzi, GouCaiJiLuColumns.g_type,
			GouCaiJiLuColumns.g_phone };

	public class GouCaiJiLuColumns {
		public static final String g_time = "g_time"; // 投注时间
		public static final String g_haoma = "g_haoma";// 号码
		public static final String g_zhushu = "g_zhushu";// 注数
		public static final String g_beishu = "g_beishu";// 倍数
		public static final String g_qishu = "g_qishu"; // 期数
		public static final String g_amount = "g_amount";// 金额
		public static final String g_jiorzi = "g_jiorzi"; // 机�?或�?自�?
		public static final String g_type = "g_type";// 类型
		public static final String g_phone = "g_phone";// 绑定电话
	}

	// /**
	// *
	// *
	// * @param obj
	// * @param values
	// */
	// public void setValues(GoucaijiluInfo obj, ContentValues values) {
	// values.put(GouCaiJiLuColumns.g_time, obj.getG_time());
	// values.put(GouCaiJiLuColumns.g_haoma, obj.getG_haoma());
	// values.put(GouCaiJiLuColumns.g_zhushu, obj.getG_zhushu());
	// values.put(GouCaiJiLuColumns.g_beishu, obj.getG_beishu());
	// values.put(GouCaiJiLuColumns.g_qishu, obj.getG_qishu());
	// values.put(GouCaiJiLuColumns.g_amount, obj.getG_amount());
	// values.put(GouCaiJiLuColumns.g_jiorzi, obj.getG_jiorzi());
	// values.put(GouCaiJiLuColumns.g_type, obj.getG_type());
	// values.put(GouCaiJiLuColumns.g_phone, obj.getG_phone());
	//
	// }
	//
	// public static GoucaijiluInfo getObjByCursor_Gouwu(Cursor cursor) {
	//
	// GoucaijiluInfo obj = new GoucaijiluInfo();
	// obj.setG_time(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_time)));
	// obj.setG_haoma(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_haoma)));
	// obj.setG_zhushu(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_zhushu)));
	// obj.setG_beishu(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_beishu)));
	// obj.setG_qishu(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_qishu)));
	// obj.setG_amount(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_amount)));
	// obj.setG_jiorzi(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_jiorzi)));
	// obj.setG_type(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_type)));
	// obj.setG_phone(cursor.getString(cursor
	// .getColumnIndexOrThrow(GouCaiJiLuColumns.g_phone)));
	// return obj;
	// }
	//
	// // 添加�?��新纪�?
	//
	// public long insertWeiOne(GoucaijiluInfo obj) {
	// SQLiteDatabase db = null;
	// Cursor c = null;
	// long rowId = 0;
	// ContentValues values = new ContentValues();
	// setValues(obj, values);
	// try {
	// db = getWritableDatabase();
	// // c = db.query(TABLE_GOUCAIJILU, GOUCAIJILU_COLUMNS,
	// // null, null, null,
	// // null, null);
	// // while (c.moveToNext()) {
	// // if (c != null) {
	// // return -1;
	// //
	// // // rowId = db.insert(TABLE_GOUWUCHE, null, values);
	// // // System.out.println("cun");
	// // }
	// // }
	//
	// rowId = db.insert(TABLE_GOUCAIJILU, null, values);
	// System.out.println("cun");
	// } catch (Exception e) {
	// // TODO: handle exception
	// } finally {
	// if (db != null) {
	// db.close();
	// }
	// if (c != null) {
	// closeCusor(c);
	// }
	// }
	//
	// return rowId;
	// }
	//
	// // 查询记录
	//
	// public List<GoucaijiluInfo> queryAllGOUCAI() {
	// List<GoucaijiluInfo> maXinXis = new ArrayList<GoucaijiluInfo>();
	// SQLiteDatabase db = null;
	// Cursor cursor = null;
	// try {
	// db = getWritableDatabase();
	//
	// cursor = db.query(TABLE_GOUCAIJILU, GOUCAIJILU_COLUMNS, null, null,
	// null, null, null);
	// if (cursor == null) {
	// return null;
	// }
	// while (cursor.moveToNext()) {
	// GoucaijiluInfo obj = getObjByCursor_Gouwu(cursor);
	// Log.e(".........", obj.getG_beishu());
	// maXinXis.add(obj);
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// } finally {
	// if (db != null) {
	// db.close();
	// }
	// if (cursor != null) {
	// closeCusor(cursor);
	// }
	// }
	// return maXinXis;
	// }
	//
	// // 查询记录的�?�?
	// public long getCount() {
	// SQLiteDatabase db = null;
	// String sql = "select count(*) from gcjl";
	// db = getWritableDatabase();
	// Cursor c = db.rawQuery(sql, null);
	// c.moveToFirst();
	// long length = c.getLong(0);
	// c.close();
	// return length;
	// }
	//
	// /**
	// * 拿到�?��的记录条�?
	// *
	// * @param firstResult
	// * 从第几条数据�?��查询�?
	// * @param maxResult
	// * 每页显示多少条记录�?
	// * @return 当前页的记录
	// */
	// public List<GoucaijiluInfo> getAllItems(int firstResult, int maxResult) {
	// List<GoucaijiluInfo> maXinXis = new ArrayList<GoucaijiluInfo>();
	// SQLiteDatabase db = null;
	// db = getWritableDatabase();
	// String sql = "select * from gcjl limit ?,?";
	// Cursor mCursor = db.rawQuery(
	// sql,
	// new String[] { String.valueOf(firstResult),
	// String.valueOf(maxResult) });
	// while (mCursor.moveToNext()) {
	// GoucaijiluInfo obj = getObjByCursor_Gouwu(mCursor);
	// maXinXis.add(obj);
	// }
	// return maXinXis;
	// }

	/**
	 * cursor
	 * 
	 * @param cursor
	 */
	protected void closeCusor(Cursor cursor) {
		if (cursor != null) {
			cursor.close();
			cursor = null;
		}
	}
}
