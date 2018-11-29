import React, {useState} from 'react';

const SearchBarComponent = props => {

    const [lat, setLat] = useState(0);
    const [lon, setLon] = useState(0);

    const onFormSubmit = event => {
        event.preventDefault();
        onSearch({lat, lon});
    };

    const onSearch = coordinates => {
        props.onSubmit(coordinates);
    };

    return (
        <div className="ui segment">
            <form onSubmit={onFormSubmit} className="ui form">
                <div className="two fields">
                    <div className="field">
                        <label>Latitude</label>
                        <input type="text" value={lat}
                               onChange={e => setLat(e.target.value)}/>
                    </div>
                    <div className="field">
                        <label>Longitude</label>
                        <input type="text" value={lon}
                               onChange={e => setLon(e.target.value)}/>
                    </div>
                </div>
            </form>
            <div>
                <button onClick={onFormSubmit} className="ui submit button">Search</button>
                <button onClick={onSearch} className="ui submit button">Show All</button>
            </div>
        </div>
    );
};

export default SearchBarComponent;
