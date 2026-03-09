package com.eolma.common.id;

import com.github.f4b6a3.tsid.TsidCreator;

/**
 * TSID 기반 ID 생성기.
 * Crockford's Base32 인코딩으로 13자리 문자열 생성.
 */
public final class TsidGenerator {

    private TsidGenerator() {
    }

    public static String generate() {
        return TsidCreator.getTsid().toString();
    }
}
