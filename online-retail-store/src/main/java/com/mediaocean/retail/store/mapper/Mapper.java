package com.mediaocean.retail.store.mapper;


public interface Mapper<I,O> {

    public abstract O map(I input, Object ...otherInputs);
}
