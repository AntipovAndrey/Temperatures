import React from 'react';

import SearchBarComponent from '../presentation/SearchBarComponent';

const SearchBar = props => {
    return (<SearchBarComponent onSubmit={props.onSubmit}/>);
};

export default SearchBar;
