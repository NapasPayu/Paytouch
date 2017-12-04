package com.napas.paytouch.ui.search;

import com.napas.paytouch.model.SearchInput;

public interface SearchView {

    void initView(SearchInput searchInput);

    void publishSearch(SearchInput searchInput);

}
