package com.weather.mappers;

public interface Mapper<T, C>{
    T mapToResponseObject(C toMap);
}
