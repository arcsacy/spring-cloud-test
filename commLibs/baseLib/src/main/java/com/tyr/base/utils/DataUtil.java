package com.tyr.base.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @Class : DataUtil
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/14 11:41
 * @Version : 1.0
 */
public class DataUtil {

    /**
     * 获取非对称加密公钥 Key
     *
     * @return 公钥 Key
     */
    public static String getPubKey() {
        Resource res = new ClassPathResource(Constants.PUBLIC_KEY);
        InputStreamReader reader = null;
        BufferedReader br = null;
        try {
            reader = new InputStreamReader(res.getInputStream());
            br = new BufferedReader(reader);
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException ioe) {
            return null;
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (br != null)
                    br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
