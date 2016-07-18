package provider;

import openhelper.MyOpenHelper;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * �Զ��������ṩ�ߣ�Ҫ���嵥�ļ������ã��Ĵ������activity,service,receiver,provider
 * @author Administrator
 *
 */
public class PersonProvider extends ContentProvider {

	MyOpenHelper oh;
	SQLiteDatabase db;
	//����uriƥ��������
	static UriMatcher um=new UriMatcher(UriMatcher.NO_MATCH);
	//��������û������uri��ƥ��������õ�uri�У�����ƥ�䣨���ݿ�����person��teacher��
	static {
		um.addURI("com.bin.people", "person", 1);//content://com.bin.people/person
		um.addURI("com.bin.people", "teacher", 2);//content://com.bin.people/teacher
		um.addURI("com.bin.people", "person/#", 3);//content://com.bin.people/person/4
		// #��ʾ���֣������Ƿ�����
	}
	//�����ṩ�ߴ���ʱ������Ӧ��ͬ�������ṩ�߷�������ʱ������
	@Override
	public boolean onCreate() {
		oh=new MyOpenHelper(getContext());
		db=oh.getWritableDatabase();//�������ݿ�
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
		String[] selectionArgs, String sortOrder) {
		Cursor cursor=null;
				db.query("person", projection, selection, selectionArgs, null, null, sortOrder,null);
		if(um.match(uri)==1){
			cursor=db.query("person", projection, selection, selectionArgs, null, null, sortOrder,null);
		}
		if(um.match(uri)==2){
			cursor=db.query("teacher", projection, selection, selectionArgs, null, null, sortOrder,null);
		}
		if(um.match(uri)==3){
			long id=ContentUris.parseId(uri);//��URIĩβЯ��������ȡ����
			cursor=db.query("person",projection,"_id=?",new String[]{id+""},null,null,sortOrder,null);
		}
		return cursor;//��ѯ���ݿⷵ�صĽ������Cursor����
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		if(um.match(uri) == 1){//�����������
			return "vnd.android.cursor.dir/person";
		}
		else if(um.match(uri) == 3){//����������
			return "vnd.android.cursor.item/person";
		}
		return null;
		
	}

	//�˷���������Ӧ�õ��ã����������ݿ��������
	//values:������Ӧ�ô��룬���ڷ�װҪ���������
	//URI:�����ṩ�ߵ���������Ҳ���ǵ�ַ(���嵥�ļ�������)
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		//db.insert("person", null, values);
		//ʹ��uriƥ����ƥ�䴫���uri�����ݶ���õķ����룬��֪��Ҫ���ĸ����������
		if(um.match(uri) == 1){
			db.insert("person", null, values);
			
			//�������ݸı��֪ͨ
			//uri:֪ͨ���͵���һ��uri�ϣ�����ע�������uri�ϵ����ݹ۲��߶������յ����֪ͨ
			getContext().getContentResolver().notifyChange(uri, null);
		}
		else if(um.match(uri) == 2){
			db.insert("teacher", null, values);
			
			getContext().getContentResolver().notifyChange(uri, null);
		}
		else{
			throw new IllegalArgumentException("uri������Ӵ��ôô��");
		}
		return uri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int i=db.delete("person",selection,selectionArgs);//����ֵΪɾ������ ��
		return i;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int i=db.update("person", values, selection, selectionArgs);
		return i;
	}

}
