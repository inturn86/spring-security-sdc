package com.sdc.study.security.jwt;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}