package com.thewhite.blank.service.review;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import com.thewhite.blank.model.QReview;
import com.thewhite.blank.model.Review;
import com.thewhite.blank.repository.ReviewRepository;
import com.thewhite.blank.service.review.argument.CreateReviewArgument;
import com.thewhite.blank.service.review.argument.SearchReviewArgument;
import com.thewhite.blank.service.review.argument.UpdateReviewArgument;
import com.thewhite.blank.util.WhereClauseBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;


/**
 * Generated by Thanos
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    @Override
    @Transactional
    public Review create(@NonNull CreateReviewArgument argument) {
        return repository.save(Review.builder()
                                     .user(argument.getUser())
                                     .exercise(argument.getExercise())
                                     .description(argument.getDescription())
                                     .build());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Review> get(@NonNull UUID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Review getExisting(@NonNull UUID id) {
        return repository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Review.notFound"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> list(@NonNull SearchReviewArgument argument, @NonNull Sort sort) {
        Predicate predicate = buildPredicate(argument);

        return Lists.newArrayList(
                repository.findAll(predicate, sort));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Review> page(@NonNull SearchReviewArgument argument, @NonNull Pageable pageable) {
        Predicate predicate = buildPredicate(argument);

        return repository.findAll(predicate, pageable);
    }

    private Predicate buildPredicate(SearchReviewArgument argument) {
    
        QReview qReview = QReview.review;
        return WhereClauseBuilder.getNew()
                                 .build();
    }

    @Override
    @Transactional
    public Review update(@NonNull UUID id, @NonNull UpdateReviewArgument argument) {
        Review entity = getExisting(id);

        entity.setUser(argument.getUser());
        entity.setExercise(argument.getExercise());
        entity.setDescription(argument.getDescription());

        return repository.save(entity);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void delete(@NonNull UUID id) {
        Review entity = getExisting(id);

        repository.delete(entity);
    }
}