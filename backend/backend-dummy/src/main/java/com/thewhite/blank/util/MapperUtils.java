package com.thewhite.blank.util;

import com.thewhite.blank.api.CollectionDto;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public final class MapperUtils {

    public static <EntityT, TargetT> CollectionDto<TargetT> mapPage(Function<EntityT, TargetT> mapper, Page<? extends EntityT> page) {
        return mapCollection(mapper, page.getContent(), page.getTotalElements());
    }

    public static <EntityT, TargetT> CollectionDto<TargetT> mapCollection(Function<EntityT, TargetT> mapper, Collection<? extends EntityT> elements) {
        return mapCollection(mapper, elements, elements.size());
    }

    public static <EntityT, TargetT> CollectionDto<TargetT> mapCollection(Function<EntityT, TargetT> mapper, Collection<? extends EntityT> elements, long realSize) {
        List<TargetT> list = elements.stream().map(mapper).collect(Collectors.toList());
        return new CollectionDto<>(list, realSize);
    }

    public static <EntityT, TargetT> List<TargetT> mapList(Function<EntityT, TargetT> mapper, Collection<? extends EntityT> elements) {
        return elements.stream().map(mapper).collect(Collectors.toList());
    }

    public static <EntityT, TargetT> Function<Page<? extends EntityT>, CollectionDto<TargetT>> getPageMapper(Function<EntityT, TargetT> mapper) {
        return page -> mapPage(mapper, page);
    }

    public static <EntityT, TargetT> Function<Collection<? extends EntityT>, CollectionDto<TargetT>> getCollectionMapper(Function<EntityT, TargetT> mapper) {
        return page -> mapCollection(mapper, page);
    }

    public static <EntityT, TargetT> BiFunction<Collection<? extends EntityT>, Long, CollectionDto<TargetT>> getCollectionWithSizeMapper(Function<EntityT, TargetT> mapper) {
        return (page, size) -> mapCollection(mapper, page, size);
    }

    public static <EntityT, TargetT> Function<Collection<? extends EntityT>, List<TargetT>> getListMapper(Function<EntityT, TargetT> mapper) {
        return page -> mapList(mapper, page);
    }

    public static <EntityT, TargetT> Function<EntityT, TargetT> getMapper(Function<EntityT, TargetT> mapper) {
        return mapper;
    }
}
