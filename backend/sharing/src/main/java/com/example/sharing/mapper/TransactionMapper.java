package com.example.sharing.mapper;

import com.example.sharing.dto.TransactionDto;
import com.example.sharing.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDto(Transaction transaction);
    Transaction toEntity(TransactionDto transactionDto);

    void updateEntity(@MappingTarget Transaction transaction, TransactionDto transactionDto);
}
