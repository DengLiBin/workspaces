#include <stdio.h>
#include <stdlib.h>
#include <jni.h>

jstring Java_com_bin_c_MainActivity_helloFromC(JNIEnv* env, jobject obj){//���л�����ָ�루env��һ������ָ�룩��MainActivity����
	//c���Ե��ַ���(ָ�����ǵ�һ���ַ��ĵ�ַ)
	char* cstr = "hello from c";
	//��C���Ե��ַ���ת����java���ַ���
	// jstring     (*NewStringUTF)(JNIEnv*, const char*);
//	jstring jstr = (*(*env)).NewStringUTF(env, cstr);
	jstring jstr = (*env)->NewStringUTF(env, cstr);
	return jstr;
}
jint Java_com_bin_c_MainActivity_add(JNIEnv* env, jobject obj, jint i,jint j){
	return i+j;
}
