package com.digitalbooks.reader.functions;


import com.digitalbooks.reader.entities.database.ReaderInfo;
import com.digitalbooks.reader.entities.models.ReaderDto;

import java.util.function.Function;


public abstract class ReaderFunctions {

    public static final Function<ReaderInfo, ReaderDto> TBLREADERINFO_TO_READERDTO = tblReaderInfo -> {
        ReaderDto readerDto = new ReaderDto();
        readerDto.setReaderId(tblReaderInfo.getReaderId());
        readerDto.setName(tblReaderInfo.getName());
        readerDto.setEmailId(tblReaderInfo.getEmailId());
        return readerDto;
    };

    private ReaderFunctions() {

    }

}