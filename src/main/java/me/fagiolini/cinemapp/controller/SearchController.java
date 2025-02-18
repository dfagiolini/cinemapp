package me.fagiolini.cinemapp.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.model.SearchModel;
import me.fagiolini.cinemapp.model.SearchResultModel;
import me.fagiolini.cinemapp.service.SearchService;

import java.util.List;

@Controller
public class SearchController {
    @Inject
    SearchService searchService;
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(uri = "/search")
    public List<SearchResultModel> search(@Body SearchModel searchModel) {
        return this.searchService.search(searchModel);
    }
}
