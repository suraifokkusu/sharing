package com.thewhite.blank.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class WhereClauseBuilder {

    private BooleanBuilder expression;

    private WhereClauseBuilder() {
    }

    private WhereClauseBuilder(BooleanExpression expression) {
        this.expression = new BooleanBuilder(expression);
    }

    public static WhereClauseBuilder getNew() {
        return new WhereClauseBuilder();
    }

    public static WhereClauseBuilder getNew(BooleanExpression initial) {
        return new WhereClauseBuilder(initial);
    }

    /**
     * Добавляет выражение если аргумент не равен Null, если в билдере уже есть выражение будет применен оператор and
     *
     * @param value             проверяемое значение
     * @param doWhenValueExists функция для создания условного выражения на основе проверяемого значения
     * @param <FieldType>       тип проверяемого значения
     */
    public <FieldType> WhereClauseBuilder optionalAnd(FieldType value, Function<FieldType, BooleanExpression> doWhenValueExists) {
        if (value != null) {
            save(doWhenValueExists.apply(value), BooleanBuilder::and);
        }
        return this;
    }

    /**
     * Добавляет выражение если аргумент истина, если в билдере уже есть выражение будет применен оператор and
     *
     * @param condition             проверяемое значение
     * @param doWhenConditionIsTrue функция для создания условного выражения на основе проверяемого значения
     */
    public WhereClauseBuilder conditionalAnd(boolean condition, Supplier<BooleanExpression> doWhenConditionIsTrue) {
        if (condition) {
            save(doWhenConditionIsTrue.get(), BooleanBuilder::and);
        }
        return this;
    }

    /**
     * Добавляет выражение в зависимости от логического значения, если в билдере уже есть выражение будет применен оператор and
     *
     * @param flag                   логическое значение
     * @param doWhenConditionIsTrue  функция для создания условного выражения, если значение истино
     * @param doWhenConditionIsFalse функция для создания условного выражения, если значения ложно
     */
    public WhereClauseBuilder optionalBooleanAnd(Boolean flag, Supplier<BooleanExpression> doWhenConditionIsTrue, Supplier<BooleanExpression> doWhenConditionIsFalse) {
        if (flag != null) {
            Supplier<BooleanExpression> doWhenCondition = flag ? doWhenConditionIsTrue
                                                               : doWhenConditionIsFalse;

            save(doWhenCondition.get(), BooleanBuilder::and);
        }

        return this;
    }

    /**
     * Добавляет выражение если аргумент не равен Null, если в билдере уже есть выражение будет применен оператор or
     *
     * @param value             проверяемое значение
     * @param doWhenValueExists функция для создания условного выражения на основе проверяемого значения
     * @param <FieldType>       тип проверяемого значения
     */
    public <FieldType> WhereClauseBuilder optionalOr(FieldType value, Function<FieldType, BooleanExpression> doWhenValueExists) {
        if (value != null) {
            save(doWhenValueExists.apply(value), BooleanBuilder::or);
        }
        return this;
    }

    /**
     * Добавляет выражение если аргумент истина, если в билдере уже есть выражение будет применен оператор and
     *
     * @param condition             проверяемое значение
     * @param doWhenConditionIsTrue функция для создания условного выражения на основе проверяемого значения
     */
    public WhereClauseBuilder conditionalOr(boolean condition, Supplier<BooleanExpression> doWhenConditionIsTrue) {
        if (condition) {
            save(doWhenConditionIsTrue.get(), BooleanBuilder::or);
        }
        return this;
    }

    /**
     * Добавляет выражение если аргумент не пустая строка или Null, если в билдере уже есть выражение будет применен оператор and
     *
     * @param value                проверяемое значение
     * @param doWhenStringNotBlank функция для создания условного выражения на основе проверяемого значения
     */
    public WhereClauseBuilder optionalStringAnd(String value, Function<String, BooleanExpression> doWhenStringNotBlank) {
        if (StringUtils.hasText(value)) {
            save(doWhenStringNotBlank.apply(value), BooleanBuilder::and);
        }
        return this;
    }

    /**
     * Добавляет выражение если аргумент не пустая строка или Null, если в билдере уже есть выражение будет применен оператор and
     * Используется в случае если строка должна удовлетворять любому из условий
     *
     * @param value      проверяемое значение
     * @param conditions функции для создания условного выражения на основе проверяемого значения, которые будут применемы через оператор or
     */
    @SafeVarargs
    public final WhereClauseBuilder optionalAnyStringAnd(String value, Function<String, BooleanExpression>... conditions) {
        if (!StringUtils.hasText(value)) return this;

        if (conditions == null || conditions.length == 0) return this;

        BooleanExpression predicate = conditions[0].apply(value);
        for (int i = 1; i < conditions.length; ++i) {
            BooleanExpression temp = conditions[i].apply(value);
            predicate = predicate.or(temp);
        }

        save(predicate, BooleanBuilder::and);

        return this;
    }

    /**
     * Добавляет выражение если аргумент не пустая строка или Null, если в билдере уже есть выражение будет применен оператор and
     *
     * @param value                проверяемое значение
     * @param doWhenStringNotBlank функция для создания условного выражения на основе проверяемого значения
     */
    public WhereClauseBuilder optionalStringOr(String value, Function<String, BooleanExpression> doWhenStringNotBlank) {
        if (StringUtils.hasText(value)) {
            save(doWhenStringNotBlank.apply(value), BooleanBuilder::or);
        }
        return this;
    }

    /**
     * Добавляет выражение если аргумент не null или пустая коллекция, если билдер уже содержал значения то будет использован оператор and
     *
     * @param value                      проверяемое значение
     * @param doWhenCollectionIsNotEmpty добавляемое выражение
     * @param <CollectionType>           тип элементов коллекции
     */
    public <CollectionType> WhereClauseBuilder optionalCollectionAnd(Collection<CollectionType> value, Function<Collection<CollectionType>, BooleanExpression> doWhenCollectionIsNotEmpty) {
        if (!CollectionUtils.isEmpty(value)) {
            save(doWhenCollectionIsNotEmpty.apply(value), BooleanBuilder::and);
        }
        return this;
    }

    /**
     * Добавляет выражение если аргумент не null или пустая коллекция, если билдер уже содержал значения то будет использован оператор or
     *
     * @param value                      проверяемое значение
     * @param doWhenCollectionIsNotEmpty добавляемое выражение
     * @param <CollectionType>           тип элементов коллекции
     */
    public <CollectionType> WhereClauseBuilder optionalCollectionOr(Collection<CollectionType> value, Function<Collection<CollectionType>, BooleanExpression> doWhenCollectionIsNotEmpty) {
        if (!CollectionUtils.isEmpty(value)) {
            save(doWhenCollectionIsNotEmpty.apply(value), BooleanBuilder::or);
        }
        return this;
    }

    /**
     * Внутренний метод, для объединения сохраненных выражений
     *
     * @param value          сохраняемое выражение
     * @param joinExpression оператор для объеденения сохраненных и нового выражения
     */
    private void save(BooleanExpression value, BiFunction<BooleanBuilder, BooleanExpression, BooleanBuilder> joinExpression) {
        if (expression == null) {
            expression = new BooleanBuilder(value);
        } else {
            expression = joinExpression.apply(expression, value);
        }
    }

    public BooleanBuilder build() {
        if (expression != null) {
            return expression;
        } else {
            return new BooleanBuilder();
        }
    }
}
