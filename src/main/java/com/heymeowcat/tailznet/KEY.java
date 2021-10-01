package com.heymeowcat.tailznet;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author heymeowcat
 */

public class KEY {
    public final String secretKey = DigestUtils.md5Hex("meow!meow!meow!");
}
