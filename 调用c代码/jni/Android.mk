#�ڸ��ļ������ļ�·��������cmd����:ndk-build.cmd��������NDK�Ļ���������
LOCAL_PATH := $(call my-dir)

    include $(CLEAR_VARS)
	#�������ɵ��ļ�������ʲô����
    LOCAL_MODULE    := hello
    #Ҫ�����c�ļ�
    LOCAL_SRC_FILES := Hello.c

    include $(BUILD_SHARED_LIBRARY)