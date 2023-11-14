package com.inova.project_manager_api.utils;

public interface SearchResponseDtoBuilder<SearchResult, SearchResponse>  {
    SearchResponse buildResponseDto(SearchResult searchResult);
}
