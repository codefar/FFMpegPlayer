LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE:= libavcodec
LOCAL_SRC_FILES:= lib/libavcodec-57.so
LOCAL_EXPORT_C_INCLUDES:= $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE:= libavformat
LOCAL_SRC_FILES:= lib/libavformat-57.so
LOCAL_EXPORT_C_INCLUDES:= $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE:= libswscale
LOCAL_SRC_FILES:= lib/libswscale-4.so
LOCAL_EXPORT_C_INCLUDES:= $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE:= libavutil
LOCAL_SRC_FILES:= lib/libavutil-55.so
LOCAL_EXPORT_C_INCLUDES:= $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE:= libavfilter
LOCAL_SRC_FILES:= lib/libavfilter-6.so
LOCAL_EXPORT_C_INCLUDES:= $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE:= libswresample
LOCAL_SRC_FILES:= lib/libswresample-2.so
LOCAL_EXPORT_C_INCLUDES:= $(LOCAL_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := ffmpeg-jni
LOCAL_SRC_FILES := ffmpeg-jni.c
LOCAL_C_INCLUDES += $(LOCAL_PATH)/include
LOCAL_LDLIBS += -llog -lz -landroid
LOCAL_SHARED_LIBRARIES := avcodec avfilter avformat avutil  swresample swscale

include $(BUILD_SHARED_LIBRARY)