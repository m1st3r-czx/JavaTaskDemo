package com.myjava.threadtest;

import javax.crypto.interfaces.PBEKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ThreadCallableDemo implements Callable {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    private Integer THREAD_TYPE = 0;

    public ThreadCallableDemo(int type) {
        THREAD_TYPE = type;
    }

    @Override
    public Object call() throws Exception {
        Object data = null;
        try {
            switch (THREAD_TYPE) {
                case 1:
                    data = getString();
                    break;
                case 2:
                    data = getInteger();
                    break;
                case 3:
                    data = getList();
                    break;
                case 4:
                    data = getMap();
                    break;
            }
            if (data == null) {
                throw new Exception("数据不能为空");
            }
        } catch (Exception ex) {
        }
        return data;
    }

    public List<Integer> getList() {
        List<Integer> result = new ArrayList<>();
        result.add(THREAD_TYPE);
        return result;
    }

    public String getString() {
        String result = "你好" + THREAD_TYPE;
        return result;
    }

    public Integer getInteger() {
        Integer result = THREAD_TYPE;
        return result;
    }

    public Map<String, Boolean> getMap() {
        Map<String, Boolean> result = new HashMap<>();
        result.put(THREAD_TYPE.toString(), true);
        return result;
    }


}
