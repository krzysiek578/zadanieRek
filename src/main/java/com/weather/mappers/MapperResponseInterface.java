package com.weather.mappers;

public interface MapperResponseInterface<T, C>{
    T mapToResponseObject(C toMap);
}
