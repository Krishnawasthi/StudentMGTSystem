package com.student.mgt.test;

import com.student.mgt.util.HashUtil;

public class HashUtilTest {
    public static void main(String[] args) {
        testSha256();
        testMd5();
        testNullInput();
        System.out.println("HashUtilTest passed successfully!");
    }

    private static void testSha256() {
        String hash = HashUtil.sha256("hello");
        assert hash.length() == 64 : "SHA-256 hash length should be 64 characters";
        assert hash.equals(HashUtil.sha256("hello")) : "Deterministic hashing check";
        assert !hash.equals(HashUtil.sha256("world")) : "Different inputs yield different hashes";
    }

    private static void testMd5() {
        String hash = HashUtil.md5("hello");
        assert hash.length() == 32 : "MD5 hash length should be 32 characters";
    }

    private static void testNullInput() {
        assert HashUtil.sha256(null).isEmpty();
        assert HashUtil.md5(null).isEmpty();
    }
}
