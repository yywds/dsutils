package com.example.utils.Utils.fileutils;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.utils.Listener.UploadCurrentProgressListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

/**
 * 用了监听上传进度
 */
public class ExMultipartBody extends RequestBody {
    private RequestBody mRequestBody;
    private int mCurrentLength;
    private UploadCurrentProgressListener mProgressListener;

    public ExMultipartBody(MultipartBody requestBody) {
        this.mRequestBody = requestBody;
    }

    public ExMultipartBody(MultipartBody requestBody, UploadCurrentProgressListener progressListener) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        // 静态代理最终还是调用的代理对象的方法
        return mRequestBody.contentType();
    }

    /**
     * 文件总长度
     */
    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    /**
     * 重写
     */
    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Log.e("TAG","监听");
        // 总的长度
        final long contentLength = contentLength();
        // 获取当前写了多少数据？BufferedSink Sink(okio 就是 io )就是一个 服务器的 输出流，我还是不知道写了多少数据

        // 又来一个代理 ForwardingSink
        ForwardingSink forwardingSink = new ForwardingSink(sink) {
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                // 每次写都会来这里
                mCurrentLength += byteCount;
                if(mProgressListener == null){
                   return;
                }
                mProgressListener.onCurrentProgress(contentLength,mCurrentLength);//进度
                Log.e("TAG",contentLength+" : "+mCurrentLength);
                super.write(source, byteCount);
            }
        };
        // 转一把
        BufferedSink bufferedSink = Okio.buffer(forwardingSink);
        mRequestBody.writeTo(bufferedSink);
        // 刷新，RealConnection 连接池
        bufferedSink.flush();
    }
}