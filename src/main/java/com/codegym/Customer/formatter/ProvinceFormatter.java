package com.codegym.Customer.formatter;

import com.codegym.Customer.model.Province;
import com.codegym.Customer.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ProvinceFormatter implements Formatter<Province> {
    private ProvinceService provinceService;
    @Autowired
    private ProvinceFormatter (ProvinceService provinceService){this.provinceService = provinceService;}
    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        return provinceService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Province object, Locale locale) {
        return "["+object.getId()+","+object.getName()+"]";
    }
}
