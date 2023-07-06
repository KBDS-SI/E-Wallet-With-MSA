package com.kbds.remit.service;

import com.kbds.remit.dto.RemitDto;
import com.kbds.remit.jpa.RemitEntity;

public interface RemitService
{
    RemitDto createRemit(RemitDto remitDto);

    Iterable<RemitEntity> getRemitByAll(RemitDto remitDto);

}
