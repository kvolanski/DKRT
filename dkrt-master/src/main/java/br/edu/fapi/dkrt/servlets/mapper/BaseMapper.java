package br.edu.fapi.dkrt.servlets.mapper;

import java.text.ParseException;

public interface BaseMapper<I,O> {

    O doMap(I input);

}
