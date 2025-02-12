package me.fagiolini.cinemapp.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.model.SearchModel;
import me.fagiolini.cinemapp.model.SearchResultModel;
import me.fagiolini.cinemapp.repository.SearchRepository;

import java.util.List;

@Singleton
public class SearchService {
    @Inject
    private SearchRepository searchRepository;

    public List<SearchResultModel> search(SearchModel searchModel) {
        return this.searchRepository.search(searchModel);
    }
}

