package com.example.toyproject001.controller.ifs;

import com.example.toyproject001.model.network.Header;

public interface CrudInterface<Req, Res>{

    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
