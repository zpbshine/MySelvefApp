LOCAL_PATH       :=  $(call my-dir)
#// 设置工作目录，而my-dir则会返回Android.mk文件所在的目录

include              $(CLEAR_VARS)
#// 清除几乎所有以LOCAL——PATH开头的变量（不包括LOCAL_PATH）

LOCAL_MODULE     :=  hello_jni
#// 设置模块的名称，即编译出来.so文件名
#// 注，要和上述步骤中build.gradle中NDK节点设置的名字相同

LOCAL_SRC_FILES  :=  test.cpp
#// 指定参与模块编译的C/C++源文件名

include              $(BUILD_SHARED_LIBRARY)
#// 指定生成的静态库或者共享库在运行时依赖的共享库模块列表。
