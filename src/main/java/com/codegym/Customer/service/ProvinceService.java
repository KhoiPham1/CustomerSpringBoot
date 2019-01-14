package com.codegym.Customer.service;

import com.codegym.Customer.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProvinceService {
    Iterable<Province> findAll();

    Province findById(Long id);
}
