package com.llx278.demo.learnbinder;

/**
 *
 * 手写的远程调用文件,这个文件可以根据.aidl的描述文件来生成
 * Created by llx on 2018/4/11.
 */

public interface IMyTest extends android.os.IInterface {
    /**
     * 本地的ipc实现，
     */
    public static abstract class Stub extends android.os.Binder implements com.llx278.demo.learnbinder.IMyTest {
        private static final java.lang.String DESCRIPTOR = "com.llx278.demo.learnbinder.IMyTest";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         *
         * 将一个binder对象转换成一个com.llx278.demo.learnbinder.IMyTest对象，这里要注意，如果远程对象
         * 和引用对象是在一个进程，那么它们就是同一个对象，如果不是，则返回一个新建的com.llx278.demo.learnbinder.
         * IMyTest.Stub.Proxy对象作为一个远程引用
         */
        public static com.llx278.demo.learnbinder.IMyTest asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }

            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof com.llx278.demo.learnbinder.IMyTest))) {
                // 在一个进程里面，直接返回
                return ((com.llx278.demo.learnbinder.IMyTest) iin);
            }
            // 不在一个进程里面，返回代理对象
            return new com.llx278.demo.learnbinder.IMyTest.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        /**
         * onTransact在本地的进程执行(即真实的binder)，这里面根据code的不同执行接口中所定义的不同方法
         * @param code
         * @param data
         * @param reply
         * @param flags
         * @return
         * @throws android.os.RemoteException
         */
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {

            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                case TRANSACTION_basicTypes: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    long _arg1;
                    _arg1 = data.readLong();
                    boolean _arg2;
                    _arg2 = (0 != data.readInt());
                    float _arg3;
                    _arg3 = data.readFloat();
                    double _arg4;
                    _arg4 = data.readDouble();
                    java.lang.String _arg5;
                    _arg5 = data.readString();
                    java.lang.String _result = this.basicTypes(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }

        /**
         * 代理的binder对象
         */
        private static class Proxy implements com.llx278.demo.learnbinder.IMyTest {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public java.lang.String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.lang.String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeLong(aLong);
                    _data.writeInt(((aBoolean) ? (1) : (0)));
                    _data.writeFloat(aFloat);
                    _data.writeDouble(aDouble);
                    _data.writeString(aString);
                    mRemote.transact(Stub.TRANSACTION_basicTypes, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_basicTypes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    }

    public java.lang.String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, java.lang.String aString) throws android.os.RemoteException;
}
