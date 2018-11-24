import React, {Component} from 'react';

class SearchBar extends Component {

    state = {lat: 0, lon: 0};

    onFormSubmit = event => {
        event.preventDefault();

        this.onSearch({...this.state});
    };

    onSearch = coordinates => {
        this.props.onSubmit(coordinates);
    };

    render() {
        return (
            <div className="ui segment">
                <form onSubmit={this.onFormSubmit} className="ui form">
                    <div className="two fields">
                        <div className="field">
                            <label>Latitude</label>
                            <input type="text"
                                   value={this.state.lat}
                                   onChange={e => this.setState({lat: e.target.value})}/>
                        </div>
                        <div className="field">
                            <label>Longitude</label>
                            <input type="text"
                                   value={this.state.lon}
                                   onChange={e => this.setState({lon: e.target.value})}/>
                        </div>
                    </div>
                </form>
                <div>
                    <button onClick={this.onFormSubmit} className="ui submit button">Search</button>
                    <button onClick={this.onSearch} className="ui submit button">Show All</button>
                </div>
            </div>
        );
    }
}

export default SearchBar;
