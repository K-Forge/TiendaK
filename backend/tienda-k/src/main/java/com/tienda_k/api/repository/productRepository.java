package com.tienda_k.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tienda_k.api.model.product;

@Repository

public interface productRepository  extends JpaRepository<product, Long>{}