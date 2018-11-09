package br.edu.fapi.dkrt.servlets.mapper;

public interface BaseMapper<I,O> {

    O doMap(I input);

}
