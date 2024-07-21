package com.gmail.mateusfcosta2002.musicwebsite.Repositories;

import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Authors.AUTHORS;
import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Categories.CATEGORIES;
import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.Musics.MUSICS;
import static com.gmail.mateusfcosta2002.musicwebsite.generated.jooq.tables.MusicsTags.MUSICS_TAGS;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SortField;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.mateusfcosta2002.musicwebsite.Repositories.Config.PageResult;

@Repository
public class CustomMusicRepositoryImpl implements CustomMusicRepository {
    private DSLContext dsl;

    public CustomMusicRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Transactional(readOnly = true)
    public PageResult<Result<? extends Record>, Record> 
    search(String search, int pageSize, int pageNumber, MusicOrder order, Long authorIdFilter) {
        Condition where = DSL.noCondition();

        SortField<?> sort = switch (order) {
            case latest -> MUSICS.CREATE_DATE.desc();
            case oldest -> MUSICS.CREATE_DATE.asc();
            case popular -> MUSICS.VIEWS_COUNT.desc();
        };

        if (search != null) {
            where = where.and("search @@ websearch_to_tsquery(?)", search);
        }

        if (authorIdFilter != null) {
            where = where.and(AUTHORS.ID.eq(authorIdFilter));
        }

        var result = dsl
            .select(MUSICS.ID, MUSICS.NAME, MUSICS.DURATION_IN_SECONDS, MUSICS.FILEPATH,
                    MUSICS_TAGS.TAGS_NAME,
                    AUTHORS.ID, AUTHORS.NAME,
                    CATEGORIES.ID, CATEGORIES.NAME, CATEGORIES.PARENT_ID, CATEGORIES.NESTING, DSL.count().over())
            .from(MUSICS)
            .join(AUTHORS).on(AUTHORS.ID.eq(MUSICS.AUTHOR_ID))
            .leftJoin(MUSICS_TAGS).on(MUSICS_TAGS.MUSICS_ID.eq(MUSICS.ID))
            .join(CATEGORIES).on(CATEGORIES.ID.eq(MUSICS.CATEGORY_ID))
            .where(where)
            .orderBy(sort)
            .limit(pageSize)
            .offset((pageNumber - 1) * pageSize)
            .fetch();

        long total = 0;

        if (result.size() > 0) {
            total = result.getFirst().get("count", Long.class);
        }

        int totalPages = (int)(total / pageSize);
        if (total % pageSize > 0) {
            ++totalPages;
        }

        return new PageResult<Result<? extends Record>, Record>(result, total, pageNumber, pageSize, totalPages);
    }
}
