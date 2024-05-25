package com.thewhite.blank.service.user;

import com.thewhite.blank.model.User;
import com.thewhite.blank.service.user.argument.CreateUserArgument;
import com.thewhite.blank.service.user.argument.SearchUserArgument;
import com.thewhite.blank.service.user.argument.UpdateUserArgument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Generated by Thanos
 */
public interface UserService {

    User create(CreateUserArgument argument);

    Optional<User> get(UUID id);

    User getExisting(UUID id);

    List<User> list(SearchUserArgument argument, Sort sort);

    Page<User> page(SearchUserArgument argument, Pageable pageable);

    User update(UUID id, UpdateUserArgument argument);

    void delete(UUID id);
}