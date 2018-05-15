/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <string.h>
#include <jni.h>

/* This is a trivial JNI example where we use a native method
 * to return a new VM String. See the corresponding Java source
 * file located at:
 *
 *   hello-jni/app/src/main/java/com/example/hellojni/HelloJni.java
 */

#include "android/log.h"

// Android log function wrappers
static const char* kTAG = "main";
#define LOGI(...) \
  ((void)__android_log_print(ANDROID_LOG_INFO, kTAG, __VA_ARGS__))
#define LOGD(...) \
  ((void)__android_log_print(ANDROID_LOG_DEBUG, kTAG, __VA_ARGS__))
#define LOGW(...) \
  ((void)__android_log_print(ANDROID_LOG_WARN, kTAG, __VA_ARGS__))
#define LOGE(...) \
  ((void)__android_log_print(ANDROID_LOG_ERROR, kTAG, __VA_ARGS__))


JNIEXPORT jobjectArray JNICALL
JNI_my_test_func(JNIEnv* env, jobject thiz,jobjectArray param) {
    LOGD("JNI_my_test_func called!!");

    jsize count = (*env)->GetArrayLength(env,param);
    LOGD("count = %d",count);
    jint i;
    for (i = 0; i < count; i++) {
        jobject obj = (*env)->GetObjectArrayElement(env,param,i);
        if (obj != NULL) {
            const char* str = (*env)->GetStringUTFChars(env,(jstring)obj,NULL);
            LOGD("str : %s",str);
            (*env)->ReleaseStringUTFChars(env,(jstring)obj,str);
        }
    }
    return NULL;
}

static JNINativeMethod gMethods[] = {
        {"myTestFunc","([Ljava/lang/String;)[Ljava/lang/String;",(void*)JNI_my_test_func}
};

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {

    JNIEnv *env;
    if ((*vm)->GetEnv(vm,(void**)&env,JNI_VERSION_1_6) != JNI_OK) {
        LOGD("jni onLoad error");
        return JNI_ERR;
    }
    //jstring className = (*env)->NewStringUTF(env,"com/example/hellojni/HelloJni");
    jclass helloJniClz = (*env)->FindClass(env,"com/example/hellojni/HelloJni");
    jint success = (*env)->RegisterNatives(env,helloJniClz,gMethods,1);
    LOGD("success = %d",success);
    if (success>=0) {
        LOGD("register success");
        return JNI_VERSION_1_6;
    }
    LOGD("register faild");
    return JNI_ERR;
}


JNIEXPORT jstring JNICALL
Java_com_example_hellojni_HelloJni_stringFromJNI(JNIEnv *env,
                                                 jobject thiz) {
#if defined(__arm__)
#if defined(__ARM_ARCH_7A__)
#if defined(__ARM_NEON__)
#if defined(__ARM_PCS_VFP)
#define ABI "armeabi-v7a/NEON (hard-float)"
#else
#define ABI "armeabi-v7a/NEON"
#endif
#else
#if defined(__ARM_PCS_VFP)
#define ABI "armeabi-v7a (hard-float)"
#else
#define ABI "armeabi-v7a"
#endif
#endif
#else
#define ABI "armeabi"
#endif
#elif defined(__i386__)
#define ABI "x86"
#elif defined(__x86_64__)
#define ABI "x86_64"
#elif defined(__mips64)  /* mips64el-* toolchain defines __mips__ too */
#define ABI "mips64"
#elif defined(__mips__)
#define ABI "mips"
#elif defined(__aarch64__)
#define ABI "arm64-v8a"
#else
#define ABI "unknown"
#endif

    return (*env)->NewStringUTF(env, "Hello from JNI !  Compiled with ABI " ABI ".");
}

JNIEXPORT void JNICALL
Java_com_example_hellojni_HelloJni_callJava(JNIEnv *env,jobject thiz) {

    // 实例化com.example.hellojni.Test1
    jclass Test1Class = (*env)->FindClass(env,"com/example/hellojni/Test1");
    jmethodID initMethodId = (*env)->GetMethodID(env,Test1Class,"<init>","()V");
    jobject test1Obj = (*env)->NewObject(env,Test1Class,initMethodId);
    // 调用method1
    jmethodID testMethodId = (*env)->GetMethodID(env,Test1Class,"method1","(Ljava/lang/String;)V");
    jstring param = (*env)->NewStringUTF(env,"hello");
    (*env)->CallVoidMethod(env,test1Obj,testMethodId,param);

}

JNIEXPORT jstring JNICALL
Java_com_example_hellojni_HelloJni_testParam(JNIEnv *env,
                                             jobject thiz,
                                             jint a,
                                             jfloat b,
                                             jstring c) {

    const jint version = (*env)->GetVersion(env);
    LOGD("from hello-jni.c version is %d",version);

    //测试c调用java层的方法，
    jclass helloJniClass = (*env)->GetObjectClass(env,thiz);

    // 调用com.example.hellojni.HelloJni#method0()方法 方法签名 ()V
    // 调用的方法不区分是private还是public
    jmethodID method0Id = (*env)->GetMethodID(env,helloJniClass,"method0","()V");
    (*env)->CallVoidMethod(env,thiz,method0Id);
    (*env)->CallVoidMethodA(env,thiz,method0Id,NULL);
    (*env)->CallVoidMethodV(env,thiz,method0Id,NULL);

    // 调用com.example.hellojni.HelloJni#method0()方法 方法签名 (Ljava/lang/String;I)Z
    jmethodID method1Id = (*env)->GetMethodID(env,helloJniClass,"method1","(Ljava/lang/String;I)Z");
    // 使用 CallBooleanMethod
    jstring method1Param1 = (*env)->NewStringUTF(env,"hello method1");
    jint  method1param2 = 999;
    jboolean method1Ret = (*env)->CallBooleanMethod(env,thiz,method1Id,method1Param1,method1param2);
    LOGD("from hello-jni.c method1 return value = %s",(method1Ret==JNI_TRUE?"true":"false"));
    // 使用 CallBooleanMethodA
    jvalue p1,p2;
    p1.l = method1Param1;
    p2.i = method1param2;
    jvalue values[] = {p1,p2};
    jboolean method1Ret1 = (*env)->CallBooleanMethodA(env,thiz,method1Id,values);
    LOGD("from hello-jni.c method1(A) return value = %s,",(method1Ret1==JNI_TRUE?"true":"false"));

    // 调用com.example.hellojni.HelloJni#method2()方法 方法签名 (I)Ljava/lang/Boolean;
    jmethodID method2Id = (*env)->GetMethodID(env,helloJniClass,"method2","(I)Ljava/lang/Boolean;");
    jint method2Param = 888;
    jobject method2Ret = (*env)->CallObjectMethod(env,thiz,method2Id,method2Param);
    jclass booleanClass = (*env)->GetObjectClass(env,method2Ret);
    jmethodID booleanValueId = (*env)->GetMethodID(env,booleanClass,"booleanValue","()Z");
    jboolean booleanValueReturn = (*env)->CallBooleanMethod(env,method2Ret,booleanValueId);
    LOGD("from hello-jni.c method2 return value = %s",(booleanValueReturn==JNI_TRUE?"true":"false"));
    //(*env)->DeleteLocalRef(env,method2Ret);

    // 调用com.example.hellojni.HelloJni#method3()方法 方法签名 (B)B
    jmethodID method3Id = (*env)->GetMethodID(env,helloJniClass,"method3","(B)B");
    jbyte method3Param = 66;
    jbyte method3Ret = (*env)->CallByteMethod(env,thiz,method3Id,method3Param);
    LOGD("from hello-jni.c method3 return value = %d",method3Ret);

    // 调用com.example.hellojni.HelloJni#method4()方法 方法签名 (C)C
    jmethodID method4Id = (*env)->GetMethodID(env,helloJniClass,"method4","(C)C");
    jchar method4Param = 55;
    jchar method4Ret = (*env)->CallCharMethod(env,thiz,method4Id,method4Param);
    LOGD("from hello-jni.c method4 return value = %d",method4Ret);

    //(*env)->NewObject(env,Test1Class,)

    const char *cStr = (*env)->GetStringUTFChars(env, c, NULL);

    __android_log_print(ANDROID_LOG_DEBUG, "main", "a is %d, b is %f, c is %s", a, b, cStr);
    return (*env)->NewStringUTF(env, "hello from testParam");
}
