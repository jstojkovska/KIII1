package com.example.vezba1.dto;

import com.example.vezba1.model.enumerations.CategoryType;

public record CategoryStatsDto(CategoryType categoryType,Long count) {

}
