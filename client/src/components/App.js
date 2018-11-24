import React, {Component} from 'react';
import temperatureApi from '../api/temperature';

import SearchBar from './SearchBar';
import AddTemperature from './AddTemperature';
import TemperatureTable from './TemperatureTable';
import './App.css'

class App extends Component {

    state = {temperatures: []};

    onSearchSubmit = async coordinates => {
        let params = !coordinates || {
            lat: coordinates.lat,
            lon: coordinates.lon
        };
        const response = await temperatureApi.get('/temperatures', {params});
        this.setState({temperatures: response.data});
    };

    onAddSubmit = async temperature => {
        if (!temperature) {
            return;
        }

        await temperatureApi.post('/temperatures', temperature);
        this.onSearchSubmit(null);
    };

    componentDidMount() {
        this.onSearchSubmit(null);
    }

    render() {
        return (
            <div className="ui container">
                <div className="ui grid">
                    <div className="two column row">
                        <div className="column">
                            <SearchBar onSubmit={this.onSearchSubmit}/>
                        </div>
                        <div className="column">
                            <AddTemperature onSubmit={this.onAddSubmit}/>
                        </div>
                    </div>
                    <TemperatureTable temperatures={this.state.temperatures}/>
                </div>
            </div>
        );
    }
}

export default App;
