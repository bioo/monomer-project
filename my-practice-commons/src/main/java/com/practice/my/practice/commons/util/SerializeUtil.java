package com.practice.my.practice.commons.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * Java原生版的 Serialize
 *
 * @author Pengcheng Zhao
 * @version 1.0
 * @date 2019/6/5 21:38
 */
public class SerializeUtil {
    static final Class<?> CLAZZ = SerializeUtil.class;

    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            /**
             * bos.close应该只是清理了通道的缓存数据 刷新通道 - - 已经接收过来的数据是没有销毁的  还在内存里
             * ByteArrayOutputStream 里有个byt e类型的数组 数据都是存放在这里的
             * toByteArray是获取byte数组的副本回传  缓存区的数据还是存在的
             * 得要加个flush可能就不管用了 像FilterOutpuStream 直接把flush写在close里了
             */
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
            LoggerUtils.fmtError(CLAZZ, e, "serialize error %s", JSONObject.parseObject(JSON.toJSONString(value)));
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }


    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
            }
        } catch (Exception e) {
            LoggerUtils.fmtError(CLAZZ, e, "serialize error %s", in);
        } finally {
            close(is);
            close(bis);
        }
        return (T) rv;
    }

    private static void close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                LoggerUtils.fmtError(CLAZZ, "close stream error");
            }
    }
}
